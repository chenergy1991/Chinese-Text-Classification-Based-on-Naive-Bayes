package trunkOfNaiveBayesClassifier;
import java.io.FileNotFoundException;
import java.io.IOException;

import trainer.*;

/**
* 先验概率计算器
*/

public class PriorProbabilityCalculator 
{
	private static TrainingDataManager tdm =new TrainingDataManager();

	/**
	* 先验概率
	* @param nameOfTheCategory 类别名
	* @return 从训练集中随机抽出一篇文章，该文章的类别名是nameOfTheCategory的概率。
	 * @throws Exception 
	*/
	//先验概率可以理解为对以往的经验的总结
	public static double calculatePriorProbability(String nameOfTheCategory) throws Exception
	{
		double ret = 0;
		//返回训练文本集中在给定分类下的训练文本数目
		double a = tdm.getTrainingFileAmountOfTheCategory(nameOfTheCategory);
		//返回训练文本集中所有的文本数目
		double b = tdm.getTrainingFilesAmountOfTrainingSet();
		ret = a / b;
		//tdm.saveTrainingFilesAmountOfTrainingSet();
		return ret;
	}
}
