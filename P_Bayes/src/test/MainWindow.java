package test;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import jeasy.analysis.MMAnalyzer;
import pretreatment.DictionaryManager;
import trainer.TrainingDataManager;
import trunkOfNaiveBayesClassifier.CoreOfNaiveBayesClassifier;

public class MainWindow extends JFrame implements ActionListener
{
	// 测试文本文本框
	JTextArea inputArea;
	// 提示用户输入文本标签
	JLabel labelInputHint;
	// 开始分类按钮
	JButton btnClassify;
	// 开始训练数据按钮
	JButton btnTrain;
	// 往词库添加词汇按钮
	JButton btnAdd;
	// 往词库删除词汇按钮
	JButton btnDel;
	// 分类结果文本标签
	JLabel labelClassifyResult;

	public MainWindow() {
		super("朴素贝叶斯分类器演示窗体");
		this.setBounds(400, 400, 470, 500);
		this.setVisible(true);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		// JLabel-输入提示
		labelInputHint = new JLabel("输入待分类文本");
		labelInputHint.setBounds(30, 10, 400, 30);
		this.add(labelInputHint);
		// JTextArea-测试文本输入框
		inputArea = new JTextArea();
		inputArea.setBounds(30, 40, 400, 200);
		Font x = new Font("Serif",0,20);
		inputArea.setFont(x);
		// 激活自动换行功能
		inputArea.setLineWrap(true);
		this.add(inputArea);
		// JLabel-分类结果标签
		labelClassifyResult = new JLabel();
		labelClassifyResult.setBounds(30, 250, 400, 30);
		labelClassifyResult.setFont(new java.awt.Font("Dialog", 1, 25));
		labelClassifyResult.setText("分类结果可能是：");
		this.add(labelClassifyResult);

		// JButton-分类按钮
		btnClassify = new JButton("对文本进行分类");
		btnClassify.setBounds(30, 310, 400, 30);
		this.add(btnClassify);
		// JButton-训练数据按钮
		btnTrain = new JButton("开始训练数据");
		btnTrain.setBounds(30, 350, 400, 30);
		this.add(btnTrain);
		// JButton-增加词汇按钮
		btnAdd = new JButton("依据wannaAdd.txt文件往字典增加词汇");
		btnAdd.setBounds(30, 390, 400, 30);
		this.add(btnAdd);
		// JButton-删除词汇按钮
		btnDel = new JButton("依据wannaDelete.txt文件从字典删除词汇");
		btnDel.setBounds(30, 430, 400, 30);
		this.add(btnDel);
		// 注册监听
		btnAdd.addActionListener(this);
		btnTrain.addActionListener(this);
		btnDel.addActionListener(this);
		btnClassify.addActionListener(this);
		btnTrain.addActionListener(this);
		this.setResizable(false);
		this.validate();
		this.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		if(arg0.getSource() == btnAdd)
		{
			System.out.println("词库中原有词汇个数：" + MMAnalyzer.size());
			System.out.print("依据wannaAdd.txt文件增加新词");			
			DictionaryManager.addMyWordsToDictionary("wannaAdd.txt");
			System.out.println("词库中现有词汇个数：" + MMAnalyzer.size());
		}
		if(arg0.getSource() == btnTrain)
		{
		}		
		else if(arg0.getSource() == btnDel)
		{
			System.out.println("词库中原有词汇个数：" + MMAnalyzer.size());
			System.out.print("依据wannaDelete.txt文件删除词汇");
			DictionaryManager.removeMyWordsFromDictionary("wannaDelete.txt");
			System.out.println("词库中现有词汇个数：" + MMAnalyzer.size());
		}
		else if(arg0.getSource()==btnClassify)
		{
			//获取分类开始时间 
			long startTime=System.currentTimeMillis();
			System.out.print("开始分类");
			System.out.println(inputArea.getText());
			String text = inputArea.getText();
			CoreOfNaiveBayesClassifier classifier = new CoreOfNaiveBayesClassifier();
			//进行分类
			String result = null;
			try {
				result = classifier.classify(text);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			labelClassifyResult.setText("分类结果可能是：" + result);
			inputArea.setText("");
			inputArea.setText(CoreOfNaiveBayesClassifier.tempString);
			//获取分类结束时间   
			long endTime=System.currentTimeMillis();
	    	System.out.println("程序运行时间： "+(endTime-startTime)+"ms");			
		}
	}
}
