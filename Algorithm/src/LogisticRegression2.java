import weka.classifiers.functions.Logistic;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

import java.io.File;

public class LogisticRegression2 {

    // 训练得到分类器
    static Logistic trainModel(String arffFile) throws Exception {

        File inputFile = new File("data\\JDT.arff");
        ArffLoader loader = new ArffLoader();
        loader.setFile(inputFile);
        Instances insTrain = loader.getDataSet(); // 读入训练文件
        insTrain.setClassIndex(61);
        Logistic logic = new Logistic();

        logic.buildClassifier(insTrain);//根据训练数据构造分类器

        return logic;
    }

    public static void main(String[] args) throws Exception {

        final String arffFilePath = "data\\JDT.arff";

        Logistic logic = trainModel(arffFilePath);

        ArffLoader loader = new ArffLoader();
        loader.setFile(new File(arffFilePath)); //测试文件
        Instances insTest = loader.getDataSet(); // 读入测试文件
        insTest.setClassIndex(61); //设置分类属性所在行号（第一行为0号）

        double sum = insTest.numInstances();
        double right = 0.0f;
        for (int i = 0; i < sum; i++) {

            Instance ins = insTest.instance(i);

            if (logic.classifyInstance(ins) == ins.classValue()) {
                right++;

                System.out.println("No.\t" + i + "\t" + ins.classValue() + " clean");
            } else {
                System.out.println("No.\t" + i + "\t" + ins.classValue() + " buggy");
            }
        }
        // 打印出分类的精确度
        System.out.println("classification precision:" + (right / sum));
    }
}
