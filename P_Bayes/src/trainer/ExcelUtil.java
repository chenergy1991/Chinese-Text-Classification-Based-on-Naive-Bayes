package trainer;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
public class ExcelUtil 
{
	 /**
	  * �� Xls �ļ�����
	  * @param:String place,�ļ�·��
	  */
	 @SuppressWarnings("unused")
	static void readExcelFile(String place) throws Exception
	{
		 // ��������������
		 File excelFile = new File(place);
		 Workbook workBook = Workbook.getWorkbook(excelFile);
		 // �õ����������еĹ��������
		 Sheet[] sheets = workBook.getSheets();
		 // ����������
		 for (int i = 0; i < sheets[0].getRows(); i++) 
		 {
			 // �õ������У���������е�����
			 Cell[] cells = sheets[0].getRow(i);
			 for (int j = 0; j < cells.length; j++) 
			 {
				 System.out.print(cells[j].getContents() + " ");
			 }
			 System.out.println();
		 }
	}
	
	
	/**
	  * ����xls�ļ���д Xls �ļ�
	  * @param String place,�ļ�·��
	  */
	 static void writeExcelFile(String place) throws Exception 
	 {
		// ��������д��book�ļ�����
		WritableWorkbook book = Workbook.createWorkbook(new File(place));
		// ��xls�ļ��д���һ��sheet,����Ϊ'qy',�ӵ�һ�п�ʼ����
		WritableSheet sheet = book.createSheet("qy", 0);
		int i = 0;
		while (i != 10) 
		{
			// ���õ�һ��д�������
			Label l = new Label(0, i, "��һ�У�" + i);
			// �����еĿ��
			sheet.setColumnView(0, 50);
			sheet.setColumnView(1, 100);
			sheet.addCell(l);
			// ���õڶ���д�������
			l = new Label(1, i, "�ڶ��У�" + i);
			sheet.addCell(l);
			i++;
		}
		// д��ecxel
		book.write();
		// һ��Ҫ�رգ�����д��
		book.close();
		System.out.println("����book�ɹ���д�����ݳɹ�......");
	 }	
	 
	 /**
	  * ����xls�ļ���д Xls �ļ�
	  * @param String place,�ļ�·��
	  */
	 @SuppressWarnings("unchecked")
	static void writeMapToExcelFile(String place,Map map) throws Exception 
	 {
		// ��������д��book�ļ�����
		WritableWorkbook book = Workbook.createWorkbook(new File(place));
		// ��xls�ļ��д���һ��sheet,����Ϊ'qy',�ӵ�һ�п�ʼ����
		WritableSheet sheet = book.createSheet("ѵ��������Ϣ", 0);
		int i = 0;
		Set<Map.Entry<String,Integer>>entrySet = map.entrySet();
		for(Map.Entry<String,Integer> entry:entrySet)
		{
			System.out.print(entry.getValue()+"\t"+entry.getKey()+"\n");
			// ���õ�һ��д�������
			Label labelCow = new Label(0, i,entry.getKey());
			sheet.addCell(labelCow);
			// ���õڶ���д�������
			labelCow = new Label(1, i,entry.getValue()+"");
			sheet.addCell(labelCow);
			i++;
			
		}
//		
//		while (i != 10) 
//		{
//			// ���õ�һ��д�������
//			Label l = new Label(0, i, "��һ�У�" + i);
//			// �����еĿ��
//			sheet.setColumnView(0, 50);
//			sheet.setColumnView(1, 100);
//			sheet.addCell(l);
//			// ���õڶ���д�������
//			l = new Label(1, i, "�ڶ��У�" + i);
//			sheet.addCell(l);
//			i++;
//		}
		// д��ecxel
		book.write();
		// һ��Ҫ�رգ�����д��
		book.close();
		System.out.println("����book�ɹ���д�����ݳɹ�......");
	 }	
	 
	 
	 
	 

	 /**
	  * ��д Xls �ļ�
	  */
	 @SuppressWarnings("unused")
	 private static void readAndWriteExcelFile(String place1,String place2) throws Exception 
	 {
		 Workbook workBook = null;
		 // ����Workbook���� ֻ��Workbook����
		 // ֱ�Ӵӱ����ļ�����Workbook
		 // ������������Workbook
		 InputStream inputStream = new FileInputStream(place1);
		 workBook = Workbook.getWorkbook(inputStream);
		 // Sheet(���������)����Excel������½ǵ�Sheet1,Sheet2,Sheet3���ڳ�����
		 // Sheet���±��Ǵ�0��ʼ��
		 // ��ȡ��һ��Sheet��
		 Sheet sheet = workBook.getSheet(0);
		 // ��ȡSheet������������������
		 int sheetColumns = sheet.getColumns();
		 // ��ȡSheet������������������
		 int sheetRows = sheet.getRows();
		 // ��ȡָ���µ�Ԫ��Ķ�������
		 for (int i = 0; i < sheetRows; i++) 
		 {
			 for (int j = 0; j < sheetColumns; j++) 
			 {
				 // ע���ڶ�ȡʱ i��j��λ�á�i������ j�����С�������ǰ ���ں�
				 Cell cell = sheet.getCell(j, i);
				 System.out.print(cell.getContents() + " ");
			 }
			 System.out.println();
		 }
		 // �����Ѿ�������Excel�����������µĿ�д���Excel������
		 WritableWorkbook wwb = Workbook.createWorkbook(new File(place2), workBook);
		 // ��ȡ��һ�Ź�����
		 WritableSheet ws = wwb.getSheet(0);
		 // ��ȡ��һ����Ԫ�����
		 WritableCell wc = ws.getWritableCell(0, 0);
		 // ���ϵ�Ԫ������ͣ�������Ӧ��ת��
		 if (wc.getType() == CellType.LABEL) 
		 {
			 Label l = (Label) wc;
			 l.setString("The value has been modified.");
		 }
		 // д��Excel����
		 wwb.write();
		 wwb.close();
		 // �������ʱ���رն��󣬷���ռ�õ��ڴ�ռ�
		 workBook.close();
	 }
 
 
 	public static void main(String[] args) throws Exception 
 	{
 		//����readExcelFile����������D:\excel\BOOK-read.xls��
 		readExcelFile("D:\\excel\\BOOK-read.xls");
 		//����writeExcelFile��������������д��D:\excel\BOOK-write.xls��
 		writeExcelFile("D:\\excel\\BOOK-write.xls");
 		//����readAndWriteExcelFile������
 		//����D:\excel\BOOK-read-write\\1.xls��д��"D:\\excel\\BOOK-read-write\\2.xls"
 		//readAndWriteExcelFile("D:\\excel\\BOOK-read-write\\1.xls","D:\\excel\\BOOK-read-write\\2.xls");
 	}
 }