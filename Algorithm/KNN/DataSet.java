package KNN;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

class DataSet implements Serializable {
	public ArrayList<String> labels=new ArrayList<String>();
	public ArrayList<Integer> featProperties=new ArrayList<Integer>();               //特征属性 1为连续性变量 0为离散型变量
	public ArrayList<ArrayList<String>> dataList=new ArrayList<ArrayList<String>>();//数据集
	private static final long serialVersionUID = 15L;
	
	/**
	 * 方法说明：默认全为连续性变量
	 * @param DataPath
	 */
	public  DataSet(String DataPath) {
		ReadCsv(DataPath);
		for(int i=0;i<labels.size();i++) {
			featProperties.add(1);
		}
	}
	public  DataSet() {

	}
	public ArrayList<ArrayList<String>> getDataSet() {
		return dataList;
	}
	
	public ArrayList<String> getLabels() {
		return labels;
	}
	/**
	 * 方法说明：读入csv文件，csv文件第一行为标签名，后面为数据样本
	 * @param DataPath 数据集路径
	 */
	private void ReadCsv(String DataPath) {
		String line = "";
		String cvsSplitBy = ",";
		
		try  {						
			BufferedReader br = new BufferedReader(new FileReader(DataPath));
			//初始化labels成员变量
			line = br.readLine();
			String[] labs = line.split(cvsSplitBy);
			for (int i = 0; i < labs.length; i++) {
				labels.add(labs[i]);
			}
						
			while ((line = br.readLine()) != null) {
				String[] items = line.split(cvsSplitBy);
				ArrayList<String> sample=new ArrayList<String>();
				for (int i = 0; i < items.length; i++) {
					sample.add(items[i]);					
				}
				dataList.add(sample);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//深拷贝
	public  DataSet remove(int axis) {
		if(axis<0 || axis>labels.size()-1) {
			return null;
		}
		DataSet newDataSet=new DataSet();
		for(int i=0;i<labels.size();i++) {
			if(i!=axis) {
				newDataSet.labels.add(labels.get(i));
				newDataSet.featProperties.add(featProperties.get(i));
				for(int j=0 ;j<dataList.size();j++) {
					newDataSet.dataList.get(j).add(dataList.get(j).get(i));
				}
			}
		}
		return newDataSet;
	}
	
	public  void add(DataSet dat) {	
		for(int j=0 ;j<dat.dataList.size();j++) {			
			dataList.add((ArrayList<String>)dat.dataList.get(j).clone());		
		}
	}
	
	public  DataSet copy() {
		DataSet newDataSet=new DataSet();
		for(int i=0;i<labels.size();i++) {
			newDataSet.labels.add(labels.get(i));
			newDataSet.featProperties.add(featProperties.get(i));	
		}
		for(int j=0 ;j<dataList.size();j++) {			
			newDataSet.dataList.add((ArrayList<String>)dataList.get(j).clone());		
		}
		return newDataSet;
	}
	
	
	public  DataSet findSubDataset(int axis,String value) {
		if(axis<0 || axis>labels.size()-1) {
			return null;
		}
		
		DataSet newDataSet=new DataSet();
		for(int i=0;i<labels.size();i++) {
			newDataSet.labels.add(labels.get(i));
			newDataSet.featProperties.add(featProperties.get(i));	
		}
		
		for(int j=0 ;j<dataList.size();j++) {
			if(dataList.get(j).get(axis).equals(value)) {				
			    newDataSet.dataList.add((ArrayList<String>)dataList.get(j).clone());
			}			
		}
		return newDataSet;
	}
	
	public  DataSet findSubDataset(int axis,String LorR,String value) {
		if(axis<0 || axis>labels.size()-1) {
			return null;
		}
		
		DataSet newDataSet=new DataSet();
		for(int i=0;i<labels.size();i++) {
			newDataSet.labels.add(labels.get(i));
			newDataSet.featProperties.add(featProperties.get(i));	
		}
		
		for(int j=0 ;j<dataList.size();j++) {
			if(LorR.equals("<=")) {
				if(Double.parseDouble(dataList.get(j).get(axis)) <= Double.parseDouble(value)) {				
					newDataSet.dataList.add((ArrayList<String>)dataList.get(j).clone());
				}	
			}else {
				if(Double.parseDouble(dataList.get(j).get(axis)) > Double.parseDouble(value)) {				
					newDataSet.dataList.add((ArrayList<String>)dataList.get(j).clone());
				}	
			}
					
		}
		return newDataSet;
	}
	
	
	public  ArrayList<String> getColumn(int axis) {
		if(axis<0 || axis>labels.size()-1) {
			return null;
		}
		ArrayList<String> col=new ArrayList<String>();
		
		for(int j=0 ;j<dataList.size();j++) {
			col.add(dataList.get(j).get(axis));
		}
		return col;		
	}
	
	
	public  boolean DigitizeLabel() {		
		for(int j=0 ;j<dataList.size();j++) {
			if(dataList.get(j).get(labels.size()-1).equals("clean")) {
				dataList.get(j).set(labels.size()-1, "1");
			}else if(dataList.get(j).get(labels.size()-1).equals("buggy")){
				dataList.get(j).set(labels.size()-1, "0");
			}
			
		}
		return true;		
	}
}
