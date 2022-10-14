package Bayes;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class NativeMain {
    public NativeMain() {

    }

    public static String getResult(String s1, String s2) {
        ArrayList<ArrayList<String>> datas = new ArrayList<ArrayList<String>>();
        ArrayList<Double> p2 = new ArrayList<Double>();//不同类别对给定多维向量特征的概率

        ArrayList<String> answer = new ArrayList<String>();
        String str = null;
        int count = 0, c = 0;
        String a = null;
        try {
            FileInputStream fis;
            fis = new FileInputStream(s1);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            br.readLine();
            while ((str = br.readLine()) != null) {
                String[] strs = str.split(",");
                ArrayList<String> array = new ArrayList<String>();
                for (int i = 0; i < strs.length; i++)
                    array.add(strs[i]);
                datas.add(array);
            }
            br.close();
            FileInputStream fis2;
            fis2 = new FileInputStream(s2);
            InputStreamReader isr2 = new InputStreamReader(fis2, "UTF-8");
            BufferedReader br2 = new BufferedReader(isr2);
            br2.readLine();
            while ((str = br2.readLine()) != null) {
                c++;
                String[] strs = str.split(",");
                ArrayList<String> test = new ArrayList<String>();
                for (int i = 0; i < strs.length - 1; i++) {
                    test.add(strs[i]);
                }
                NativeBeyes beyes = new NativeBeyes(datas, test);
                if (beyes.predect(datas, test, p2).equals(strs[strs.length - 1])) {
                    count++;
                }
                FileInputStream fis1 = new FileInputStream(s2);
                InputStreamReader isr1 = new InputStreamReader(fis1, "UTF-8");
                BufferedReader br1 = new BufferedReader(isr1);
                br1.readLine();
                while ((str = br1.readLine()) != null) {
                    String[] strs1 = str.split(",");
                    ArrayList<String> array = new ArrayList<String>();
                    for (int i = 0; i < strs1.length; i++)
                        array.add(strs1[i]);
                    datas.add(array);
                }
                br1.close();
            }
            br2.close();
            a = "朴素贝叶斯法计算正确率为" + (double) count / datas.size();
            System.out.println(a);
//            for (int i = 0; i < p2.size(); i++) {
//                System.out.println(p2.get(i));
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return a;
    }
}