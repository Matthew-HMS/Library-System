import java.util.ArrayList;
import java.util.*;

public class SearchBook {
	public void search(ArrayList<Book> booklist) {

		Scanner scan = new Scanner(System.in);

		System.out.println("查詢書籍\n1.書籍名稱查詢\n2.書籍ID查詢\n");
		System.out.println("請輸入查詢方法:");
		String searchWay = "";

		do {
			searchWay = scan.nextLine();// 改成String 比較不會出錯
			if (searchWay.equals("1")) {
				System.out.println("請輸入查詢書籍之名稱:");
				String bookName = scan.nextLine().toLowerCase();
				//int remainNum = 0;// 計算還剩下幾本
				int count = 0;
				
				for (int i = 0; i < booklist.size(); i++) {
					//圖書館有這本書
					if (booklist.get(i).getName().contains(bookName)) {
						System.out.printf("書名:%s\t作者:%s\t出版社:%s\tID:%s\t圖書分類:%s\t存放區域:%s\n",
								booklist.get(i).getName(), booklist.get(i).getAuthor(),
								booklist.get(i).getPub(), booklist.get(i).getId(), booklist.get(i).getType(),
								booklist.get(i).getAddress());// 列印出所有相同名稱之書籍
					}
						
					// 圖書館沒有這本書
					else {
						count++;
						if (count == booklist.size()) {
							System.out.println("十分抱歉，本館沒有您所查詢之書籍");
						}
					}
			
				} // for_i
				System.out.println("以上是你的搜尋結果");//放在for外面
			} // end if_1

			else if (searchWay.equals("2")) {
				System.out.println("請輸入查詢書籍之ID:");
				String bookId = scan.nextLine();
				for (int i = 0; i < booklist.size(); i++) {
					if (booklist.get(i).getId().equals(bookId)) {
						// 輸出書本資料
						System.out.printf("書名:%s\t作者:%s\t出版社:%s\tID:%s\t圖書分類:%s\t存放區域:%s", booklist.get(i).getName(),
								booklist.get(i).getAuthor(), booklist.get(i).getPub(), booklist.get(i).getId(),
								booklist.get(i).getType(), booklist.get(i).getAddress());// 列印出所有相同名稱之書籍
						break;
					}
				}
			} 
			else {
				System.out.println("請輸入正確選項");
				searchWay = "-1";
			}
		} while (searchWay.equals("-1"));
	}
}

