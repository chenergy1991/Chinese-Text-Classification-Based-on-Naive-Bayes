package pretreatment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import jeasy.analysis.MMAnalyzer;

public class DictionaryManager 
{
	/**
	* 依据文件增加词语到极易中文分词组件的词库，以组建个性化词库
	* @param filePath 文件路径
	*/
	public static void  addMyWordsToDictionary(String filePath)
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
		for(String newWord : result)
		{
			MMAnalyzer.addWord(newWord);
		}
	}
	/**
	* 依据文件删除当前在极易中文分词组件里的词语，以组建个性化词库
	* @param filePath 文件路径
	*/
	public static void  removeMyWordsFromDictionary(String filePath)
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
		for(String i : result)
		{
			MMAnalyzer.removeWord(i);
		}
	}
}
