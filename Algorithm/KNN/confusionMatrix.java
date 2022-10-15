package KNN;

import java.util.ArrayList;

public class confusionMatrix {
    public static double cal_accuracy(ArrayList<String> pred_y, ArrayList<String> true_y) {

        double TP = 0, FN = 0, FP = 0, TN = 0;
        for (int i = 0; i < pred_y.size(); i++) {
            if ("clean".equals(pred_y.get(i)) && "clean".equals(true_y.get(i))) {
                TP += 1;
            } else if ("buugy".equals(pred_y.get(i)) && "clean".equals(true_y.get(i))) {
                FN += 1;
            } else if ("clean".equals(pred_y.get(i)) && "buggy".equals(true_y.get(i))) {
                FP += 1;
            } else if ("buggy".equals(pred_y.get(i)) && "buggy".equals(true_y.get(i))) {
                TN += 1;
            }
        }
        return (TP + TN) / (TP + FN + FP + TN);
    }

    public static double cal_precision(ArrayList<String> pred_y, ArrayList<String> true_y) {
        //传入预测出的结果列表pred_y和真实的结果列表true_y,并返回精度
        double TP = 0, FN = 0, FP = 0, TN = 0;
        for (int i = 0; i < pred_y.size(); i++) {
            if ("clean".equals(pred_y.get(i)) && "clean".equals(true_y.get(i))) {
                TP += 1;
            } else if ("buugy".equals(pred_y.get(i)) && "clean".equals(true_y.get(i))) {
                FN += 1;
            } else if ("clean".equals(pred_y.get(i)) && "buggy".equals(true_y.get(i))) {
                FP += 1;
            } else if ("buggy".equals(pred_y.get(i)) && "buggy".equals(true_y.get(i))) {
                TN += 1;
            }
        }
        return TP / (TP + FP);
    }

    public static double cal_recall(ArrayList<String> pred_y, ArrayList<String> true_y) {
        //传入预测出的结果列表pred_y和真实的结果列表true_y,并返回召回率
        double TP = 0, FN = 0, FP = 0, TN = 0;
        for (int i = 0; i < pred_y.size(); i++) {
            if ("clean".equals(pred_y.get(i)) && "clean".equals(true_y.get(i))) {
                TP += 1;
            } else if ("buugy".equals(pred_y.get(i)) && "clean".equals(true_y.get(i))) {
                FN += 1;
            } else if ("clean".equals(pred_y.get(i)) && "buggy".equals(true_y.get(i))) {
                FP += 1;
            } else if ("buggy".equals(pred_y.get(i)) && "buggy".equals(true_y.get(i))) {
                TN += 1;
            }
        }
        return TP / (TP + FN);
    }

    public static double cal_Specificity(ArrayList<String> pred_y, ArrayList<String> true_y) {
        double TP = 0, FN = 0, FP = 0, TN = 0;
        for (int i = 0; i < pred_y.size(); i++) {
            if ("clean".equals(pred_y.get(i)) && "clean".equals(true_y.get(i))) {
                TP += 1;
            } else if ("buugy".equals(pred_y.get(i)) && "clean".equals(true_y.get(i))) {
                FN += 1;
            } else if ("clean".equals(pred_y.get(i)) && "buggy".equals(true_y.get(i))) {
                FP += 1;
            } else if ("buggy".equals(pred_y.get(i)) && "buggy".equals(true_y.get(i))) {
                TN += 1;
            }
        }
        return TN / (TN + FP);
    }

    public static double cal_MCC(ArrayList<String> pred_y, ArrayList<String> true_y) {
        double TP = 0, FN = 0, FP = 0, TN = 0;
        for (int i = 0; i < pred_y.size(); i++) {
            if ("clean".equals(pred_y.get(i)) && "clean".equals(true_y.get(i))) {
                TP += 1;
            } else if ("buugy".equals(pred_y.get(i)) && "clean".equals(true_y.get(i))) {
                FN += 1;
            } else if ("clean".equals(pred_y.get(i)) && "buggy".equals(true_y.get(i))) {
                FP += 1;
            } else if ("buggy".equals(pred_y.get(i)) && "buggy".equals(true_y.get(i))) {
                TN += 1;
            }
        }
        return (TN * TP - FP * FN) / Math.sqrt((TP + FP) * (TP + FN) * (TN + FP) * (TN + FN));
    }
}
