package KNN;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KNN implements Serializable{
    DataSet dataset;
    Integer k = 5;
    private static final long serialVersionUID = 12L;

    public KNN(int k, DataSet ds) {
        dataset = ds;
        this.k = k;
    }

    public KNN(int k) {
        this.k = k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public void fit(DataSet ds) {
        dataset = ds;
    }

    public String predict(ArrayList<String> sample) {
        List<Map<String, Object>> disList = calEuclidDistance(sample);
        return findMajority(disList);
    }

    public static void writeObjectToFile(Object obj, String filename) {
        File file = new File(filename);
        FileOutputStream out;
        try {
            out = new FileOutputStream(file);
            ObjectOutputStream objOut = new ObjectOutputStream(out);
            objOut.writeObject(obj);
            objOut.flush();
            objOut.close();
            System.out.println("write object success!");
        } catch (IOException e) {
            System.out.println("write object failed");
            e.printStackTrace();
        }
    }


    public static Object readObjectFromFile(String filename) {
        Object temp = null;
        File file = new File(filename);
        FileInputStream in;
        try {
            in = new FileInputStream(file);
            ObjectInputStream objIn = new ObjectInputStream(in);
            temp = objIn.readObject();
            objIn.close();
            System.out.println("read object success!");
        } catch (IOException e) {
            System.out.println("read object failed");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return temp;
    }


    public ArrayList<String> predict(DataSet dset) {
        ArrayList<String> results = new ArrayList<String>();
        for (int i = 0; i < dset.dataList.size(); i++) {
            results.add(predict(dset.dataList.get(i)));
        }
        return results;
    }

    public String findMajority(List<Map<String, Object>> disList) {
        Collections.sort(disList, new Comparator<Map<String, Object>>() {
            public int compare(Map<String, Object> map1, Map<String, Object> map2) {
                double d1 = (double) map1.get("euclidDis");
                double d2 = (double) map2.get("euclidDis");
                if (d1 > d2) {
                    return 1;
                } else if (d1 < d2) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        List<Map<String, Object>> subDisList = disList.subList(0, k);
        Map<String, Integer> classify = new HashMap();
        for (Map<String, Object> map : subDisList) {
            String type = (String) map.get("type");
            classify.merge(type, 1, Integer::sum);
        }

        int frequency = 0;
        String kind = "";
        for (Map.Entry<String, Integer> entry : classify.entrySet()) {
            if (entry.getValue() > frequency) {
                frequency = entry.getValue();
                kind = entry.getKey();
            }
        }
        return kind;

    }

    public List<Map<String, Object>> calEuclidDistance(ArrayList<String> sample) {
        List<Map<String, Object>> disList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < dataset.dataList.size(); i++) {
            double euclidDis = 0;
            for (int j = 0; j < dataset.dataList.get(0).size() - 1; j++) {
                euclidDis = euclidDis + Math.pow(
                        Double.parseDouble(sample.get(j)) - Double.parseDouble(dataset.dataList.get(i).get(j)), 2);
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("euclidDis", Math.sqrt(euclidDis));
            map.put("index", i);
            map.put("type", dataset.dataList.get(i).get(dataset.dataList.get(0).size() - 1));
            disList.add(map);
        }
        return disList;
    }

    public boolean score(ArrayList<String> realResults, ArrayList<String> precResults) {
        double rightNum = 0;
        double allNum = realResults.size();

        if (realResults.size() != precResults.size()) {
            return false;
        }
        for (int i = 0; i < realResults.size(); i++) {
//            System.out.println(realResults.get(i) + "----------" + precResults.get(i));
            if (realResults.get(i).equals(precResults.get(i))) {
                rightNum++;
            }
        }

//        System.out.println("准确率:" + rightNum / allNum);
        return true;
    }

    //	public double score(ArrayList<String> realResults,ArrayList<String> precResults) {
//		double rightNum=0;
//		double allNum=realResults.size();
//
//		for(int i=0;i<realResults.size();i++) {
//			if(realResults.get(i).equals(precResults.get(i)) ) {
//				rightNum++;
//			}		
//		}		
//		return rightNum/allNum;
//	}
//    public static void main(String[] args) {
//        //训练集测试集路径
//        String pathTrain = "data\\Lucene.csv";
//        String pathTest = "data\\PDE.csv";
//
//        DataSet datTrain = new DataSet(pathTrain);
//        DataSet datTest = new DataSet(pathTest);
//
//
//        KNN knnClassifier = new KNN(6, datTrain);
////		for(int k=1;k<100;k++) {
////			knnClassifier.setK(k);
////			double score=knnClassifier.score(datTest.getColumn(datTest.labels.size()-1),knnClassifier.predict(datTest));
////			System.out.println(k+"----"+score);
////		}
//
//
//        knnClassifier.score(datTest.getColumn(datTest.labels.size() - 1), knnClassifier.predict(datTest));
//    }
}
