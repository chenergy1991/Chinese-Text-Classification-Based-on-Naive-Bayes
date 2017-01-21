package pretreatment;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
/**
* ͣ�ôʴ�����
*/
public class StopWordsHandler 
{
	static String[] stopWordsList = null;
	/**
	* �б�һ�������ǲ���ͣ�ô�
	* @param word ����
	* @return �����ǲ���ͣ�ô�
	*/	
	public static boolean isStopWord(String word)
	{
		if(stopWordsList == null)
		{
			System.out.print("��ʼ��ͣ�ôʱ�\n");
			stopWordsList = getStopWordsListFromFile("stopWords.txt");
		}
//		String[] stopWordsList = getStopWordsListFromFile("stopWords.txt");
		for(int i=0;i<stopWordsList.length;++i)
		{
			if(word.equalsIgnoreCase(stopWordsList[i]))
				return true;
		}
		return false;
	}
	
	/**
	* ͣ�ôʹ��˷���
	* @param oldWords δȥ��ͣ�ôʵĴ�������
	* @return ��ȥ��ͣ�ôʵĴ�������
	*/

	public static String[] dropStopWords(String[] oldWords)
	{
		//��VectorҪ����һ�����
		Vector<String> v = new Vector<String>();
		for(int i=0;i<oldWords.length;++i)
		{
			//����ͣ�ôʣ������ôʡ�
			if(StopWordsHandler.isStopWord(oldWords[i])==false)
			{
				v.add(oldWords[i]);
			}
		}
		String[] newWords = new String[v.size()];
		v.toArray(newWords);
		return newWords;
	}
	
	/**
	* ͨ����ȡ�ļ������ͣ�ô�����ʼ��ͣ�ôʱ�
	* @param filePath �ļ�·��
	* @return ���ͣ�ôʵ�����
	*/
	
	public static String []  getStopWordsListFromFile(String filePath)
	{  
		ArrayList<String> list = new ArrayList<String>();  
		File file = new File(filePath);    
		BufferedReader reader = null;  
		try 
		{   
			reader = new BufferedReader(new FileReader(file));   
			String tempString = null;   
			while ((tempString = reader.readLine()) != null)
			{    
				list.add(tempString);   
			}   
			reader.close();  
		} 
		catch (IOException e) 
		{   
			e.printStackTrace();  
		} 
		finally 
		{   
			if (reader != null)
			{    
				try 
				{     
					reader.close();    
				} 
				catch (IOException e1) 
				{    
				}   
			} 
		}  
		String[] result = new String[list.size()];
		result = list.toArray(result);
		return result;
	}
}
