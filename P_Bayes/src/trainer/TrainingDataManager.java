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
* 训练集管理器
*/

public class TrainingDataManager 
{
	//训练语料分类集合
	private String[] trainingFileCategories;
	//训练语料存放文件
	private File trainingTextDir;
	//训练语料存放路径
	private static String defaultPath = "TrainingSet";
	//训练集管理器的构造器
	public TrainingDataManager() 
	{
		trainingTextDir = new File(defaultPath);
		//如果训练语料的存放路径不存在，则抛出异常。
		if (!trainingTextDir.isDirectory()) 
		{
			throw new IllegalArgumentException("找不到训练语料库！ [" +defaultPath + "]");
		}
        //File.list()返回一个字符串数组，这些字符串指定此抽象路径名表示的目录中的文件和目录。 
		this.trainingFileCategories = trainingTextDir.list();
	}
	
	
	/**
	* 返回由训练集的所有类别名组成的字符串数组。这个类别就是训练集语料库里的目录名
	* @return 训练文本类别
	*/
	public String[] getTrainingCategories() 
	{
		return this.trainingFileCategories;
	}
	
	/**
	* 根据训练文本类别返回这个类别下的所有训练文本路径（full path）
	* @param nameOfTheCategory 给定的分类
	* @return 给定分类下所有文件的路径（full path）
	*/
	public String[] getFilesPath(String nameOfTheCategory) 
	{
		//getPath()将此抽象路径名转换为一个路径名字符串。
		File classDir = new File(trainingTextDir.getPath() +File.separator + nameOfTheCategory);
		//File.list()返回一个字符串数组，这些字符串指定此抽象路径名表示的目录中的文件和目录。
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
	* 返回指定路径的文本文件内容
	* @param filePath 给定的文本文件路径
	* @return 文本内容
	* @throws java.io.FileNotFoundException
	* @throws java.io.IOException
	*/
	public static String getText(String filePath) throws FileNotFoundException,IOException 
	{
		InputStreamReader isReader =new InputStreamReader(new FileInputStream(filePath),"GBK");
		BufferedReader reader = new BufferedReader(isReader);
		String aline;
		StringBuilder sb = new StringBuilder();
	    //读取一个文本行:readLine()
		while ((aline = reader.readLine()) != null)
		{
			sb.append(aline + " ");
		}
		isReader.close();
		reader.close();
		return sb.toString();
	}

	/**
	* 返回训练文本集中所有的文本数目
	* @return 训练文本集中所有的文本数目
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
	* 返回训练文本集中在给定分类下的训练文本数目
	* @param nameOfTheCategory 给定的分类名
	* @return 训练集中在给定分类下的训练文本数目
	*/
	public int getTrainingFileAmountOfTheCategory(String nameOfTheCategory)
	{
		File categoryDir = new File(trainingTextDir.getPath() +File.separator +nameOfTheCategory);
		return categoryDir.list().length;
	}

	/**
	* 返回给定分类中包含有效词的训练文本的数目
	* @param nameOfTheCategory 给定的分类名
	* @param key 给定的有效词
	* @return 给定分类中包含有效词的训练文本的数目
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
	* 返回训练文本集中所有的文本数目
	* @return 训练文本集中所有的文本数目
	 * @throws Exception 
	*/
	public void saveTrainingFilesAmountOfTrainingSet() throws Exception
	{
		int ret = getTrainingFilesAmountOfTrainingSet();
		TreeMap<String,Integer>map = new TreeMap<String,Integer>();
		map.put("数据集的文本总数",ret);
		ExcelUtil.writeMapToExcelFile("knowledge.xls",map);
	}
	
	
	
}