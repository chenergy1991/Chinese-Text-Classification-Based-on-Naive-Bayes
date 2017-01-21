package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import trunkOfNaiveBayesClassifier.CoreOfNaiveBayesClassifier;

/**
* ����������������
*/

public class TestCaseManager 
{
	//�������������༯��
	private String[] testCaseCategories;
	//�ܵĲ������������Ŀ¼
	private File testCaseDir;
	//�ܵĲ����������Ĵ��·��
	private static String defaultPath = "TestCaseSet";
	//����������������Ĺ�����
	public TestCaseManager() 
	{
		testCaseDir = new File(defaultPath);
		//���ѵ�����ϵĴ��·�������ڣ����׳��쳣��
		if (!testCaseDir.isDirectory()) 
		{
			throw new IllegalArgumentException("�Ҳ������������⣡ [" +defaultPath + "]");
		}
        //File.list()����һ���ַ������飬��Щ�ַ���ָ���˳���·������ʾ��Ŀ¼�е��ļ���Ŀ¼�� 
		this.testCaseCategories = testCaseDir.list();
	}
		
	/**
	* �����ɲ����������������������ɵ��ַ������顣��������ǲ������������Ŀ¼��
	* @return �����������
	*/
	public String[] getTestCaseCategories() 
	{
		return this.testCaseCategories;
	}
	
	/**
	* ���ݲ���������𷵻��������µ����в�������·����full path��
	* @param nameOfTheCategory �����ķ���
	* @return ���������������ļ���·����full path��
	*/
	public String[] getTestCasePath(String nameOfTheCategory) 
	{
		//getPath()���˳���·����ת��Ϊһ��·�����ַ�����
		File classDir = new File(testCaseDir.getPath() +File.separator + nameOfTheCategory);
		//File.list()����һ���ַ������飬��Щ�ַ���ָ���˳���·������ʾ��Ŀ¼�е��ļ���Ŀ¼��
		String[] ret = classDir.list();
		for (int i = 0; i < ret.length; i++) 
		{
			//System.out.println(ret[i]);
			ret[i] = testCaseDir.getPath() +File.separator + nameOfTheCategory +File.separator +ret[i];
			//System.out.println(traningTextDir.getPath());
			//System.out.println(File.separator);
			//System.out.println(classification);
			//System.out.println(ret[i]);
		}
		return ret;
	}

	/**
	* ����ָ��·���Ĳ��������ı��ļ�����
	* @param filePath �������ı��ļ�·��
	* @return �ı�����
	* @throws java.io.FileNotFoundException
	* @throws java.io.IOException
	*/
	public static String getTestCase(String filePath) throws FileNotFoundException,IOException 
	{
		InputStreamReader isReader =new InputStreamReader(new FileInputStream(filePath),"GBK");
		BufferedReader reader = new BufferedReader(isReader);
		String aline;
		StringBuilder sb = new StringBuilder();
	    //��ȡһ���ı���:readLine()
		while ((aline = reader.readLine()) != null)
		{
			sb.append(aline + " ");
		}
		isReader.close();
		reader.close();
		return sb.toString();
	}

	/**
	* ���ز��������������еĲ����ı���Ŀ
	* @return ѵ���ı��������е��ı���Ŀ
	*/
	public int getTestsAmountOfTestCaseSet()
	{
		int ret = 0;
		for (int i = 0; i < testCaseCategories.length; i++)
		{
			ret += getTestCaseAmountOfTheCategory(testCaseCategories[i]);
		}
		return ret;
	}

	/**
	* ���ز������������ڸ��������µĲ���������Ŀ
	* @param nameOfTheCategory �����ķ�����
	* @return ѵ�������ڸ��������µ�ѵ���ı���Ŀ
	* 
	*/
	public int getTestCaseAmountOfTheCategory(String nameOfTheCategory)
	{
		File categoryDir = new File(testCaseDir.getPath() +File.separator +nameOfTheCategory);
		return categoryDir.list().length;
	}

	/**
	* ����΢ƽ��׼ȷ��
	* @param nameOfTheCategory �����ķ�����
	 * @throws Exception 
	*/
	public void getTheCorrectRateOfClassify(String nameOfTheCategory) throws Exception 
	{
		//����ʼִ��ʱ��
		Date beginTime = new Date();
		CoreOfNaiveBayesClassifier classifier = new CoreOfNaiveBayesClassifier();
		int filePathLength = 0;
		int ret = 0;
		try 
		{
			String[] filePath = getTestCasePath(nameOfTheCategory);
			filePathLength = filePath.length;
			for (int j = 0; j <filePathLength; j++) 
			{
				String text = getTestCase(filePath[j]);
				System.out.print(text);
				//���з���
				String result = classifier.classify(text);
				System.out.print("�����������ǣ�" + result);
				if(result.equals(nameOfTheCategory))
				{
						ret++;
				}
			}
		}
		catch (FileNotFoundException ex) 
		{
			Logger.getLogger(TestCaseManager.class.getName()).log(Level.SEVERE, null,ex);
	
		} 
		catch (IOException ex)
		{
			Logger.getLogger(TestCaseManager.class.getName()).log(Level.SEVERE, null,ex);
	
		}
		double correctRate = (double)ret / filePathLength;
		
		System.out.println("������ȷ����"+ret);
		System.out.println("�������·���"+filePathLength);
		System.out.println("����ɹ���"+correctRate);
		//�������ʱ��
		Date endTime = new Date();
		long between = endTime.getTime()-beginTime.getTime(); 
		System.out.print("ִ�г����ʱ��"+between+"����");
	}
}