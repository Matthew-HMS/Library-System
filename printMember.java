import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.*;

public class printMember {
	/**
	 * 原始Excel模板地址
	 */
	private static String path = "D:Example.xlsx";
	/**
	 * 匯出的Excel模板地址
	 */
	private static String export = "D:所有會員資料.xlsx";
	
	public static void printMember(ArrayList<Users> userslist) throws Exception {
		//讀入檔案流
		InputStream inputStream = new FileInputStream(new File(path));
		//建立Excel物件
		Workbook wb = WorkbookFactory.create(inputStream);
		//一般Excel都會有一個預設的sheet，所以直接獲取第一個sheet
		Sheet sheet = wb.getSheetAt(0);
		//建立第一行
		Row row = sheet.createRow(0);
		//設定A1的值
		row.createCell(0).setCellValue("使用者名稱");
		row.createCell(1).setCellValue("身分");
		row.createCell(2).setCellValue("帳號");
		row.createCell(3).setCellValue("密碼");
		row.createCell(4).setCellValue("電話");
		row.createCell(5).setCellValue("電子信箱");
		//row.createCell(6).setCellValue("");
		for(int i = 1; i <= userslist.size(); i++) {
			String status ="";
			int j = i - 1;
			row = sheet.createRow(i);
			row.createCell(0).setCellValue(userslist.get(j).getName());
			row.createCell(1).setCellValue(userslist.get(j).getIdentity());
			row.createCell(2).setCellValue(userslist.get(j).getAccount());
			row.createCell(3).setCellValue(userslist.get(j).getPassword());
			row.createCell(4).setCellValue(userslist.get(j).getPhone());
			row.createCell(5).setCellValue(userslist.get(j).getEmail());
			
		}
		//輸出檔案
		FileOutputStream out = new FileOutputStream(export);
        wb.write(out);
        JOptionPane.showMessageDialog(null, "所有會員資料Excel檔已輸出 : "+export, "訊息", JOptionPane.INFORMATION_MESSAGE);
	}
}
