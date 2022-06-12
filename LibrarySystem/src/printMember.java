import java.io.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.*;


public class printMember {
	/**
	 * ��lExcel�ҪO�a�}
	 */
	private static String path = "D:Example.xlsx";
	/**
	 * �ץX��Excel�ҪO�a�}
	 */
	private static String export = "D:�Ҧ��|�����.xlsx";
	
	public static void printMember(ArrayList<Users> userslist) throws Exception {
		//Ū�J�ɮ׬y
		InputStream inputStream = new FileInputStream(new File(path));
		//�إ�Excel����
		Workbook wb = WorkbookFactory.create(inputStream);
		//�@��Excel���|���@�ӹw�]��sheet�A�ҥH��������Ĥ@��sheet
		Sheet sheet = wb.getSheetAt(0);
		//�إ߲Ĥ@��
		Row row = sheet.createRow(0);
		//�]�wA1����
		row.createCell(0).setCellValue("�ϥΪ̦W��");
		row.createCell(1).setCellValue("����");
		row.createCell(2).setCellValue("�b��");
		row.createCell(3).setCellValue("�K�X");
		row.createCell(4).setCellValue("�q��");
		row.createCell(5).setCellValue("�q�l�H�c");
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
		//��X�ɮ�
		FileOutputStream out = new FileOutputStream(export);
        wb.write(out);
        JOptionPane.showMessageDialog(null, "�Ҧ��|�����Excel�ɤw��X : "+export, "�T��", JOptionPane.INFORMATION_MESSAGE);
	}
}