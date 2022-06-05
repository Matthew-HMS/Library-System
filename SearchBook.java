package Library;


import java.util.ArrayList;
import java.util.*;

public class SearchBook {
	public void search(ArrayList<Book> booklist) {

		Scanner scan = new Scanner(System.in);

		System.out.println("查詢書籍\n1.書籍名稱查詢\n2.書籍ID查詢\n");
		System.out.println("請輸入查詢方法:");
		int searchWay = scan.nextInt();// 改成String 比較不會出錯

		do {
			if (searchWay == 1) {
				System.out.println("請輸入查詢書籍之名稱:");
				String bookName = scan.nextLine();
				int remainNum = 0;// 計算還剩下幾本
				int count = 0;
				// 圖書館有這本書
				for (int i = 0; i < booklist.size(); i++) {
					if (booklist.get(i).getName().contains(bookName) == true) {
						// 可以借
						System.out.println("本館有此書");
						for (int j = 0; j < booklist.size(); j++) {
							if (booklist.get(j).getName() == bookName) {
								remainNum++;
								System.out.printf("書名:%s\t作者:%s\t出版社:%s\tID:%d\t圖書分類:%s\t存放區域:%s",
										booklist.get(j).getName(), booklist.get(j).getAuthor(),
										booklist.get(j).getPub(), booklist.get(j).getId(), booklist.get(j).getType(),
										booklist.get(j).getAddress());// 列印出所有相同名稱之書籍
							}
						}

						if (remainNum > 0) {
							System.out.printf("總共還有%d可以借", remainNum);
						} else {
							System.out.printf("十分抱歉，本館的%s都被借光了", bookName);
						}

					} // end if_true

					// 圖書館沒有這本書
					else {
						count++;
						if (count == booklist.size()) {
							System.out.println("十分抱歉，本館沒有您所查詢之書籍");
						}
					}
				} // for_i
			} // end if_1

			else if (searchWay == 2) {
				System.out.println("請輸入查詢書籍之ID:");
				int bookId = scan.nextInt();
				for (int i = 0; i < booklist.size(); i++) {
					if (booklist.get(i).getId() == bookId) {
						// 輸出書本資料
						System.out.printf("書名:%s\t作者:%s\t出版社:%s\tID:%d\t圖書分類:%s\t存放區域:%s", booklist.get(i).getName(),
								booklist.get(i).getAuthor(), booklist.get(i).getPub(), booklist.get(i).getId(),
								booklist.get(i).getType(), booklist.get(i).getAddress());// 列印出所有相同名稱之書籍
						break;
					}
				}
			} 
			else {
				System.out.println("請輸入正確選項");
				searchWay = -1;
			}
		} while (searchWay == -1);
	}
}
