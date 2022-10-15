package DecisionTreeClassifier;

import java.util.ArrayList;

public class DecisionTreeMain {
    public static void main(String[] args) {
        //训练集测试集路径
        String pathTrain = "data\\Lucene.csv";
        String pathTest = "data\\PDE.csv";


        DataSet dataTrain = new DataSet(pathTrain);
        DataSet dataTest = new DataSet(pathTest);

        //训练决策树模型
        DecisionTreeClassifier decClassifier = new DecisionTreeClassifier();
        decClassifier.fit(dataTrain);

        //保存决策树模型，将整个训练好的类保存至文件中
        DecisionTreeClassifier.writeObjectToFile(decClassifier, "DecisionTreeClassifier.dat");

        //载入已经训练好的决策树模型，直接开始测试，省去再次训练的时间
        DecisionTreeClassifier decClassifierLoad = (DecisionTreeClassifier) DecisionTreeClassifier.readObjectFromFile("DecisionTreeClassifier.dat");
        decClassifierLoad.score(dataTest.getColumn(dataTest.labels.size() - 1), decClassifier.predict(dataTest));

        //预测标签和真实标签
        ArrayList<String> predictY = dataTest.getColumn(dataTest.labels.size() - 1);
        ArrayList<String> trueY = decClassifier.predict(dataTest);
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
