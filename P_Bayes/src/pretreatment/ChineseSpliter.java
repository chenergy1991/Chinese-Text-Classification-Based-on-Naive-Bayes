package pretreatment;
import java.io.IOException;
import jeasy.analysis.MMAnalyzer;

/**
* 中文分词器
*/
public class ChineseSpliter 
{	
	/**
	* 调用极易中文分词组件里的方法对给定的文本进行中文分词
	* @param text 待分词的文本
	* @param splitToken 用于分割的标记,如"|"
	* @return 分词后的文本
	*/	
	public static String split(String text,String splitToken)
	{
		String result = null;
		//采用正向最大匹配的中文分词算法，相当于分词粒度等于0 
		MMAnalyzer analyzer = new MMAnalyzer();  
		try  	
        {	//分词
			result = analyzer.segment(text, splitToken);	
		}  	
        catch (IOException e)  	
        { 	
        	e.printStackTrace(); 	
        } 
		System.out.println(result);	
        return result;
	}	
}


