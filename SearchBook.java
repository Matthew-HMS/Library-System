package Library;

//import java.awt.print.Book;
import java.util.ArrayList;
import java.util.*;

public class SearchBook extends Book{
	public void search(ArrayList<Book> booklist) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("�d�߮��y\n1.���y�W�٬d��\n2.���yID�d��\n");
		System.out.println("�п�J�d�ߤ�k:");
		int searchWay = scan.nextInt();
		
		do {
		if(searchWay == 1) {
			System.out.println("�п�J�d�߮��y���W��:");
			String bookName = scan.nextLine();
			int remainNum = 0;//�p���ٳѤU�X��
			
			//�Ϯ��]���o����
			if(booklist.contains(bookName) == true) {
				//�i�H��
				System.out.println("���]������");
				for(int i = 0; i < booklist.size(); i++) {
					if(booklist.get(i).getName() == bookName) {
						remainNum ++;
						System.out.printf("�ѦW:%s\t�@��:%s\t�X����:%s\tID:%d\t�ϮѤ���:%s\t�s��ϰ�:%s"
								,booklist.get(i).getName()
								,booklist.get(i).getAuthor()
								,booklist.get(i).getPub()
								,booklist.get(i).getId()
								,booklist.get(i).getType()
								,booklist.get(i).getAddress());//�C�L�X�Ҧ��ۦP�W�٤����y
					}
				}
				
				if(remainNum > 0) {
				System.out.printf("�`�@�٦�%d�i�H��",remainNum);
				}
				else {
					System.out.printf("�Q����p�A���]��%s���Q�ɥ��F",bookName);
				}
				
				
			}
			
			//�Ϯ��]�S���o����
			else {System.out.println("�Q����p�A���]�S���z�Ҭd�ߤ����y");}
		}//end if_1 
		
		else if(searchWay == 2) {
			System.out.println("�п�J�d�߮��y��ID:");
			int bookId = scan.nextInt();
			for(int i = 0; i < booklist.size(); i++) {
				if(booklist.get(i).getId() == bookId) {
					//��X�ѥ����
					System.out.printf("�ѦW:%s\t�@��:%s\t�X����:%s\tID:%d\t�ϮѤ���:%s\t�s��ϰ�:%s"
							,booklist.get(i).getName()
							,booklist.get(i).getAuthor()
							,booklist.get(i).getPub()
							,booklist.get(i).getId()
							,booklist.get(i).getType()
							,booklist.get(i).getAddress());//�C�L�X�Ҧ��ۦP�W�٤����y
					break;
				}
			}
		}
		else {
			System.out.println("�п�J���T�ﶵ");
			searchWay = -1;
		}
		}while(searchWay == -1);
}
}
