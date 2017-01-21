package trunkOfNaiveBayesClassifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pojo.ClassifyResult;
import pretreatment.ChineseSpliter;
import pretreatment.StopWordsHandler;
import trainer.TrainingDataManager;

/**
* ���ر�Ҷ˹��������������
*/
public class CoreOfNaiveBayesClassifier 
{
	//Ϊ����ʾ������ӵ���������
	static StringBuffer sb;
	public static String tempString;

	//ѵ����������
	private TrainingDataManager tdm;

	/**
	* Ĭ�ϵĹ��췽������ʼ��ѵ����
	*/
	public CoreOfNaiveBayesClassifier() 
	{
		tdm =new TrainingDataManager();
	}

	/**
	* �Ը������ı����з���ķ���
	* @param text �������ı�
	* @return ������
	 * @throws Exception 
	*/
	public String classify(String text) throws Exception
	{
		//����ı��е���Ч�ʻ������
		String[] effectiveWords = null;		
		//���ķִʴ���(�ִʺ������ܻ�������ͣ�ô�)
		//String split 
		effectiveWords= ChineseSpliter.split(text,"/").split("/");
		//��ӡ�ִʽ��
		sb = new StringBuffer();
		sb.append("���ķִʽ����\n");
		for(int i = 0 ;i < effectiveWords.length;i++)
		{			
			sb.append(effectiveWords[i]+" / ");
			System.out.println(effectiveWords[i]);
			tempString = sb.toString();
		}		
		System.out.println("ȥ��ͣ�ô�");
		//ȥ��ͣ�ôʣ���߳��������ٶȲ������������ţ�
		effectiveWords = StopWordsHandler.dropStopWords(effectiveWords);
		sb.append("\nȥ��ͣ�ôʽ����\n");
		for(String i : effectiveWords)
		{
			System.out.println(i);
			sb.append(i+" / ");
			tempString = sb.toString();
		}
		//����ѵ���ı������Щ������ѵ�������Ͽ����Ŀ¼����
		String[] nameOfCategories = tdm.getTrainingCategories();
//		for(int i =0;i<nameOfCategories.length;i++)
//		{
//			System.out.println(nameOfCategories[i]);	
//		}
		//�����ı�����ĳ���ĸ��ʣ�probability��terms�ı�����className���ĸ��ʡ�
		double probabilityOfTheCategory = 0.0;
		String nameOfTheCategory = "" ;
		
		//ʵ����һ��������ŷ����������ArrayList
		List<ClassifyResult> crs = new ArrayList<ClassifyResult>();
		for (int i = 0; i < nameOfCategories.length; i++) 
		{
			//��i�����������
			nameOfTheCategory = nameOfCategories[i];
			//����������ı�����terms����ΪclassName�ķ����е���������
			probabilityOfTheCategory = PosteriorProbabilityCalculator.calculatePosteriorProbability(effectiveWords, nameOfTheCategory);			
			//ʵ����һ���������Ķ���cR
			ClassifyResult cR = new ClassifyResult();
			//ΪcR�ġ�������𡱸�ֵ
			cR.setCategory(nameOfTheCategory);
			//ΪcR�ġ��������ʡ���ֵ.p(nameOfTheCategory|text)
			cR.setProbability(probabilityOfTheCategory);
			System.out.println(nameOfTheCategory + "��" + probabilityOfTheCategory);
			//System.out.println(cR.getClassification() + "��" + cR.getProbability());
			//�ѷ���������List			
			crs.add(cR);
		}
		//ʵ����һ���������Ƚ���
		ClassifyResultComparator comparator=new ClassifyResultComparator();
		//ʹ�÷�������������crs��������
		Collections.sort(crs, comparator);			   
//		  for (int i=0;i<crs.size();i++)
//		  {
//			  ClassifyResult cr=(ClassifyResult)crs.get(i);
//		      //System.out.println(cr.getClassification()  +","+cr.getProbability()); 
//		  }
//�������п��ܵ�ǰ����������
//		 return crs.get(0).getCategory()+","+crs.get(1).getCategory();
        //�������п��ܵ������
		return crs.get(0).getCategory();
	}
}
