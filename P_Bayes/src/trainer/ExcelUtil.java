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
	  * 读 Xls 文件内容
	  * @param:String place,文件路径
	  */
	 @SuppressWarnings("unused")
	static void readExcelFile(String place) throws Exception
	{
		 // 创建工作簿对象
		 File excelFile = new File(place);
		 Workbook workBook = Workbook.getWorkbook(excelFile);
		 // 得到工作簿所有的工作表对象
		 Sheet[] sheets = workBook.getSheets();
		 // 遍历所有行
		 for (int i = 0; i < sheets[0].getRows(); i++) 
		 {
			 // 得到所有列，在输出列中的内容
			 Cell[] cells = sheets[0].getRow(i);
			 for (int j = 0; j < cells.length; j++) 
			 {
				 System.out.print(cells[j].getContents() + " ");
			 }
			 System.out.println();
		 }
	}
	
	
	/**
	  * 创建xls文件并写 Xls 文件
	  * @param String place,文件路径
	  */
	 static void writeExcelFile(String place) throws Exception 
	 {
		// 创建可以写的book文件对象
		WritableWorkbook book = Workbook.createWorkbook(new File(place));
		// 在xls文件中创建一个sheet,名称为'qy',从第一列开始插入
		WritableSheet sheet = book.createSheet("qy", 0);
		int i = 0;
		while (i != 10) 
		{
			// 设置第一列写入的内容
			Label l = new Label(0, i, "第一列：" + i);
			// 设置列的宽度
			sheet.setColumnView(0, 50);
			sheet.setColumnView(1, 100);
			sheet.addCell(l);
			// 设置第二列写入的内容
			l = new Label(1, i, "第二列：" + i);
			sheet.addCell(l);
			i++;
		}
		// 写入ecxel
		book.write();
		// 一定要关闭，否则不写入
		book.close();
		System.out.println("创建book成功，写入数据成功......");
	 }	
	 
	 /**
	  * 创建xls文件并写 Xls 文件
	  * @param String place,文件路径
	  */
	 @SuppressWarnings("unchecked")
	static void writeMapToExcelFile(String place,Map map) throws Exception 
	 {
		// 创建可以写的book文件对象
		WritableWorkbook book = Workbook.createWorkbook(new File(place));
		// 在xls文件中创建一个sheet,名称为'qy',从第一列开始插入
		WritableSheet sheet = book.createSheet("训练集总信息", 0);
		int i = 0;
		Set<Map.Entry<String,Integer>>entrySet = map.entrySet();
		for(Map.Entry<String,Integer> entry:entrySet)
		{
			System.out.print(entry.getValue()+"\t"+entry.getKey()+"\n");
			// 设置第一列写入的内容
			Label labelCow = new Label(0, i,entry.getKey());
			sheet.addCell(labelCow);
			// 设置第二列写入的内容
			labelCow = new Label(1, i,entry.getValue()+"");
			sheet.addCell(labelCow);
			i++;
			
		}
//		
//		while (i != 10) 
//		{
//			// 设置第一列写入的内容
//			Label l = new Label(0, i, "第一列：" + i);
//			// 设置列的宽度
//			sheet.setColumnView(0, 50);
//			sheet.setColumnView(1, 100);
//			sheet.addCell(l);
//			// 设置第二列写入的内容
//			l = new Label(1, i, "第二列：" + i);
//			sheet.addCell(l);
//			i++;
//		}
		// 写入ecxel
		book.write();
		// 一定要关闭，否则不写入
		book.close();
		System.out.println("创建book成功，写入数据成功......");
	 }	
	 
	 
	 
	 

	 /**
	  * 读写 Xls 文件
	  */
	 @SuppressWarnings("unused")
	 private static void readAndWriteExcelFile(String place1,String place2) throws Exception 
	 {
		 Workbook workBook = null;
		 // 构建Workbook对象 只读Workbook对象
		 // 直接从本地文件创建Workbook
		 // 从输入流创建Workbook
		 InputStream inputStream = new FileInputStream(place1);
		 workBook = Workbook.getWorkbook(inputStream);
		 // Sheet(术语：工作表)就是Excel表格左下角的Sheet1,Sheet2,Sheet3但在程序中
		 // Sheet的下标是从0开始的
		 // 获取第一张Sheet表
		 Sheet sheet = workBook.getSheet(0);
		 // 获取Sheet表中所包含的总列数
		 int sheetColumns = sheet.getColumns();
		 // 获取Sheet表中所包含的总行数
		 int sheetRows = sheet.getRows();
		 // 获取指这下单元格的对象引用
		 for (int i = 0; i < sheetRows; i++) 
		 {
			 for (int j = 0; j < sheetColumns; j++) 
			 {
				 // 注意在读取时 i和j的位置。i代表行 j代表列。且列在前 行在后
				 Cell cell = sheet.getCell(j, i);
				 System.out.print(cell.getContents() + " ");
			 }
			 System.out.println();
		 }
		 // 利用已经创建的Excel工作薄创建新的可写入的Excel工作薄
		 WritableWorkbook wwb = Workbook.createWorkbook(new File(place2), workBook);
		 // 读取第一张工作表
		 WritableSheet ws = wwb.getSheet(0);
		 // 获取第一个单元格对象
		 WritableCell wc = ws.getWritableCell(0, 0);
		 // 决断单元格的类型，做出相应的转化
		 if (wc.getType() == CellType.LABEL) 
		 {
			 Label l = (Label) wc;
			 l.setString("The value has been modified.");
		 }
		 // 写入Excel对象
		 wwb.write();
		 wwb.close();
		 // 操作完成时，关闭对象，翻译占用的内存空间
		 workBook.close();
	 }
 
 
 	public static void main(String[] args) throws Exception 
 	{
 		//测试readExcelFile方法，读“D:\excel\BOOK-read.xls”
 		readExcelFile("D:\\excel\\BOOK-read.xls");
 		//测试writeExcelFile方法，创建，并写“D:\excel\BOOK-write.xls”
 		writeExcelFile("D:\\excel\\BOOK-write.xls");
 		//测试readAndWriteExcelFile方法，
 		//将“D:\excel\BOOK-read-write\\1.xls”写入"D:\\excel\\BOOK-read-write\\2.xls"
 		//readAndWriteExcelFile("D:\\excel\\BOOK-read-write\\1.xls","D:\\excel\\BOOK-read-write\\2.xls");
 	}
 }