package DecisionTreeClassifier;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;


//树节点
class TreeNode implements Serializable{
	//节点名
	private String sname;
	public Integer type;    // 0 代表离散型特征   1代表连续型特征    2代表尾节点
	private static final long serialVersionUID = 12L;
	public TreeNode(String str,int type) {
		sname=str;
		this.type=type;
	}
	
	public String getsname() {
		return sname;
	}
	ArrayList<Edge> edges=new ArrayList<Edge>();//和子节点间的边标签
	ArrayList<TreeNode> childNodes=new ArrayList<TreeNode>();//对应子节点
}

class Edge implements Serializable{
	public String compare;
	public String number;
	private static final long serialVersionUID = 11L;
	public Edge(String compare,String number) {
		this.compare=compare;
		this.number=number;
	}
	
	public Edge(String number) {
		this.number=number;
	}
}


public class DecisionTreeClassifier implements Serializable {
	private TreeNode node=null;
	private static final long serialVersionUID = 1L;


	public static void writeObjectToFile(Object obj,String filename)
	{
		File file =new File(filename);
		FileOutputStream out;
		try {
			out = new FileOutputStream(file);
			ObjectOutputStream objOut=new ObjectOutputStream(out);
			objOut.writeObject(obj);
			objOut.flush();
			objOut.close();
			System.out.println("write object success!");
		} catch (IOException e) {
			System.out.println("write object failed");
			e.printStackTrace();
		}
	}


	public static Object readObjectFromFile(String filename)
	{
		Object temp=null;
		File file =new File(filename);
		FileInputStream in;
		try {
			in = new FileInputStream(file);
			ObjectInputStream objIn=new ObjectInputStream(in);
			temp=objIn.readObject();
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

	public DecisionTreeClassifier() {
		
	}
	
	
	public void fit(DataSet dataset) {
		node=creattree(dataset);
		put(node);
	}
	
	
	public ArrayList<String> predict(DataSet dataset) {
		ArrayList<String> results=new ArrayList<String>();
		for(int i=0;i<dataset.dataList.size();i++) {
			results.add(findResult(dataset.dataList.get(i),dataset.labels,node)); 
		}
		return results;
	}
	
	
	
	public String predict(ArrayList<String> sample,ArrayList<String> labels) {
		return  findResult(sample,labels,node);
	}
	
	
	public boolean score(ArrayList<String> realResults,ArrayList<String> precResults) {
		double rightNum=0;
		double allNum=realResults.size();
		
		if(realResults.size()!=precResults.size()) {
			return false;
		}
		for(int i=0;i<realResults.size();i++) {
			System.out.println(realResults.get(i)+"----------"+precResults.get(i));
			if(realResults.get(i).equals(precResults.get(i)) ) {
				rightNum++;
			}		
		}
		
		System.out.println("准确率:"+rightNum/allNum);
		return true;
	}
	public String findResult(ArrayList<String> sample,ArrayList<String> labels,TreeNode treenode) {

		//获取顶点代表的特征的下标
		int index=labels.indexOf(treenode.getsname());		
		switch(treenode.type) {
			case 0:
				int i;
				for(i=0;i<treenode.childNodes.size();i++) {
					//如果都不相等呢
					if(Double.parseDouble(sample.get(index))==Double.parseDouble(treenode.edges.get(i).number) ) {						
						break;
					}
				}
				
				if(i!=treenode.childNodes.size()) {
					return findResult(sample,labels,treenode.childNodes.get(i));
				}else {
					return "无法判断";
				}
			case 1:
				if(Double.parseDouble(sample.get(index))<=Double.parseDouble(treenode.edges.get(0).number) ) {
					return findResult(sample,labels,treenode.childNodes.get(0));
				}else{
					return findResult(sample,labels,treenode.childNodes.get(1));
				}
			case 2:
				return treenode.getsname();
		}
		return null;
			
	}
	public void put(TreeNode node) {
		System.out.println("结点："+node.getsname()+"\n");
		for(int i=0;i<node.edges.size();i++) {
				System.out.println(node.getsname()+"的标签属性:"+node.edges.get(i));
			if(node.childNodes.get(i).childNodes.isEmpty()==true) {
				System.out.println("叶子结点："+node.childNodes.get(i).getsname());
			}
			else {
				put(node.childNodes.get(i));
			}
		}	
	}
	
	public TreeNode creattree(DataSet dat) {
		if(dat.labels.size()==1) {
			TreeNode node=new TreeNode(majorityCnt(dat),2);	
			return node;
		}
		
		if(isPure(dat)) {
			int classIndex=dat.labels.size()-1;
			TreeNode node=new TreeNode(dat.dataList.get(0).get(classIndex),2);	
			return node;
		}
		
		Map<String, String> featMap=chooseBestFeatureToSplit(dat);
		
		if(featMap.get("featureIndex").equals("-1")) {
			TreeNode node=new TreeNode(majorityCnt(dat),2);	
			return node;
		}
		int index=Integer.parseInt(featMap.get("featureIndex"));
		int type=dat.featProperties.get(index);
		
		TreeNode treeNode=new TreeNode(dat.getLabels().get(index),type);	

		if(type==1) {  //连续型变量
			Edge edge1=new Edge("<=",featMap.get("partitionValue"));
			Edge edge2=new Edge(">",featMap.get("partitionValue"));
			treeNode.edges.add(edge1);
			treeNode.edges.add(edge2);
			//构建左子树
			DataSet dataset=splitDataSet(dat, index,"<=", featMap.get("partitionValue"));
			treeNode.childNodes.add(creattree(dataset));
			//构建右子树
			DataSet dataset1=splitDataSet(dat, index,">", featMap.get("partitionValue"));
			treeNode.childNodes.add(creattree(dataset1));
		}else {		//离散型变量
			//获取该离散型变量的所有取值
			ArrayList<String> set=new ArrayList<String>();
			set.add(dat.getDataSet().get(0).get(index));
			for(int i=1;i<dat.getDataSet().size();i++) {
				if(set.contains(dat.getDataSet().get(i).get(index))==false) {
					set.add(dat.getDataSet().get(i).get(index));
				}
			}
			
			for(int i=0;i<set.size();i++) {
				//建边
				Edge edge=new Edge(set.get(i));
				treeNode.edges.add(edge);	
				//建子节点
				DataSet dataset=splitDataSet(dat, index, set.get(i)); 
				treeNode.childNodes.add(creattree(dataset.remove(index)));
			}
		}

		return treeNode;				
	}
	
	

	/**
	 * 方法说明：
	 * @param dataset
	 * @return  连续变量返回 "featureIndex"--->特征下标 和 "partitionValue"---->划分值
	 */
	private Map<String, String> chooseBestFeatureToSplit(DataSet dataset) {
		 	int featNum=dataset.labels.size()-1;
		 	int size=dataset.dataList.size();
		 	double baseEnt= calcShannonEnt(dataset);
		 	int bestFeatureIndex=-1;
		 	double bestInfoGain=0.0;
		 	double partitionValue=0;
		 	for(int i=0;i<featNum;i++) {
		 		double newEntropy=0;
		 		double partiValue=0;
		 		if(dataset.featProperties.get(i)==1) {  //连续型变量	
		 			
		 			ArrayList<String> set=new ArrayList<String>();
		 			set.add(dataset.getDataSet().get(0).get(i));
		 			for(int k=1;k<dataset.getDataSet().size();k++) {
		 				if(set.contains(dataset.getDataSet().get(k).get(i))==false) {
		 					set.add(dataset.getDataSet().get(k).get(i));
		 				}
		 			}
		 			
		 			ArrayList<Double> values=new ArrayList<Double>();
		 			for(int j=0;j<set.size();j++) {
		 				values.add(Double.parseDouble( set.get(j)));
		 			}		 			
		 			values.sort(Comparator.naturalOrder());
		 			
		 			//找到最好的切分点
		 			double minEntropy=Double.MAX_VALUE;
		 			double parValue=0;
		 			for(int k=0;k<values.size()-1;k++) {
		 				 double partValue=( values.get(k)+ values.get(k+1))/2;
		 				 DataSet dat1=splitDataSet(dataset,i,"<=", String.valueOf(partValue));
		 				 DataSet dat2=splitDataSet(dataset,i,">", String.valueOf(partValue));
		 				 int len1=dat1.dataList.size();
		 				 int len2=dat2.dataList.size();
		 				 double Entropy=((double)len1/size)* calcShannonEnt(dat1)+((double)len2/size)* calcShannonEnt(dat2);
		 				 if(Entropy<minEntropy) {
		 					 minEntropy=Entropy;
		 					 parValue=partValue;
		 				 }
		 			}	
		 			
		 			newEntropy=minEntropy;
		 			partiValue=parValue;
		 					 			
		 		}else {//离散型变量	
		 			//获取离散属性的值域
		 			ArrayList<String> set=new ArrayList<String>();
		 			set.add(dataset.getDataSet().get(0).get(i));
		 			for(int k=1;k<dataset.getDataSet().size();k++) {
		 				if(set.contains(dataset.getDataSet().get(k).get(i))==false) {
		 					set.add(dataset.getDataSet().get(k).get(i));
		 				}
		 			}
		 			
		 			double Entropy=0;
		 			for(int j=0;j<set.size();j++) {
		 				 DataSet dat=splitDataSet(dataset,i,set.get(j));
		 				Entropy=Entropy+( (double)dat.dataList.size()/dataset.dataList.size())* calcShannonEnt(dat);
		 			}
		 			newEntropy=Entropy;
		 		}
		 		
		 		double infoGain = baseEnt-newEntropy;
		 		if(infoGain > bestInfoGain) {
		 			bestInfoGain=infoGain;
		 			partitionValue=partiValue;
		 			bestFeatureIndex=i;
		 		}
//		 		System.out.println(i);
		 	}
		 	
		 	Map<String, String> map=new HashMap<String, String>();
		 	map.put("featureIndex", String.valueOf(bestFeatureIndex));
		 	map.put("partitionValue", String.valueOf(partitionValue));
		 	return map;
	}
	
	private double calcShannonEnt(DataSet dataSet) {
		int classIndex=dataSet.labels.size()-1;
		ArrayList<String> set=new ArrayList<String>();
		set.add(dataSet.getDataSet().get(0).get(classIndex));
		for(int i=1;i<dataSet.getDataSet().size();i++) {
				if(set.contains(dataSet.getDataSet().get(i).get(classIndex))==false) {
					set.add(dataSet.getDataSet().get(i).get(classIndex));
				}
		}
		
		int num=0;
		double Ent=0;
		for(int j=0;j<set.size();j++) {
			num=0;
			for(int i=0;i<dataSet.dataList.size();i++) {
				if(set.get(j).equals( dataSet.dataList.get(i).get(classIndex) )) {
					num++;
				}
			}		
			Ent=-((double)num/dataSet.dataList.size()) * ( Math.log( ((double)num/dataSet.dataList.size()) )/Math.log(2.0) )+Ent;
		}
		return Ent;		
	}
	
	
	private boolean isPure(DataSet dataSet) {
		// TODO Auto-generated method stub
		int classIndex=dataSet.labels.size()-1;
		ArrayList<String> set=new ArrayList<String>();
		set.add(dataSet.getDataSet().get(0).get(classIndex));
		for(int i=1;i<dataSet.getDataSet().size();i++) {
				if(set.contains(dataSet.getDataSet().get(i).get(classIndex))==false) {
					set.add(dataSet.getDataSet().get(i).get(classIndex));
				}
		}
		
		if(set.size()==1) {
			return true;
		}
		return false;
	}


	private String majorityCnt(DataSet dataSet) {

		int classIndex=dataSet.labels.size()-1;
		Map<String ,Integer> map=new HashMap<String ,Integer>();
		for(int j=0;j<dataSet.dataList.size();j++) {
			String kind=dataSet.dataList.get(j).get(classIndex);
			if(map.containsKey(kind)) {
				map.put(kind, map.get(kind)+1);
			}else {
				map.put(kind,1);
			}
		}
		 int length =map.size();
		 Collection<Integer> c = map.values();
	     Object[] obj = c.toArray();
	     Arrays.sort(obj);
	     int max=(int) obj[obj.length - 1];
	     for(Map.Entry<String, Integer> entry:map.entrySet()) {
	    	 if(max==entry.getValue()) {
	    		 return entry.getKey();
	    	 }
	     }
		return null;
	}
	
	private DataSet splitDataSet(DataSet dataSet, int axis,String LorR, String value) {
		 return dataSet.findSubDataset(axis,LorR, value);
	 }	 	 
	private DataSet splitDataSet(DataSet dataSet, int axis, String value) {
		 return dataSet.findSubDataset(axis, value);
	 }
	
	
	public static void main(String[] args) {
//		String path="F:/专业实训作业/软件缺陷数据集及相关说明材料/Datasets/AEEEM/csv/JDT.csv";

		String path0="data\\Lucene.csv";
		String path1="data\\PDE.csv";
		//DecisionTreeClassifier decClassifier=new DecisionTreeClassifier();
		
//		DataSet datTrain1=new DataSet(path);
		DataSet datTrain2=new DataSet(path0);
		DataSet datTest=new DataSet(path1);
//		datTrain1.add(datTrain2);
		/*
		decClassifier.fit(datTrain2);
		DecisionTreeClassifier.writeObjectToFile(decClassifier,"temp.dat");

		 */
		DecisionTreeClassifier decClassifier = (DecisionTreeClassifier) DecisionTreeClassifier.readObjectFromFile("temp.dat");
		decClassifier.score(datTest.getColumn(datTest.labels.size()-1),decClassifier.predict(datTest));
		
	}
}




class DataSet{
	public ArrayList<String> labels=new ArrayList<String>();
	public ArrayList<Integer> featProperties=new ArrayList<Integer>();               //特征属性 1为连续性变量 0为离散型变量
	public ArrayList<ArrayList<String>> dataList=new ArrayList<ArrayList<String>>();//数据集
	
	
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
}
