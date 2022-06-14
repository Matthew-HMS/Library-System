import java.io.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.*;

public class Excel {
	/**
	 * ��lExcel�ҪO�a�}
	 */
	private static String path = "D:/Example.xlsx";
	/**
	 * �ץX��Excel�ҪO�a�}
	 */
	private static String export = "D:/�îѲM��.xlsx";
	
	public static  void ExportExcel(ArrayList<Book> booklist) throws Exception {
		//Ū�J�ɮ׬y
		InputStream inputStream = new FileInputStream(new File(path));
		//�إ�Excel����
		Workbook wb = WorkbookFactory.create(inputStream);
		//�@��Excel���|���@�ӹw�]��sheet�A�ҥH��������Ĥ@��sheet
		Sheet sheet = wb.getSheetAt(0);
		//�إ߲Ĥ@��
		Row row = sheet.createRow(0);
		//�]�wA1����
		row.createCell(0).setCellValue("�ѦW");
		row.createCell(1).setCellValue("�@��");
		row.createCell(2).setCellValue("�X����");
		row.createCell(3).setCellValue("ID");
		row.createCell(4).setCellValue("����");
		row.createCell(5).setCellValue("�îѰ�");
		row.createCell(6).setCellValue("���y���A");
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
			
			case 0 : status = "�b�[�W";break;
			
			case 1 : status = "�w�ɥX";break;
			}
			row.createCell(6).setCellValue(status);
		}
		//��X�ɮ�
		FileOutputStream out = new FileOutputStream(export);
        wb.write(out);
        JOptionPane.showMessageDialog(null, "�îѲM��Excel�ɤw��X : "+export);
	}
}