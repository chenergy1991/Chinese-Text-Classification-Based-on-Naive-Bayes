package trunkOfNaiveBayesClassifier;
import java.io.FileNotFoundException;
import java.io.IOException;

import trainer.*;

/**
* ������ʼ�����
*/

public class PriorProbabilityCalculator 
{
	private static TrainingDataManager tdm =new TrainingDataManager();

	/**
	* �������
	* @param nameOfTheCategory �����
	* @return ��ѵ������������һƪ���£������µ��������nameOfTheCategory�ĸ��ʡ�
	 * @throws Exception 
	*/
	//������ʿ������Ϊ�������ľ�����ܽ�
	public static double calculatePriorProbability(String nameOfTheCategory) throws Exception
	{
		double ret = 0;
		//����ѵ���ı������ڸ��������µ�ѵ���ı���Ŀ
		double a = tdm.getTrainingFileAmountOfTheCategory(nameOfTheCategory);
		//����ѵ���ı��������е��ı���Ŀ
		double b = tdm.getTrainingFilesAmountOfTrainingSet();
		ret = a / b;
		//tdm.saveTrainingFilesAmountOfTrainingSet();
		return ret;
	}
}
