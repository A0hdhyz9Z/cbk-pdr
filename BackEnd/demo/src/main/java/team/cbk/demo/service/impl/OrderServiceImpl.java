package team.cbk.demo.service.impl;

import org.ejml.simple.SimpleMatrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import team.cbk.demo.mapper.AlgMapper;
import team.cbk.demo.mapper.OrderMapper;
import team.cbk.demo.mapper.UserMapper;
import team.cbk.demo.pojo.*;
import team.cbk.demo.service.OrderService;
import team.cbk.demo.utils.Constants;
import team.cbk.demo.utils.CsvHandling;
import team.cbk.demo.utils.DataSet;
import team.cbk.demo.utils.UtilsDataSet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    AlgMapper algMapper;
    @Override
    public int upload(MultipartFile file, User user) {
        int num = orderMapper.selectcountTempRecordByUserName(user.getUserName());
        if(num == 0){
            orderMapper.insertTempRecordByUserName(user.getUserName());
        }

        int orderId = orderMapper.selectTempOrderIdByUserName(user.getUserName());
        //修改这里
        //TODO
        final String projectPath = System.getProperty("user.dir");
        String filePath=projectPath+"/record/"+user.getUserName()+"/"+orderId+"/";
        String fileName = orderId+".csv";
        if(num != 0){
            int deletenum = orderMapper.selectTempOrderIdByUserName(user.getUserName());
            File file1 = new File(filePath + deletenum + ".csv");
            if (file1.exists())
                file1.delete();
        }
        File filedest = new File(filePath + fileName);
        if(!filedest.getParentFile().exists())
            filedest.getParentFile().mkdirs();
        try {
            file.transferTo(filedest);
            /*
            String detect = CsvDetect.detect(filePath + fileName);
            if(!detect.equals("1")){
                return 0;
            }
             */
            orderMapper.updateTempRecordOrderAddressByUserName(user.getUserName(),filePath+fileName);
            return 1;
        } catch (IOException e) {
            e.printStackTrace();
            orderMapper.updateTempRecordOrderAddressByUserName(user.getUserName(),filePath+fileName);
            return 0;
        }
    }

    @Override
    public int insertNewOrder(Order order) {
        orderMapper.insertOrderByOrder(order);
        order.setOrderId(orderMapper.selectOrderIdByOrder(order));
        dealOrder(order);
        return 0;
    }

    @Override
    public int updateTempOrder(Order order) {
        orderMapper.updateTempRecordOrderToCompleteOrderByOrder(order);
        order.setOrderId(orderMapper.selectOrderIdByOrder(order));
        order.setOrderDataAddress(orderMapper.selectOrderDataAddress(order));
        dealOrder(order);
        return 0;
    }

    public void dealOrder(Order order){
        ThreadServiceImpl threadService = new ThreadServiceImpl(userMapper,orderMapper,algMapper,order);
        threadService.start();
    }

    @Override
    public ArrayList<Order> selectOrders(String username) {
        ArrayList<Order> orders = orderMapper.selectAllOrdersByUserName(username);
        return orders;
    }

    @Override
    public ArrayList<score> selectScore(int orderid) {
        ArrayList<score> scores = orderMapper.selectScoreByOrderId(orderid);
        return scores;
    }

    @Override
    public int predict(MultipartFile file, User user, int orderId, int Alg) {
        //修改这里
        //TODO
        final String projectPath = System.getProperty("user.dir");
        String filePath=projectPath+"/record/"+user.getUserName()+"/"+orderId+"/";
        String fileName = "temp.csv";
        File filedest = new File(filePath + fileName);
        int ans=-1;
        if(!filedest.getParentFile().exists())
            return -1;
        try {
            file.transferTo(filedest);
            /*
            String detect = CsvDetect.detect(filePath + fileName);
            if(!detect.equals("1")){
                return 0;
            }
             */
            if(Alg == 1){
                LogisticRegression logisticRegression = new LogisticRegression();
                logisticRegression.fitFromFile(filePath+"1.dat");
                HashMap<String, SimpleMatrix> temp = CsvHandling.loadMatrixFromCsv_xy(filePath + fileName);
                SimpleMatrix X = temp.get("x");
                SimpleMatrix predict_y1 = logisticRegression.predict(X);
                ans= (int) predict_y1.get(0,0);
            }else if(Alg == 2){
                KNN KNNLoad = (KNN) KNN.readObjectFromFile(filePath+"2.dat");
                UtilsDataSet temp = new UtilsDataSet(filePath + fileName);
                ArrayList<UtilsDataSet> utilsDataSets = UtilsDataSet.train_test_split(temp, 0.5);
                UtilsDataSet dataTest = utilsDataSets.get(0);
                ArrayList<String> trueY = KNNLoad.predict(dataTest);
                if(trueY.get(0).equals("clean")){
                    ans=1;
                }else {
                    ans=0;
                }
            }else if(Alg == 3){
                DecisionTreeClassifier decClassifierLoad = (DecisionTreeClassifier) DecisionTreeClassifier.readObjectFromFile(filePath+"3.dat");
                //预测标签和真实标签
                DataSet temp = new DataSet(filePath + fileName);
                ArrayList<DataSet> utilsDataSets = DataSet.train_test_split(temp, 0.5);
                DataSet dataTest = utilsDataSets.get(0);
                ArrayList<String> trueY = decClassifierLoad.predict(dataTest);
                if(trueY.get(0).equals("clean")){
                    ans=1;
                }else {
                    ans=0;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            File file1 = new File(filePath + fileName);
            if (file1.exists())
                file1.delete();
            return ans;
        }
    }
}
