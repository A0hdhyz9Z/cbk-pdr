package team.cbk.demo;

import org.ejml.simple.SimpleMatrix;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import team.cbk.demo.mapper.AlgMapper;
import team.cbk.demo.pojo.DecisionTreeClassifier;
import team.cbk.demo.pojo.KNN;
import team.cbk.demo.pojo.LogisticRegression;
import team.cbk.demo.service.OrderService;
import team.cbk.demo.utils.*;

import java.util.ArrayList;
import java.util.HashMap;

//@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {

    @Autowired
    AlgMapper algMapper;
    @Autowired
    OrderService orderService;
    @Test
    void contextLoads() {
//        //训练集测试集路径
//        String pathTrain = Constants.JDT;
//
//        UtilsDataSet temp = new UtilsDataSet(pathTrain);
//        ArrayList<UtilsDataSet> utilsDataSets = UtilsDataSet.train_test_split(temp, 0.9);
//        UtilsDataSet dataTrain = utilsDataSets.get(0);
//        UtilsDataSet dataTest = utilsDataSets.get(1);
//        KNN knnClassifier = new KNN(6, dataTrain);
//
//        knnClassifier.fit(dataTrain);
//        KNN.writeObjectToFile(knnClassifier, "knnClassifier.dat");
//
//        //载入已经训练好的决策树模型，直接开始测试，省去再次训练的时间
//        KNN KNNLoad = (KNN) KNN.readObjectFromFile("knnClassifier.dat");
//        KNNLoad.score(dataTest.getColumn(dataTest.labels.size() - 1), KNNLoad.predict(dataTest));
//
////        knnClassifier.score(dataTest.getColumn(dataTest.labels.size() - 1), knnClassifier.predict(dataTest));
//
//        //预测标签和真实标签
//        ArrayList<String> predictY = dataTest.getColumn(dataTest.labels.size() - 1);
//        ArrayList<String> trueY = KNNLoad.predict(dataTest);
//        System.out.println("真实标签：" + trueY);
//        System.out.println("预测标签：" + predictY);
//
//        //通过混淆矩阵计算准确率、精度、召回率、F1 score、特异度（Specificity）、G-mean、MCC
//        double acc = confusionMatrix.cal_accuracy(predictY, trueY);
//        double pre = confusionMatrix.cal_precision(predictY, trueY);
//        double recall = confusionMatrix.cal_recall(predictY, trueY);
//        double F1 = 2 * pre * recall / (pre + recall);
//        double Specificity = confusionMatrix.cal_Specificity(predictY, trueY);
//        double Gmean = Math.sqrt(recall * Specificity);
//        double MCC = confusionMatrix.cal_MCC(predictY, trueY);
//
//        System.out.println("准确率：" + acc);
//        System.out.println("精度：" + pre);
//        System.out.println("召回率：" + recall);
//        System.out.println("F1 score = " + F1);
//        System.out.println("特异度(Specificity) = " + Specificity);
//        System.out.println("G-mean = " + Gmean);
//        System.out.println("MCC = " + MCC);




//决策树

//        String pathTrain = Constants.JDT;
//
//        DataSet temp = new DataSet(pathTrain);
//        ArrayList<DataSet> utilsDataSets = DataSet.train_test_split(temp, 0.9);
//        DataSet dataTrain = utilsDataSets.get(0);
//        DataSet dataTest = utilsDataSets.get(1);
//
//        //训练决策树模型
//        DecisionTreeClassifier decClassifier = new DecisionTreeClassifier();
//        decClassifier.fit(dataTrain);
//
//        //保存决策树模型，将整个训练好的类保存至文件中
//        DecisionTreeClassifier.writeObjectToFile(decClassifier, "DecisionTreeClassifier.dat");
//
//        //载入已经训练好的决策树模型，直接开始测试，省去再次训练的时间
//        DecisionTreeClassifier decClassifierLoad = (DecisionTreeClassifier) DecisionTreeClassifier.readObjectFromFile("DecisionTreeClassifier.dat");
//        //预测标签和真实标签
//        ArrayList<String> predictY = dataTest.getColumn(dataTest.labels.size() - 1);
//        ArrayList<String> trueY = decClassifier.predict(dataTest);
//        System.out.println("真实标签：" + trueY);
//        System.out.println("预测标签：" + predictY);
//
//        //通过混淆矩阵计算准确率、精度、召回率、F1 score、特异度（Specificity）、G-mean、MCC
//        double acc = confusionMatrix.cal_accuracy(predictY, trueY);
//        double pre = confusionMatrix.cal_precision(predictY, trueY);
//        double recall = confusionMatrix.cal_recall(predictY, trueY);
//        double F1 = 2 * pre * recall / (pre + recall);
//        double Specificity = confusionMatrix.cal_Specificity(predictY, trueY);
//        double Gmean = Math.sqrt(recall * Specificity);
//        double MCC = confusionMatrix.cal_MCC(predictY, trueY);
//
//        System.out.println("准确率：" + acc);
//        System.out.println("精度：" + pre);
//        System.out.println("召回率：" + recall);
//        System.out.println("F1 score = " + F1);
//        System.out.println("特异度(Specificity) = " + Specificity);
//        System.out.println("G-mean = " + Gmean);
//        System.out.println("MCC = " + MCC);




        //algMapper.insertAcc(1.0,2.0,2.0,2.0,2.0,2.0,22.0,2.0,2.0,22.0,2.0,2.0);
        //algMapper.insertPre(1.0,2.0,2.0,2.0,2.0,2.0,22.0,2.0,2.0,22.0,2.0,2.0);
        //algMapper.insertRecall(1.0,2.0,2.0,2.0,2.0,2.0,22.0,2.0,2.0,22.0,2.0,2.0);
        //algMapper.insertF1(1.0,2.0,2.0,2.0,2.0,2.0,22.0,2.0,2.0,22.0,2.0,2.0);
//        score score = orderService.selectScore(2);
//        System.out.println(score);
// 需要数据集参数 即模型存放位置
//        HashMap<String, SimpleMatrix> temp = CsvHandling.loadMatrixFromCsv_xy(Constants.JDT);
//        SimpleMatrix X = temp.get("x");
//        SimpleMatrix y = temp.get("y");
//
//        //训练集划分
//        temp = DataHandling.train_test_split(X, y, 0.9);
//        SimpleMatrix train_x = temp.get("train_x");
//        SimpleMatrix train_y = temp.get("train_y");
//        SimpleMatrix test_x = temp.get("test_x");
//        SimpleMatrix test_y = temp.get("test_y");
//        //
//        LogisticRegression l = new LogisticRegression();
//        double[] Jw=l.fit(train_x,train_y);
//        l.storeModule(System.getProperty("user.dir")+"/test/1.dat");
//        //WriteObjToFile.writeObjectToFile(l,System.getProperty("user.dir")+"/test/1.dat");
//        SimpleMatrix predict_y = l.predict(test_x);
//        double acc = ErrorAnalysis.cal_accuracy(predict_y, test_y);
//        double pre = ErrorAnalysis.cal_precision(predict_y, test_y);
//        double recall = ErrorAnalysis.cal_recall(predict_y, test_y);
//        System.out.println(acc);
//        System.out.println(pre);
//        System.out.println(recall);
//
//
//        System.out.println("------------");
//        LogisticRegression logisticRegression = new LogisticRegression();
//        logisticRegression.fitFromFile(System.getProperty("user.dir")+"/test/1.dat");
//        SimpleMatrix predict_y1 = logisticRegression.predict(test_x);
//        double acc1 = ErrorAnalysis.cal_accuracy(predict_y1, test_y);
//        double pre1 = ErrorAnalysis.cal_precision(predict_y1, test_y);
//        double recall1 = ErrorAnalysis.cal_recall(predict_y1, test_y);
//        System.out.println(acc1);
//        System.out.println(pre1);
//        System.out.println(recall1);
    }

}