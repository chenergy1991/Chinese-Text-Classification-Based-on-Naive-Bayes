package trainer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
* ѵ����������
*/

public class TrainingDataManager 
{
	//ѵ�����Ϸ��༯��
	private String[] trainingFileCategories;
	//ѵ�����ϴ���ļ�
	private File trainingTextDir;
	//ѵ�����ϴ��·��
	private static String defaultPath = "TrainingSet";
	//ѵ�����������Ĺ�����
	public TrainingDataManager() 
	{
		trainingTextDir = new File(defaultPath);
		//���ѵ�����ϵĴ��·�������ڣ����׳��쳣��
		if (!trainingTextDir.isDirectory()) 
		{
			throw new IllegalArgumentException("�Ҳ���ѵ�����Ͽ⣡ [" +defaultPath + "]");
		}
        //File.list()����һ���ַ������飬��Щ�ַ���ָ���˳���·������ʾ��Ŀ¼�е��ļ���Ŀ¼�� 
		this.trainingFileCategories = trainingTextDir.list();
	}
	
	
	/**
	* ������ѵ�����������������ɵ��ַ������顣���������ѵ�������Ͽ����Ŀ¼��
	* @return ѵ���ı����
	*/
	public String[] getTrainingCategories() 
	{
		return this.trainingFileCategories;
	}
	
	/**
	* ����ѵ���ı���𷵻��������µ�����ѵ���ı�·����full path��
	* @param nameOfTheCategory �����ķ���
	* @return ���������������ļ���·����full path��
	*/
	public String[] getFilesPath(String nameOfTheCategory) 
	{
		//getPath()���˳���·����ת��Ϊһ��·�����ַ�����
		File classDir = new File(trainingTextDir.getPath() +File.separator + nameOfTheCategory);
		//File.list()����һ���ַ������飬��Щ�ַ���ָ���˳���·������ʾ��Ŀ¼�е��ļ���Ŀ¼��
		String[] ret = classDir.list();
		for (int i = 0; i < ret.length; i++) 
		{
			//System.out.println(ret[i]);
			ret[i] = trainingTextDir.getPath() +File.separator + nameOfTheCategory +File.separator +ret[i];
			//System.out.println(traningTextDir.getPath());
			//System.out.println(File.separator);
			//System.out.println(classification);
			//System.out.println(ret[i]);
		}
		return ret;
	}

	/**
	* ����ָ��·�����ı��ļ�����
	* @param filePath �������ı��ļ�·��
	* @return �ı�����
	* @throws java.io.FileNotFoundException
	* @throws java.io.IOException
	*/
	public static String getText(String filePath) throws FileNotFoundException,IOException 
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
	* ����ѵ���ı��������е��ı���Ŀ
	* @return ѵ���ı��������е��ı���Ŀ
	*/
	public int getTrainingFilesAmountOfTrainingSet()
	{
		int ret = 0;
		for (int i = 0; i < trainingFileCategories.length; i++)
		{
			
			ret += getTrainingFileAmountOfTheCategory(trainingFileCategories[i]);
		}
		return ret;
	}

	/**
	* ����ѵ���ı������ڸ��������µ�ѵ���ı���Ŀ
	* @param nameOfTheCategory �����ķ�����
	* @return ѵ�������ڸ��������µ�ѵ���ı���Ŀ
	*/
	public int getTrainingFileAmountOfTheCategory(String nameOfTheCategory)
	{
		File categoryDir = new File(trainingTextDir.getPath() +File.separator +nameOfTheCategory);
		return categoryDir.list().length;
	}

	/**
	* ���ظ��������а�����Ч�ʵ�ѵ���ı�����Ŀ
	* @param nameOfTheCategory �����ķ�����
	* @param key ��������Ч��
	* @return ���������а�����Ч�ʵ�ѵ���ı�����Ŀ
	*/
	
	public int getAmountOfFilesWhichContainKeyInTheCategory(String nameOfTheCategory,String key) 
	{
		int ret = 0;
		try 
		{
			String[] filePath = getFilesPath(nameOfTheCategory);
			for (int j = 0; j < filePath.length; j++) 
			{
				String text = getText(filePath[j]);
				if (text.contains(key)) 
				{
					ret++;
				}
			}
		}
		catch (FileNotFoundException ex) 
		{
			Logger.getLogger(TrainingDataManager.class.getName()).log(Level.SEVERE, null,ex);
	
		} 
		catch (IOException ex)
		{
			Logger.getLogger(TrainingDataManager.class.getName()).log(Level.SEVERE, null,ex);
	
		}
		return ret;
	}
	
	/**
	* ����ѵ���ı��������е��ı���Ŀ
	* @return ѵ���ı��������е��ı���Ŀ
	 * @throws Exception 
	*/
	public void saveTrainingFilesAmountOfTrainingSet() throws Exception
	{
		int ret = getTrainingFilesAmountOfTrainingSet();
		TreeMap<String,Integer>map = new TreeMap<String,Integer>();
		map.put("���ݼ����ı�����",ret);
		ExcelUtil.writeMapToExcelFile("knowledge.xls",map);
	}
	
	
	
}