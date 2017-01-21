package trunkOfNaiveBayesClassifier;

import java.io.FileNotFoundException;
import java.io.IOException;


/**
* ��Ȼ����ֵ������
*/

public class PosteriorProbabilityCalculator
{
	/**
	* ����������p(words|nameOfTheCategory)*p(nameOfTheCategory)
	* @param words ��������Ч������(�����ڱ�ʾ��ƪ���µ�����)
	* @param nameOfTheCategory ���������
	* @return �������
	 * @throws Exception 
	*/
	//һ�������ɺܶ���������
	static double calculatePosteriorProbability(String[] words, String nameOfTheCategory) throws Exception
	{
		double ret = 1.0;
		ret *= PriorProbabilityCalculator.calculatePriorProbability(nameOfTheCategory);
		ret *= LikelihoodCalculator.calculateLikelihood(words, nameOfTheCategory);
		return ret;
	}
}
