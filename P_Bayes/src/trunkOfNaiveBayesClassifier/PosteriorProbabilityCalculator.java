package trunkOfNaiveBayesClassifier;

import java.io.FileNotFoundException;
import java.io.IOException;


/**
* 似然函数值计算器
*/

public class PosteriorProbabilityCalculator
{
	/**
	* 计算后验概率p(words|nameOfTheCategory)*p(nameOfTheCategory)
	* @param words 给定的有效词数组(可用于表示整篇文章的向量)
	* @param nameOfTheCategory 给定的类别
	* @return 后验概率
	 * @throws Exception 
	*/
	//一个向量由很多个分量组成
	static double calculatePosteriorProbability(String[] words, String nameOfTheCategory) throws Exception
	{
		double ret = 1.0;
		ret *= PriorProbabilityCalculator.calculatePriorProbability(nameOfTheCategory);
		ret *= LikelihoodCalculator.calculateLikelihood(words, nameOfTheCategory);
		return ret;
	}
}
