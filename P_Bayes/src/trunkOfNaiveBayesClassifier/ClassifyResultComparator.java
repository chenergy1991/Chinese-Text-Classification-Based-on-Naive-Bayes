package trunkOfNaiveBayesClassifier;
import java.util.Comparator;


import pojo.ClassifyResult;

/**
* 分类结果排序器
*/

public class ClassifyResultComparator implements Comparator 
{
	/**
	* 比较两个ClassifyResult概率属性的大小
	* @param Object 类对象 
	* @param Object 类对象 
	* @return 1|0|-1
	*/
	
	public int compare(Object arg0, Object arg1) 
	{
		ClassifyResult c1=(ClassifyResult)arg0;
		ClassifyResult c2=(ClassifyResult)arg1;
		if(c1.getProbability()<c2.getProbability())
			return 1;
		else if(c1.getProbability()>c2.getProbability())
			return -1;
		else 
			return 0;		
	}
}
