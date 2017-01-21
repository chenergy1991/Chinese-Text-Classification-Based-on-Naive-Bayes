package trunkOfNaiveBayesClassifier;
import trainer.TrainingDataManager;

/**
 * 后验概率计算器
 */

public class LikelihoodCalculator 
{
	private static TrainingDataManager tdm = new TrainingDataManager();
	// 调整因子
	private static double zoomFactor = 10.0f;

	/**
	 * 计算一个词的似然函数值
	 * @param word 给定的词
	 * @param nameOfTheCategory 给定的分类名
	 * @return p(word | nameOfTheCategory)
	 */
	public static double calculateLikelihoodOfTheWord(String word,String nameOfTheCategory) 
	{
		// 要返回的单个词的似然函数值
		double ret = 0.0;
		// 返回训练集语料库中在给定分类下的包含“有效词”的训练文本的数目
		double amountOfFilesWhichContainKeyInTheCategory = tdm.getAmountOfFilesWhichContainKeyInTheCategory(nameOfTheCategory, word);
		// 返回训练集语料库中在给定分类下的训练文本的数目
		double trainingFileAmountOfTheCategory = tdm.getTrainingFileAmountOfTheCategory(nameOfTheCategory);
		// 返回训练集语料库的所有文本个数
		double trainingFilesAmountOfTrainingSet = tdm.getTrainingFilesAmountOfTrainingSet();
		// 加1，避免*0。
		ret = zoomFactor* (amountOfFilesWhichContainKeyInTheCategory + 1) / (trainingFileAmountOfTheCategory + trainingFilesAmountOfTrainingSet);
		System.out.println("一个词的似然函数值" + ret);
		return ret;
	}
	/**
	 * 计算有效词数组的似然函数值
	 * @param words 给定的有效词数组
	 * @param nameOfTheCategory 给定的分类名
	 * @return p(words | nameOfTheCategory)
	 */
	//依据独立性假设，对单个词的似然函数值进行累乘可得到整个有效词数组的似然函数值。
	static double calculateLikelihood(String[] words, String nameOfTheCategory) 
	{
		double ret = 1.0F;
		for (int i = 0; i < words.length; i++) 
		{
			String word = words[i];
			ret *= calculateLikelihoodOfTheWord(word, nameOfTheCategory);
		}
		return ret;
	}
}
