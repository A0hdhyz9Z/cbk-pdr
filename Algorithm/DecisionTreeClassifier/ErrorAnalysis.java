package DecisionTreeClassifier;

import org.ejml.simple.SimpleMatrix;

/**
 * 用于提供测试的评价指标和误差分析
 * @author hqz
 * @version 1.0
 *
 */
public class ErrorAnalysis {
    private ErrorAnalysis() {
    }

    /**
     *
     * @param pred_y 预测出来的特征矩阵y
     * @param true_y 实际的特征矩阵y
     * @return 返回准确率
     */
    public static double cal_accuracy(SimpleMatrix pred_y, SimpleMatrix true_y) {

        double TP = 0, FN = 0, FP = 0, TN = 0;
        for (int i = 0; i < pred_y.numRows(); i++) {
            if (Math.abs(pred_y.get(i) - 1) < 0.001 && Math.abs(true_y.get(i) - 1) < 0.001)
                TP += 1;
            else if (Math.abs(pred_y.get(i) - 1) > 0.001 && Math.abs(true_y.get(i) - 1) < 0.001)
                FN += 1;
            else if (Math.abs(pred_y.get(i) - 1) < 0.001 && Math.abs(true_y.get(i) - 1) > 0.001)
                FP += 1;
            else
                TN += 1;
        }
        return (TP + TN) / (TP + FN + FP + TN);
    }
    /**
     *
     * @param pred_y 预测出来的特征矩阵y
     * @param true_y 实际的特征矩阵y
     * @return 返回精准度
     */
    public static double cal_precision(SimpleMatrix pred_y, SimpleMatrix true_y) {
        //传入预测出的结果列表pred_y和真实的结果列表true_y,并返回精度
        double TP = 0, FN = 0, FP = 0, TN = 0;
        for (int i = 0; i < pred_y.numRows(); i++) {
            if (Math.abs(pred_y.get(i) - 1) < 0.001 && Math.abs(true_y.get(i) - 1) < 0.001)
                TP += 1;
            else if (Math.abs(pred_y.get(i) - 1) > 0.001 && Math.abs(true_y.get(i) - 1) < 0.001)
                FN += 1;
            else if (Math.abs(pred_y.get(i) - 1) < 0.001 && Math.abs(true_y.get(i) - 1) > 0.001)
                FP += 1;
            else
                TN += 1;
        }
        return TP / (TP + FP);
    }
    /**
     *
     * @param pred_y 预测出来的特征矩阵y
     * @param true_y 实际的特征矩阵y
     * @return 返回召回率
     */
    public static double cal_recall(SimpleMatrix pred_y, SimpleMatrix true_y) {
        //传入预测出的结果列表pred_y和真实的结果列表true_y,并返回召回率
        double TP = 0, FN = 0, FP = 0, TN = 0;
        for (int i = 0; i < pred_y.numRows(); i++) {
            if (Math.abs(pred_y.get(i) - 1) < 0.001 && Math.abs(true_y.get(i) - 1) < 0.001)
                TP += 1;
            else if (Math.abs(pred_y.get(i) - 1) > 0.001 && Math.abs(true_y.get(i) - 1) < 0.001)
                FN += 1;
            else if (Math.abs(pred_y.get(i) - 1) < 0.001 && Math.abs(true_y.get(i) - 1) > 0.001)
                FP += 1;
            else
                TN += 1;
        }
        return TP / (TP + FN);
    }


}
