import java.io.*;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.*;

public class Excel {
	/**
	 * 原始Excel模板地址
	 */
	private static String path = "C:/Users/黃柏翰/Desktop/Example.xlsx";
	/**
	 * 匯出的Excel模板地址
	 */
	private static String export = "C:/Users/黃柏翰/Desktop/藏書清單.xlsx";
	
	public static  void ExportExcel(ArrayList<Book> booklist) throws Exception {
		//讀入檔案流
		InputStream inputStream = new FileInputStream(new File(path));
		//建立Excel物件
		Workbook wb = WorkbookFactory.create(inputStream);
		//一般Excel都會有一個預設的sheet，所以直接獲取第一個sheet
		Sheet sheet = wb.getSheetAt(0);
		//建立第一行
		Row row = sheet.createRow(0);
		//設定A1的值
		row.createCell(0).setCellValue("書名");
		row.createCell(1).setCellValue("作者");
		row.createCell(2).setCellValue("出版社");
		row.createCell(3).setCellValue("ID");
		row.createCell(4).setCellValue("種類");
		row.createCell(5).setCellValue("藏書區");
		row.createCell(6).setCellValue("書籍狀態");
		for(int i = 1; i <= booklist.size(); i++) {
			String status ="";
			int j = i - 1;
			row = sheet.createRow(i);
			row.createCell(0).setCellValue(booklist.get(j).getName());
			row.createCell(1).setCellValue(booklist.get(j).getAuthor());
			row.createCell(2).setCellValue(booklist.get(j).getPub());
			row.createCell(3).setCellValue(booklist.get(j).getId());
			row.createCell(4).setCellValue(booklist.get(j).getType());
			row.createCell(5).setCellValue(booklist.get(j).getAddress());
			switch(booklist.get(j).getHasLended()) {
			
			case 0 : status = "在架上";break;
			
			case 1 : status = "已借出";break;
			}
			row.createCell(6).setCellValue(status);
		}
		//輸出檔案
		FileOutputStream out = new FileOutputStream(export);
        wb.write(out);
        System.out.println("藏書清單Excel檔已輸出 : "+export);
	}
}
