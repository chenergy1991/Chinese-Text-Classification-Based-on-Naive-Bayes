package pojo;
/**
* ������pojo
*/

//��Java����
public class ClassifyResult 
{	//����ĸ���
	private double probability;
	//�������
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
	
	//�������Ĺ��췽��
	public ClassifyResult()
	{
		this.probability = 0;
		this.category = null;
	}
}
