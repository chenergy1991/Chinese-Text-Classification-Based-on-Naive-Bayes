package pojo;
/**
* 分类结果pojo
*/

//简单Java对象
public class ClassifyResult 
{	//分类的概率
	private double probability;
	//所属类别
	private String category;
	
	//getter and setter
	public double getProbability() {
		return probability;
	}	
	public void setProbability(double probability) {
		this.probability = probability;
	}
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	//分类结果的构造方法
	public ClassifyResult()
	{
		this.probability = 0;
		this.category = null;
	}
}
