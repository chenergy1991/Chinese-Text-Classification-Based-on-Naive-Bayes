package pretreatment;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
/**
* 停用词处理器
*/
public class StopWordsHandler 
{
	static String[] stopWordsList = null;
	/**
	* 判别一个词语是不是停用词
	* @param word 词语
	* @return 词语是不是停用词
	*/	
	public static boolean isStopWord(String word)
	{
		if(stopWordsList == null)
		{
			System.out.print("初始化停用词表\n");
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
	* 停用词过滤方法
	* @param oldWords 未去除停用词的词语数组
	* @return 已去除停用词的词语数组
	*/

	public static String[] dropStopWords(String[] oldWords)
	{
		//对Vector要更进一步理解
		Vector<String> v = new Vector<String>();
		for(int i=0;i<oldWords.length;++i)
		{
			//不是停用词，则保留该词。
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
	* 通过读取文件保存的停用词来初始化停用词表。
	* @param filePath 文件路径
	* @return 存放停用词的数组
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
