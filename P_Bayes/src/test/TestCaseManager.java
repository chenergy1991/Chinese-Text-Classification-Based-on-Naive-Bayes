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
* 测试用例集管理器
*/

public class TestCaseManager 
{
	//测试用例集分类集合
	private String[] testCaseCategories;
	//总的测试用例集存放目录
	private File testCaseDir;
	//总的测试用例集的存放路径
	private static String defaultPath = "TestCaseSet";
	//测试用例库管理器的构造器
	public TestCaseManager() 
	{
		testCaseDir = new File(defaultPath);
		//如果训练语料的存放路径不存在，则抛出异常。
		if (!testCaseDir.isDirectory()) 
		{
			throw new IllegalArgumentException("找不到测试用例库！ [" +defaultPath + "]");
		}
        //File.list()返回一个字符串数组，这些字符串指定此抽象路径名表示的目录中的文件和目录。 
		this.testCaseCategories = testCaseDir.list();
	}
		
	/**
	* 返回由测试用例库里所有类别名组成的字符串数组。这个类别就是测试用例库里的目录名
	* @return 测试用例类别
	*/
	public String[] getTestCaseCategories() 
	{
		return this.testCaseCategories;
	}
	
	/**
	* 根据测试用例类别返回这个类别下的所有测试用例路径（full path）
	* @param nameOfTheCategory 给定的分类
	* @return 给定分类下所有文件的路径（full path）
	*/
	public String[] getTestCasePath(String nameOfTheCategory) 
	{
		//getPath()将此抽象路径名转换为一个路径名字符串。
		File classDir = new File(testCaseDir.getPath() +File.separator + nameOfTheCategory);
		//File.list()返回一个字符串数组，这些字符串指定此抽象路径名表示的目录中的文件和目录。
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
	* 返回指定路径的测试用例文本文件内容
	* @param filePath 给定的文本文件路径
	* @return 文本内容
	* @throws java.io.FileNotFoundException
	* @throws java.io.IOException
	*/
	public static String getTestCase(String filePath) throws FileNotFoundException,IOException 
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
	* 返回测试用例集中所有的测试文本数目
	* @return 训练文本集中所有的文本数目
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
	* 返回测试用例集中在给定分类下的测试用例数目
	* @param nameOfTheCategory 给定的分类名
	* @return 训练集中在给定分类下的训练文本数目
	* 
	*/
	public int getTestCaseAmountOfTheCategory(String nameOfTheCategory)
	{
		File categoryDir = new File(testCaseDir.getPath() +File.separator +nameOfTheCategory);
		return categoryDir.list().length;
	}

	/**
	* 返回微平均准确率
	* @param nameOfTheCategory 给定的分类名
	 * @throws Exception 
	*/
	public void getTheCorrectRateOfClassify(String nameOfTheCategory) throws Exception 
	{
		//程序开始执行时间
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
				//进行分类
				String result = classifier.classify(text);
				System.out.print("分类结果可能是：" + result);
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
		
		System.out.println("分类正确次数"+ret);
		System.out.println("测试文章份数"+filePathLength);
		System.out.println("分类成功率"+correctRate);
		//程序结束时间
		Date endTime = new Date();
		long between = endTime.getTime()-beginTime.getTime(); 
		System.out.print("执行程序耗时："+between+"毫秒");
	}
}