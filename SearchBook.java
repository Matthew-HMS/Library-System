package Try;

import java.util.ArrayList;
import java.util.*;

public class SearchBook {
	public void search(ArrayList<Book> booklist) {

		Scanner scan = new Scanner(System.in);

		System.out.println("�d�߮��y\n1.���y�W�٬d��\n2.���yID�d��\n");
		System.out.println("�п�J�d�ߤ�k:");
		String searchWay = "";

		do {
			searchWay = scan.nextLine();// �令String ������|�X��
			if (searchWay.equals("1")) {
				System.out.println("�п�J�d�߮��y���W��:");
				String bookName = scan.nextLine();
				//int remainNum = 0;// �p���ٳѤU�X��
				int count = 0;
				// �Ϯ��]���o����
				for (int i = 0; i < booklist.size(); i++) {
					
						// �i�H��
						
						
							if (booklist.get(i).getName().contains(bookName)) {
								//remainNum++;//
								System.out.printf("�ѦW:%s\t�@��:%s\t�X����:%s\tID:%s\t�ϮѤ���:%s\t�s��ϰ�:%s\n",
										booklist.get(i).getName(), booklist.get(i).getAuthor(),
										booklist.get(i).getPub(), booklist.get(i).getId(), booklist.get(i).getType(),
										booklist.get(i).getAddress());// �C�L�X�Ҧ��ۦP�W�٤����y
							}
						

//						if (remainNum > 0) {
//							System.out.printf("�`�@�٦�%d�i�H��", remainNum);
//						} else {
//							System.out.printf("�Q����p�A���]��%s���Q�ɥ��F", bookName);
//						}

					
					// �Ϯ��]�S���o����
					else {
						count++;
						if (count == booklist.size()) {
							System.out.println("�Q����p�A���]�S���z�Ҭd�ߤ����y");
						}
					}
			
					
				} // for_i
				System.out.println("�H�W�O�A���j�M���G");//��bfor�~��
			} // end if_1

			else if (searchWay.equals("2")) {
				System.out.println("�п�J�d�߮��y��ID:");
				String bookId = scan.nextLine();
				for (int i = 0; i < booklist.size(); i++) {
					if (booklist.get(i).getId().equals(bookId)) {
						// ��X�ѥ����
						System.out.printf("�ѦW:%s\t�@��:%s\t�X����:%s\tID:%s\t�ϮѤ���:%s\t�s��ϰ�:%s", booklist.get(i).getName(),
								booklist.get(i).getAuthor(), booklist.get(i).getPub(), booklist.get(i).getId(),
								booklist.get(i).getType(), booklist.get(i).getAddress());// �C�L�X�Ҧ��ۦP�W�٤����y
						break;
					}
				}
			} 
			else {
				System.out.println("�п�J���T�ﶵ");
				searchWay = "-1";
			}
		} while (searchWay.equals("-1"));
	}
}

