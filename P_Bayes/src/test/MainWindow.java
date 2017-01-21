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
	// �����ı��ı���
	JTextArea inputArea;
	// ��ʾ�û������ı���ǩ
	JLabel labelInputHint;
	// ��ʼ���ఴť
	JButton btnClassify;
	// ��ʼѵ�����ݰ�ť
	JButton btnTrain;
	// ���ʿ���Ӵʻ㰴ť
	JButton btnAdd;
	// ���ʿ�ɾ���ʻ㰴ť
	JButton btnDel;
	// �������ı���ǩ
	JLabel labelClassifyResult;

	public MainWindow() {
		super("���ر�Ҷ˹��������ʾ����");
		this.setBounds(400, 400, 470, 500);
		this.setVisible(true);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		// JLabel-������ʾ
		labelInputHint = new JLabel("����������ı�");
		labelInputHint.setBounds(30, 10, 400, 30);
		this.add(labelInputHint);
		// JTextArea-�����ı������
		inputArea = new JTextArea();
		inputArea.setBounds(30, 40, 400, 200);
		Font x = new Font("Serif",0,20);
		inputArea.setFont(x);
		// �����Զ����й���
		inputArea.setLineWrap(true);
		this.add(inputArea);
		// JLabel-��������ǩ
		labelClassifyResult = new JLabel();
		labelClassifyResult.setBounds(30, 250, 400, 30);
		labelClassifyResult.setFont(new java.awt.Font("Dialog", 1, 25));
		labelClassifyResult.setText("�����������ǣ�");
		this.add(labelClassifyResult);

		// JButton-���ఴť
		btnClassify = new JButton("���ı����з���");
		btnClassify.setBounds(30, 310, 400, 30);
		this.add(btnClassify);
		// JButton-ѵ�����ݰ�ť
		btnTrain = new JButton("��ʼѵ������");
		btnTrain.setBounds(30, 350, 400, 30);
		this.add(btnTrain);
		// JButton-���Ӵʻ㰴ť
		btnAdd = new JButton("����wannaAdd.txt�ļ����ֵ����Ӵʻ�");
		btnAdd.setBounds(30, 390, 400, 30);
		this.add(btnAdd);
		// JButton-ɾ���ʻ㰴ť
		btnDel = new JButton("����wannaDelete.txt�ļ����ֵ�ɾ���ʻ�");
		btnDel.setBounds(30, 430, 400, 30);
		this.add(btnDel);
		// ע�����
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
			System.out.println("�ʿ���ԭ�дʻ������" + MMAnalyzer.size());
			System.out.print("����wannaAdd.txt�ļ������´�");			
			DictionaryManager.addMyWordsToDictionary("wannaAdd.txt");
			System.out.println("�ʿ������дʻ������" + MMAnalyzer.size());
		}
		if(arg0.getSource() == btnTrain)
		{
		}		
		else if(arg0.getSource() == btnDel)
		{
			System.out.println("�ʿ���ԭ�дʻ������" + MMAnalyzer.size());
			System.out.print("����wannaDelete.txt�ļ�ɾ���ʻ�");
			DictionaryManager.removeMyWordsFromDictionary("wannaDelete.txt");
			System.out.println("�ʿ������дʻ������" + MMAnalyzer.size());
		}
		else if(arg0.getSource()==btnClassify)
		{
			//��ȡ���࿪ʼʱ�� 
			long startTime=System.currentTimeMillis();
			System.out.print("��ʼ����");
			System.out.println(inputArea.getText());
			String text = inputArea.getText();
			CoreOfNaiveBayesClassifier classifier = new CoreOfNaiveBayesClassifier();
			//���з���
			String result = null;
			try {
				result = classifier.classify(text);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			labelClassifyResult.setText("�����������ǣ�" + result);
			inputArea.setText("");
			inputArea.setText(CoreOfNaiveBayesClassifier.tempString);
			//��ȡ�������ʱ��   
			long endTime=System.currentTimeMillis();
	    	System.out.println("��������ʱ�䣺 "+(endTime-startTime)+"ms");			
		}
	}
}
