package pretreatment;
import java.io.IOException;
import jeasy.analysis.MMAnalyzer;

/**
* ���ķִ���
*/
public class ChineseSpliter 
{	
	/**
	* ���ü������ķִ������ķ����Ը������ı��������ķִ�
	* @param text ���ִʵ��ı�
	* @param splitToken ���ڷָ�ı��,��"|"
	* @return �ִʺ���ı�
	*/	
	public static String split(String text,String splitToken)
	{
		String result = null;
		//�����������ƥ������ķִ��㷨���൱�ڷִ����ȵ���0 
		MMAnalyzer analyzer = new MMAnalyzer();  
		try  	
        {	//�ִ�
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


