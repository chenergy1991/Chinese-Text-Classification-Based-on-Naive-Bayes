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
	* �����ļ����Ӵ��ﵽ�������ķִ�����Ĵʿ⣬���齨���Ի��ʿ�
	* @param filePath �ļ�·��
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
	* �����ļ�ɾ����ǰ�ڼ������ķִ������Ĵ�����齨���Ի��ʿ�
	* @param filePath �ļ�·��
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
