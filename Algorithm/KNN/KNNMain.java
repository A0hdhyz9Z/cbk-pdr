package KNN;

import DecisionTreeClassifier.DecisionTreeClassifier;

import java.util.ArrayList;

public class KNNMain {
    public static void main(String[] args) {
        //训练集测试集路径
        String pathTrain = "data\\Lucene.csv";
        String pathTest = "data\\PDE.csv";

        DataSet dataTrain = new DataSet(pathTrain);
        DataSet dataTest = new DataSet(pathTest);

        KNN knnClassifier = new KNN(6, dataTrain);
//		for(int k=1;k<100;k++) {
//			knnClassifier.setK(k);
//			double score=knnClassifier.score(datTest.getColumn(datTest.labels.size()-1),knnClassifier.predict(datTest));
//			System.out.println(k+"----"+score);
//		}

        knnClassifier.fit(dataTrain);
        KNN.writeObjectToFile(knnClassifier, "knnClassifier.dat");

        //载入已经训练好的决策树模型，直接开始测试，省去再次训练的时间
        KNN KNNLoad = (KNN) KNN.readObjectFromFile("knnClassifier.dat");
        KNNLoad.score(dataTest.getColumn(dataTest.labels.size() - 1), knnClassifier.predict(dataTest));

//        knnClassifier.score(dataTest.getColumn(dataTest.labels.size() - 1), knnClassifier.predict(dataTest));

        //预测标签和真实标签
        ArrayList<String> predictY = dataTest.getColumn(dataTest.labels.size() - 1);
        ArrayList<String> trueY = knnClassifier.predict(dataTest);
        System.out.println("真实标签：" + trueY);
        System.out.println("预测标签：" + predictY);

        //通过混淆矩阵计算准确率、精度、召回率、F1 score、特异度（Specificity）、G-mean、MCC
        double acc = confusionMatrix.cal_accuracy(predictY, trueY);
        double pre = confusionMatrix.cal_precision(predictY, trueY);
        double recall = confusionMatrix.cal_recall(predictY, trueY);
        double F1 = 2 * pre * recall / (pre + recall);
        double Specificity = confusionMatrix.cal_Specificity(predictY, trueY);
        double Gmean = Math.sqrt(recall * Specificity);
        double MCC = confusionMatrix.cal_MCC(predictY, trueY);

        System.out.println("准确率：" + acc);
        System.out.println("精度：" + pre);
        System.out.println("召回率：" + recall);
        System.out.println("F1 score = " + F1);
        System.out.println("特异度(Specificity) = " + Specificity);
        System.out.println("G-mean = " + Gmean);
        System.out.println("MCC = " + MCC);
    }
}
