package Bayes;
/********************************************************************************
 *
 *
 * 数据挖掘实验，分类实验
 * 朴素贝叶斯分类
 *
 *训练样本示例格式：（R,1,5,5,4）
 */

import java.io.File;
import java.util.Scanner;
import java.util.Vector;

public class BayesDemo {
    static Vector<String> indata = new Vector<>();//读入数据
    static Vector<double[]> catagory_clean = new Vector<>();//存储类别R的所有数据
    static Vector<double[]> catagory_buggy = new Vector<>();//存储类别L的所有数据
//    static Vector<int[]> catagory_B = new Vector<>();//存储类别B的所有数据


    public static boolean loadData(String url) {//加载测试的数据文件
        try {
            Scanner in = new Scanner(new File(url));//读入文件
            while (in.hasNextLine()) {
                String str = in.nextLine();//将文件的每一行存到str的临时变量中
                indata.add(str);//将每一个样本点的数据追加到Vector 中
            }
            return true;
        } catch (Exception e) { //如果出错返回false
            return false;
        }
    }

    public static void pretreatment(Vector<String> indata) {   //数据预处理，将原始数据中的每一个属性值提取出来存放到Vector<double[]>  data中
        int i = 0;
        String t;
        while (i < indata.size()) {//取出indata中的每一行值
            double[] tem = new double[61];
            t = indata.get(i);
            String[] sourceStrArray = t.split(",");//使用字符串分割函数提取出各属性值
            switch (sourceStrArray[61]) {
                case "0": {
                    for (int j = 0; j <= 60; j++) {
                        tem[j] = Double.parseDouble(sourceStrArray[j]);
                    }
                    catagory_clean.add(tem);
                    break;
                }
                case "1": {
                    for (int j = 0; j <= 60; j++) {
                        tem[j] = Double.parseDouble(sourceStrArray[j]);
                    }
                    catagory_buggy.add(tem);
                    break;
                }
//                case "B": {
//                    for (int j = 1; j < 5; j++) {
//                        tem[j - 1] = Integer.parseInt(sourceStrArray[j]);
//                    }
//                    catagory_B.add(tem);
//                    break;
//                }
            }
            i++;
        }

    }

    public static double bayes(double[] x, Vector<double[]> catagory) {
        double[] ai_y = new double[61];
        int[] sum_ai = new int[61];
        for (int i = 0; i <= 60; i++) {
            for (int j = 0; j <= catagory.size(); j++) {
                if (x[i] == catagory.get(j)[i]) {
                    sum_ai[i]++;
                }
            }
        }
        double sum = 1;
        for (int i = 0; i <= 60; i++) {
            ai_y[i] = (double) sum_ai[i] / (double) catagory.size();
            sum *= ai_y[i];
        }
        return sum;
    }

    public static void main(String[] args) {


        loadData("data\\JDT.csv");
        pretreatment(indata);
//        System.out.println("catagory_clean"+catagory_clean.size());
//        System.out.println("indata"+indata.size());
        double p_yclean = (double) catagory_clean.size() / (double) (indata.size());//表示概率p（R）
//        double p_yB = (double) catagory_B.size() / (double) (indata.size());//表示概率p（B）
        double p_ybuggy = (double) catagory_buggy.size() / (double) (indata.size());//表示概率p（L）

        double[] x = new double[61];
        double x_in_clean, x_in_buggy, x_in_B;

        int sumClean = 0, sumBuggy = 0, sumB = 0;
        double correct = 0;


//        System.out.println("请输入样本x格式如下：\n 1 1 1 1\n");
        int r = 0;
        while (r < indata.size()) {

//            for (int i = 0; i <= 60; i++)
//                //读取数字放入数组的第i个元素
//            {
//                x[i] = Double.parseDouble(indata.get(r).split(",")[i]);
//            }

//            x_in_B = bayes(x, catagory_B) * p_yB;
            x_in_buggy = bayes(x, catagory_buggy) * p_ybuggy;
            x_in_clean = bayes(x, catagory_clean) * p_yclean;


//            if (x_in_B == Math.max(Math.max(x_in_B, x_in_buggy), x_in_clean)) {
//                System.out.println("输入的第"+r+"样本属于类别：B");
//                sumB++;
//                if(indata.get(r).split(",",5)[0].equals("B")) {
//                    correct++;
//                }
//            }
            if (x_in_buggy == Math.max(x_in_buggy, x_in_clean)) {
                System.out.println("输入的第" + r + "样本属于类别：buggy");
                sumBuggy++;
//                if(indata.get(r).split(",")[0].equals("L")) {
//                    correct++;
//                }
            } else if (x_in_clean == Math.max(x_in_buggy, x_in_clean)) {
                System.out.println("输入的第" + r + "样本属于类别：clean");
                sumClean++;
//                if(indata.get(r).split(",",5)[0].equals("R")) {
//                    correct++;
//                }
            }

            r++;


        }

        System.out.println("使用训练样本进行分类器检验得到结果统计如下：");
        System.out.println("R类有：" + sumClean + "    实际有clean类样本" + catagory_clean.size() + "个");
        System.out.println("L类有：" + sumBuggy + "    实际有buggy类样本" + catagory_buggy.size() + "个");
//        System.out.println("B类有："+sumB+"      实际有B类样本"+catagory_B.size()+"个");

//        System.out.println("分类的正确率为"+correct*1.0/indata.size()*100+"%");

    }
}


