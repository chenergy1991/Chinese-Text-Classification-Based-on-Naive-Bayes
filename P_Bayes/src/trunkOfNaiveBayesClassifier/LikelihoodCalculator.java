package trunkOfNaiveBayesClassifier;
import trainer.TrainingDataManager;

/**
 * ������ʼ�����
 */

public class LikelihoodCalculator 
{
	private static TrainingDataManager tdm = new TrainingDataManager();
	// ��������
	private static double zoomFactor = 10.0f;

	/**
	 * ����һ���ʵ���Ȼ����ֵ
	 * @param word �����Ĵ�
	 * @param nameOfTheCategory �����ķ�����
	 * @return p(word | nameOfTheCategory)
	 */
	public static double calculateLikelihoodOfTheWord(String word,String nameOfTheCategory) 
	{
		// Ҫ���صĵ����ʵ���Ȼ����ֵ
		double ret = 0.0;
		// ����ѵ�������Ͽ����ڸ��������µİ�������Ч�ʡ���ѵ���ı�����Ŀ
		double amountOfFilesWhichContainKeyInTheCategory = tdm.getAmountOfFilesWhichContainKeyInTheCategory(nameOfTheCategory, word);
		// ����ѵ�������Ͽ����ڸ��������µ�ѵ���ı�����Ŀ
		double trainingFileAmountOfTheCategory = tdm.getTrainingFileAmountOfTheCategory(nameOfTheCategory);
		// ����ѵ�������Ͽ�������ı�����
		double trainingFilesAmountOfTrainingSet = tdm.getTrainingFilesAmountOfTrainingSet();
		// ��1������*0��
		ret = zoomFactor* (amountOfFilesWhichContainKeyInTheCategory + 1) / (trainingFileAmountOfTheCategory + trainingFilesAmountOfTrainingSet);
		System.out.println("һ���ʵ���Ȼ����ֵ" + ret);
		return ret;
	}
	/**
	 * ������Ч���������Ȼ����ֵ
	 * @param words ��������Ч������
	 * @param nameOfTheCategory �����ķ�����
	 * @return p(words | nameOfTheCategory)
	 */
	//���ݶ����Լ��裬�Ե����ʵ���Ȼ����ֵ�����۳˿ɵõ�������Ч���������Ȼ����ֵ��
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
