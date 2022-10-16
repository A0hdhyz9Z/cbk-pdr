package team.cbk.demo.service.impl;

import lombok.AllArgsConstructor;
import org.ejml.simple.SimpleMatrix;
import team.cbk.demo.mapper.AlgMapper;
import team.cbk.demo.mapper.OrderMapper;
import team.cbk.demo.mapper.UserMapper;
import team.cbk.demo.pojo.*;
import team.cbk.demo.service.ThreadService;
import team.cbk.demo.utils.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

@AllArgsConstructor
public  class ThreadServiceImpl extends Thread implements ThreadService {
    UserMapper userMapper;
    OrderMapper orderMapper;
    AlgMapper algMapper;
    Order order;
    //public static ConcurrentLinkedQueue<Order> offlineQueen=new ConcurrentLinkedQueue<>();
    @Override
    public void run() {
                        int algNum = order.getOrderAlg();
                        if (algNum >= 100) {
                            //决策树训练任务
                            DecisionTreeClassifier best = null;
                            Double bestF1 = Double.valueOf(0);
                            ArrayList<Double> accList = new ArrayList<>();
                            ArrayList<Double> preList = new ArrayList<>();
                            ArrayList<Double> recallList = new ArrayList<>();
                            ArrayList<Double> f1List = new ArrayList<>();
                            for(int i =0;i<10;i++){
                                DataSet temp = new DataSet(order.getOrderDataAddress());
                                ArrayList<DataSet> utilsDataSets = DataSet.train_test_split(temp, 0.9);
                                DataSet dataTrain = utilsDataSets.get(0);
                                DataSet dataTest = utilsDataSets.get(1);

                                //训练决策树模型
                                DecisionTreeClassifier decClassifier = new DecisionTreeClassifier();
                                decClassifier.fit(dataTrain);
                                //预测标签和真实标签
                                ArrayList<String> predictY = dataTest.getColumn(dataTest.labels.size() - 1);
                                ArrayList<String> trueY = decClassifier.predict(dataTest);
                                //通过混淆矩阵计算准确率、精度、召回率、F1 score、特异度（Specificity）、G-mean、MCC
                                double acc = confusionMatrix.cal_accuracy(predictY, trueY);
                                double pre = confusionMatrix.cal_precision(predictY, trueY);
                                double recall = confusionMatrix.cal_recall(predictY, trueY);
                                double F1 = 2 * pre * recall / (pre + recall);
                                accList.add(acc);
                                preList.add(pre);
                                recallList.add(recall);
                                f1List.add(F1);
                                if (best == null) {
                                    best = decClassifier;
                                    bestF1 = F1;
                                } else if (bestF1 < F1) {
                                    best = decClassifier;
                                    bestF1=F1;
                                }
                            }
                            //保存模型
                            final String projectPath = System.getProperty("user.dir");
                            String filePath = projectPath + "/record/" + order.getUserName() + "/" + order.getOrderId() + "/";
                            File filedest = new File(filePath + "3.dat");
                            if(!filedest.getParentFile().exists())
                                filedest.getParentFile().mkdirs();
                            DecisionTreeClassifier.writeObjectToFile(best, filePath+"3.dat");
                            int id =order.getOrderId()*1000+100;
                            algMapper.insertAcc(Double.valueOf(id),accList.get(0),accList.get(1),accList.get(2),accList.get(3),accList.get(4),accList.get(5),
                                    accList.get(6),accList.get(7),accList.get(8),accList.get(9),Double.valueOf(order.getOrderId()));
                            algMapper.insertPre(Double.valueOf(id),preList.get(0),preList.get(1),preList.get(2),preList.get(3),preList.get(4),preList.get(5),
                                    preList.get(6),preList.get(7),preList.get(8),preList.get(9),Double.valueOf(order.getOrderId()));
                            algMapper.insertRecall(Double.valueOf(id),recallList.get(0),recallList.get(1),recallList.get(2),recallList.get(3),recallList.get(4),recallList.get(5),
                                    recallList.get(6),recallList.get(7),recallList.get(8),recallList.get(9),Double.valueOf(order.getOrderId()));
                            algMapper.insertF1(Double.valueOf(id),f1List.get(0),f1List.get(1),f1List.get(2),f1List.get(3),f1List.get(4),f1List.get(5),
                                    f1List.get(6),f1List.get(7),f1List.get(8),f1List.get(9),Double.valueOf(order.getOrderId()));
                            algMapper.insertAlgByOrderIdAndAlgName(order.getOrderId(), 3);
                            //写入数据库
                            algNum -= 100;
                        }
                        if (algNum >= 10) {
                            //knn训练任务
                            KNN best = null;
                            Double bestF1 = Double.valueOf(0);
                            ArrayList<Double> accList = new ArrayList<>();
                            ArrayList<Double> preList = new ArrayList<>();
                            ArrayList<Double> recallList = new ArrayList<>();
                            ArrayList<Double> f1List = new ArrayList<>();
                            for(int i = 0;i<10;i++){
                                //训练集测试集路径
                                UtilsDataSet temp = new UtilsDataSet(order.getOrderDataAddress());
                                ArrayList<UtilsDataSet> utilsDataSets = UtilsDataSet.train_test_split(temp, 0.9);
                                UtilsDataSet dataTrain = utilsDataSets.get(0);
                                UtilsDataSet dataTest = utilsDataSets.get(1);
                                KNN knnClassifier = new KNN(6, dataTrain);
                                knnClassifier.fit(dataTrain);
                                //预测标签和真实标签
                                ArrayList<String> predictY = dataTest.getColumn(dataTest.labels.size() - 1);
                                ArrayList<String> trueY = knnClassifier.predict(dataTest);

                                //通过混淆矩阵计算准确率、精度、召回率、F1 score、特异度（Specificity）、G-mean、MCC
                                double acc = confusionMatrix.cal_accuracy(predictY, trueY);
                                double pre = confusionMatrix.cal_precision(predictY, trueY);
                                double recall = confusionMatrix.cal_recall(predictY, trueY);
                                double F1 = 2 * pre * recall / (pre + recall);
                                accList.add(acc);
                                preList.add(pre);
                                recallList.add(recall);
                                f1List.add(F1);
                                if (best == null) {
                                    best = knnClassifier;
                                    bestF1 = F1;
                                } else if (bestF1 < F1) {
                                    best = knnClassifier;
                                    bestF1=F1;
                                }
                            }
                            final String projectPath = System.getProperty("user.dir");
                            String filePath = projectPath + "/record/" + order.getUserName() + "/" + order.getOrderId() + "/";
                            File filedest = new File(filePath + "2.dat");
                            if(!filedest.getParentFile().exists())
                                filedest.getParentFile().mkdirs();
                            KNN.writeObjectToFile(best, filePath+"2.dat");
                            int id =order.getOrderId()*1000+10;
                            algMapper.insertAcc(Double.valueOf(id),accList.get(0),accList.get(1),accList.get(2),accList.get(3),accList.get(4),accList.get(5),
                                    accList.get(6),accList.get(7),accList.get(8),accList.get(9),Double.valueOf(order.getOrderId()));
                            algMapper.insertPre(Double.valueOf(id),preList.get(0),preList.get(1),preList.get(2),preList.get(3),preList.get(4),preList.get(5),
                                    preList.get(6),preList.get(7),preList.get(8),preList.get(9),Double.valueOf(order.getOrderId()));
                            algMapper.insertRecall(Double.valueOf(id),recallList.get(0),recallList.get(1),recallList.get(2),recallList.get(3),recallList.get(4),recallList.get(5),
                                    recallList.get(6),recallList.get(7),recallList.get(8),recallList.get(9),Double.valueOf(order.getOrderId()));
                            algMapper.insertF1(Double.valueOf(id),f1List.get(0),f1List.get(1),f1List.get(2),f1List.get(3),f1List.get(4),f1List.get(5),
                                    f1List.get(6),f1List.get(7),f1List.get(8),f1List.get(9),Double.valueOf(order.getOrderId()));
                            algMapper.insertAlgByOrderIdAndAlgName(order.getOrderId(), 2);
                            algNum -= 10;
                        }
                        if (algNum < 10 && algNum > 0) {
                            //逻辑回归训练任务
                            LogisticRegression best = null;
                            Double bestF1 = Double.valueOf(0);
                            ArrayList<Double> accList = new ArrayList<>();
                            ArrayList<Double> preList = new ArrayList<>();
                            ArrayList<Double> recallList = new ArrayList<>();
                            ArrayList<Double> f1List = new ArrayList<>();
                            for (int i = 0; i < 10; i++) {
                                HashMap<String, SimpleMatrix> temp = CsvHandling.loadMatrixFromCsv_xy(order.getOrderDataAddress());
                                SimpleMatrix X = temp.get("x");
                                SimpleMatrix y = temp.get("y");
                                //训练集划分
                                temp = DataHandling.train_test_split(X, y, 0.9);
                                SimpleMatrix train_x = temp.get("train_x");
                                SimpleMatrix train_y = temp.get("train_y");
                                SimpleMatrix test_x = temp.get("test_x");
                                SimpleMatrix test_y = temp.get("test_y");
                                //
                                LogisticRegression l = new LogisticRegression();
                                double[] Jw = l.fit(train_x, train_y);
                                SimpleMatrix predict_y = l.predict(test_x);
                                double acc = ErrorAnalysis.cal_accuracy(predict_y, test_y);
                                double pre = ErrorAnalysis.cal_precision(predict_y, test_y);
                                double recall = ErrorAnalysis.cal_recall(predict_y, test_y);
                                double f1score = 2 * (pre * recall) / (pre + recall);
                                accList.add(acc);
                                preList.add(pre);
                                recallList.add(recall);
                                f1List.add(f1score);
                                if (best == null) {
                                    best = l;
                                    bestF1 = f1score;
                                } else if (bestF1 < f1score) {
                                    best = l;
                                    bestF1=f1score;
                                }

                            }
                            final String projectPath = System.getProperty("user.dir");
                            String filePath = projectPath + "/record/" + order.getUserName() + "/" + order.getOrderId() + "/";
                            File filedest = new File(filePath + "1.dat");
                            if(!filedest.getParentFile().exists())
                                filedest.getParentFile().mkdirs();
                            best.storeModule(filePath + "1.dat");
                            algNum = 0;
                            int id =order.getOrderId()*1000+1;
                            algMapper.insertAcc(Double.valueOf(id),accList.get(0),accList.get(1),accList.get(2),accList.get(3),accList.get(4),accList.get(5),
                                    accList.get(6),accList.get(7),accList.get(8),accList.get(9),Double.valueOf(order.getOrderId()));
                            algMapper.insertPre(Double.valueOf(id),preList.get(0),preList.get(1),preList.get(2),preList.get(3),preList.get(4),preList.get(5),
                                    preList.get(6),preList.get(7),preList.get(8),preList.get(9),Double.valueOf(order.getOrderId()));
                            algMapper.insertRecall(Double.valueOf(id),recallList.get(0),recallList.get(1),recallList.get(2),recallList.get(3),recallList.get(4),recallList.get(5),
                                    recallList.get(6),recallList.get(7),recallList.get(8),recallList.get(9),Double.valueOf(order.getOrderId()));
                            algMapper.insertF1(Double.valueOf(id),f1List.get(0),f1List.get(1),f1List.get(2),f1List.get(3),f1List.get(4),f1List.get(5),
                                    f1List.get(6),f1List.get(7),f1List.get(8),f1List.get(9),Double.valueOf(order.getOrderId()));
                            algMapper.insertAlgByOrderIdAndAlgName(order.getOrderId(), 1);
                        }
                        orderMapper.updataOrderStatus(order.getOrderId());
                        User user = userMapper.selectUserByname(order.getUserName());
                        EmailUtils.trainSuccess(user.getUserName(), user.getUserEmail(), order.getOrderName());
    }

}
