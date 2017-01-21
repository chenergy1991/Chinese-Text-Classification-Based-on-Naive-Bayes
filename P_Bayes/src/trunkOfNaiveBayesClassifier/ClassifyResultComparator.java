package trunkOfNaiveBayesClassifier;
import java.util.Comparator;


import pojo.ClassifyResult;

/**
* ������������
*/

public class ClassifyResultComparator implements Comparator 
{
	/**
	* �Ƚ�����ClassifyResult�������ԵĴ�С
	* @param Object ����� 
	* @param Object ����� 
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
