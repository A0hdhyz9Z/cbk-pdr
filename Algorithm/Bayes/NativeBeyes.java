package Bayes;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class NativeBeyes {
    private ArrayList<ArrayList<String>> datas = new ArrayList<ArrayList<String>>();//训练数据集
    private ArrayList<String> test = new ArrayList<String>();//测试数据集
    private static ArrayList<ArrayList<String>> subdatas = new ArrayList<ArrayList<String>>();//同类别数据集
    public ArrayList<Double> p = new ArrayList<Double>();//不同类别对应概率
    public ArrayList<String> kinds = new ArrayList<String>();//记录种类值
    private ArrayList<Double> p1 = new ArrayList<Double>();//不同类别对给定多维向量特征的概率
//    private ArrayList<Double> p2 = new ArrayList<Double>();//不同类别对给定多维向量特征的概率


    public NativeBeyes(ArrayList<ArrayList<String>> datas, ArrayList<String> test) {
        this.datas = datas;
        this.test = test;
    }

    /*
     * 计算类别种数计算各类别的概率
     */
    public int sorts(ArrayList<ArrayList<String>> datas, ArrayList<String> test) {
        int a = 0, b = 0, c = datas.size();//类别种类计数器及首个类别计数
        while (datas.isEmpty() != true) {
            for (int i = 0; i < datas.size(); i++) {
                if (datas.get(0).get(datas.get(0).size() - 1).equals(datas.get(i).get(datas.get(0).size() - 1)) == true) {
                    subdatas.add(datas.get(i));
                    b++;
                    if (b >= 2) {
                        datas.remove(i);
                        i--;
                    }

                }
            }
            datas.remove(0);
            p.add((double) b);
            b = 0;
            a++;
        }
        for (int i = 0; i < a; i++) {
            p.add(p.get(i) / c);
        }
        for (int i = 0; i < a; i++) {
            p.remove(0);
        }
        numerator(test);

        //创建输出缓冲流对象
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter("ArrayListToFileTest.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < p.size(); i++) {
            double line = p.get(i);
            try {
                bw.write(String.valueOf(line));
                bw.newLine();
                bw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //释放资源
        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return a;
    }

    //***********************************//
    /*
     * 计算分母（denominator）的值，即相应属性值在对应属性集中的概率值
     */
    public double denominator(ArrayList<ArrayList<String>> datas, ArrayList<String> test) {
        double pp = 1.0;
        int a = 0;
        for (int i = 0; i < test.size(); i++) {
            for (int j = 0; j < datas.size(); j++) {
                if (datas.get(j).get(i).equals(test.get(i)) == true) {
                    a++;
                }
            }
            pp *= (double) a / datas.size();
            a = 0;
        }
        return pp;
    }

    //************************************//
    /*
     * 计算相应类别下不同特征属性值的条件概率(部分分子numerator值)
     */
    public void numerator(ArrayList<String> test) {
        double p = 1.0;
        double a = 0;
        int b = 0, c = 0;
        while (this.subdatas.isEmpty() != true) {
            for (int i = 0; i < this.subdatas.size(); i++) {
                if (this.subdatas.get(0).get(this.subdatas.get(0).size() - 1).equals(this.subdatas.get(i).get(this.subdatas.get(0).size() - 1)) == true) {
                    b++;
                } else {
                    break;
                }
            }
            kinds.add(this.subdatas.get(0).get(this.subdatas.get(0).size() - 1));
            for (int i = 0; i < test.size(); i++) {
                for (int j = 0; j < b; j++) {
                    if (test.get(i).equals(this.subdatas.get(j).get(i))) {
                        c++;
                    }
                }
                p *= (double) c / b;
                c = 0;
            }
            p1.add(p);
            a = p;
//            System.out.println("p1: " + p1);
            p = 1.0;
            String str = this.subdatas.get(0).get(this.subdatas.get(0).size() - 1);
            for (int i = 0; i < b; i++) {
                this.subdatas.remove(0);
            }
            b = 0;
        }
    }
    //******************************************//
    /*
     * 比较得出概率最大的可能分类
     */

    public int compare(ArrayList<ArrayList<String>> datas, ArrayList<String> test, int c, double q, ArrayList<Double> p, ArrayList<Double> p1) {
        double[] num = new double[c];//1
        int t = 0;
        double a = 0.0;
        for (int i = 0; i < num.length; i++) {
            num[i] = (double) p.get(i) * p1.get(i) / q;
        }
        a = num[0];
        for (int i = 0; i < num.length; i++) {
            if (a < num[i]) {
                a = num[i];
                t = i;
            }
        }
        System.out.printf("该分类可信度为：%.2f     ", num[t]);
        return t;
    }

    //********************************************//
    public String predect(ArrayList<ArrayList<String>> datas, ArrayList<String> test, ArrayList<Double> p2) throws IOException {
        NativeBeyes beyes = new NativeBeyes(datas, test);
        numerator(test);//p(类别下的条件概率)

        double bb = beyes.denominator(datas, test);
        p2.add(bb);

        String str2 = beyes.kinds.get(beyes.compare(datas, test, beyes.sorts(datas, test), bb, beyes.p, beyes.p1));

        System.out.println("分类为：" + str2);




        return str2;
    }
}