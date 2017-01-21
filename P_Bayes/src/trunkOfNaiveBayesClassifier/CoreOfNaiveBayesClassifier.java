package trunkOfNaiveBayesClassifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pojo.ClassifyResult;
import pretreatment.ChineseSpliter;
import pretreatment.StopWordsHandler;
import trainer.TrainingDataManager;

/**
* 朴素贝叶斯分类器程序主干
*/
public class CoreOfNaiveBayesClassifier 
{
	//为了演示方便而加的两个变量
	static StringBuffer sb;
	public static String tempString;

	//训练集管理器
	private TrainingDataManager tdm;

	/**
	* 默认的构造方法，初始化训练集
	*/
	public CoreOfNaiveBayesClassifier() 
	{
		tdm =new TrainingDataManager();
	}

	/**
	* 对给定的文本进行分类的方法
	* @param text 给定的文本
	* @return 分类结果
	 * @throws Exception 
	*/
	public String classify(String text) throws Exception
	{
		//存放文本中的有效词汇的数组
		String[] effectiveWords = null;		
		//中文分词处理(分词后结果可能还包含有停用词)
		//String split 
		effectiveWords= ChineseSpliter.split(text,"/").split("/");
		//打印分词结果
		sb = new StringBuffer();
		sb.append("中文分词结果：\n");
		for(int i = 0 ;i < effectiveWords.length;i++)
		{			
			sb.append(effectiveWords[i]+" / ");
			System.out.println(effectiveWords[i]);
			tempString = sb.toString();
		}		
		System.out.println("去除停用词");
		//去掉停用词（提高程序运行速度并降低噪音干扰）
		effectiveWords = StopWordsHandler.dropStopWords(effectiveWords);
		sb.append("\n去除停用词结果：\n");
		for(String i : effectiveWords)
		{
			System.out.println(i);
			sb.append(i+" / ");
			tempString = sb.toString();
		}
		//返回训练文本类别（这些类别就是训练集语料库里的目录名）
		String[] nameOfCategories = tdm.getTrainingCategories();
//		for(int i =0;i<nameOfCategories.length;i++)
//		{
//			System.out.println(nameOfCategories[i]);	
//		}
		//待测文本属于某类别的概率，probability是terms文本属于className类别的概率。
		double probabilityOfTheCategory = 0.0;
		String nameOfTheCategory = "" ;
		
		//实例化一个用来存放分类结果对象的ArrayList
		List<ClassifyResult> crs = new ArrayList<ClassifyResult>();
		for (int i = 0; i < nameOfCategories.length; i++) 
		{
			//第i个分类的名称
			nameOfTheCategory = nameOfCategories[i];
			//计算给定的文本向量terms在名为className的分类中的条件概率
			probabilityOfTheCategory = PosteriorProbabilityCalculator.calculatePosteriorProbability(effectiveWords, nameOfTheCategory);			
			//实例化一个分类结果的对象cR
			ClassifyResult cR = new ClassifyResult();
			//为cR的“所处类别”赋值
			cR.setCategory(nameOfTheCategory);
			//为cR的“条件概率”赋值.p(nameOfTheCategory|text)
			cR.setProbability(probabilityOfTheCategory);
			System.out.println(nameOfTheCategory + "：" + probabilityOfTheCategory);
			//System.out.println(cR.getClassification() + "：" + cR.getProbability());
			//把分类结果存入List			
			crs.add(cR);
		}
		//实例化一个分类结果比较器
		ClassifyResultComparator comparator=new ClassifyResultComparator();
		//使用分类结果排序器对crs进行排序
		Collections.sort(crs, comparator);			   
//		  for (int i=0;i<crs.size();i++)
//		  {
//			  ClassifyResult cr=(ClassifyResult)crs.get(i);
//		      //System.out.println(cr.getClassification()  +","+cr.getProbability()); 
//		  }
//返回最有可能的前两个分类名
//		 return crs.get(0).getCategory()+","+crs.get(1).getCategory();
        //返回最有可能的类别名
		return crs.get(0).getCategory();
	}
}
