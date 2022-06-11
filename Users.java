import java.util.*;

import javax.swing.JOptionPane;

import java.io.FileNotFoundException;
import java.io.PrintStream;


public abstract class Users {
    private String name;
    private String password;
    private String account;//ID
    private String identity;//Admim or member
    private String email;
    private String phone;
	private int fine;

	Scanner scan = new Scanner(System.in);
    
    public Users(){}
    public Users(String account, String password, String name, String email, String phone, String identity){
        setAccount(account);
        setPassword(password);
        setName(name);
        setEmail(email);
        setPhone(phone);
        setIdentity(identity);
		setFine(0);
	}
    
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return password;
    }
    public void setAccount(String account){
        this.account = account;
    }
    public String getAccount(){
        return account;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public String getPhone() {
        return phone;
    }
    public void setIdentity(String identity){
        this.identity = identity;
    }
    public String getIdentity(){
        return identity;
    }
	public void setFine(int fine){
		this.fine = fine;
	}
	public int getFine(){
		return fine;
	}

    public void searchBook(ArrayList<Book> booklist) throws FileNotFoundException{
        
		String [] options = {"書籍名稱查詢","書籍ID查詢","作者名稱查詢","列出所有藏書","返回"};
		int searchWay = JOptionPane.showOptionDialog(null, "請選擇查詢方法 :", "Search Book", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[3]);
		String status = "";

		do {
			if (searchWay == 0) {
				String bookName = JOptionPane.showInputDialog(null, "請輸入查詢書籍之名稱 :", "Search Book", JOptionPane.QUESTION_MESSAGE);
				bookName = bookName.toLowerCase();
				//int remainNum = 0;// 計算還剩下幾本
				int count = 0;
				
				// 圖書館有這本書
				for (int i = 0; i < booklist.size(); i++) {
					if (booklist.get(i).getName().toLowerCase().contains(bookName)) {
						if(booklist.get(i).getHasLended() == 0){status = "在架上";}
						else if(booklist.get(i).getHasLended() == 1){status = "已借出";}
						else if(booklist.get(i).getHasLended() == 2){status = "已預約";}
						else{status = "未知狀態";}
						JOptionPane.showMessageDialog(null,"書名 :" + booklist.get(i).getName() + "\t作者:" + booklist.get(i).getAuthor() +"\t出版社:" 
							+ booklist.get(i).getPub() + "\tID:" + booklist.get(i).getId() + "\t圖書分類:" + booklist.get(i).getType() + "\t存放區域:" 
							+ booklist.get(i).getAddress() + "\t書籍狀態:" + status + "\n");
					}
					// 圖書館沒有這本書
					else {count++;}
				} // for_i
				
               	if (count == booklist.size()) {
                    JOptionPane.showMessageDialog(null, "十分抱歉，本館沒有您所查詢之書籍", "Search Book", JOptionPane.ERROR_MESSAGE);
                }
				else{
                   	JOptionPane.showMessageDialog(null, "以上是你的搜尋結果", "Search Book", JOptionPane.INFORMATION_MESSAGE);
            	}
			} // end if_1

			else if (searchWay == 1) {
				String bookId = JOptionPane.showInputDialog(null, "請輸入查詢書籍之ID :", "Search Book", JOptionPane.QUESTION_MESSAGE);
				for (int i = 0; i < booklist.size(); i++) {
					if (booklist.get(i).getId().equals(bookId)) {
						// 輸出書本資料
						if(booklist.get(i).getHasLended() == 0){status = "在架上";}
						else if(booklist.get(i).getHasLended() == 1){status = "已借出";}
						else if(booklist.get(i).getHasLended() == 2){status = "已預約";}
						else{status = "未知狀態";}
						JOptionPane.showMessageDialog(null,"書名 :" + booklist.get(i).getName() + "\t作者:" + booklist.get(i).getAuthor() +"\t出版社:" 
							+ booklist.get(i).getPub() + "\tID:" + booklist.get(i).getId() + "\t圖書分類:" + booklist.get(i).getType() + "\t存放區域:" 
							+ booklist.get(i).getAddress() + "\t書籍狀態:" + status + "\n");
						break;
					}
				}
			} 
			else if (searchWay == 2) {
				String author = JOptionPane.showInputDialog(null, "請輸入作者名稱查詢 :", "Search Book", JOptionPane.QUESTION_MESSAGE);
				for (int i = 0; i < booklist.size(); i++) {
					if (booklist.get(i).getName().equals(author)) {
						// 輸出書本資料
						if(booklist.get(i).getHasLended() == 0){status = "在架上";}
						else if(booklist.get(i).getHasLended() == 1){status = "已借出";}
						else if(booklist.get(i).getHasLended() == 2){status = "已預約";}
						else{status = "未知狀態";}
						JOptionPane.showMessageDialog(null,"書名 :" + booklist.get(i).getName() + "\t作者:" + booklist.get(i).getAuthor() +"\t出版社:" 
							+ booklist.get(i).getPub() + "\tID:" + booklist.get(i).getId() + "\t圖書分類:" + booklist.get(i).getType() + "\t存放區域:" 
							+ booklist.get(i).getAddress() + "\t書籍狀態:" + status + "\n");
					}
				}
			}
            else if (searchWay == 3) {
                printBooklist(booklist);
            }
            else if (searchWay == 4) {
                JOptionPane.showMessageDialog(null, "您已離開查詢書籍功能", "Search Book", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
			else {
				JOptionPane.showMessageDialog(null, "請輸入正確選項", "Search Book", JOptionPane.ERROR_MESSAGE);
				searchWay = -1;
			}
		} while (searchWay == -1);
    }

	public void editMember(ArrayList<Users> users) {
        String account = JOptionPane.showInputDialog(null, "請輸入欲更改Member帳號 :", "Edit Member", JOptionPane.QUESTION_MESSAGE);
    	int input = 0;
    	int count = 0;
    	for (int i = 0; i < users.size(); i++) {
    		if(users.get(i).getAccount().equals(account)) {
    			count = 1;
    			while(input != 4) {
					String [] option = {"Password","Name","Email","Phone","離開"};
					input = JOptionPane.showOptionDialog(null, "要更改什麼?", "Edit Member", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[4]);
    				switch(input){	
    				
					case 0:
    					
						String oldPassword = JOptionPane.showInputDialog(null, "請輸入舊密碼 : ", "Edit Member", JOptionPane.QUESTION_MESSAGE);
						if(users.get(i).getPassword().equals(oldPassword)) {
							users.get(i).setPassword(JOptionPane.showInputDialog(null, "請輸入新密碼 : ", "Edit Member", JOptionPane.QUESTION_MESSAGE));
    						JOptionPane.showMessageDialog(null, "修改密碼完成!", "Edit Member", JOptionPane.INFORMATION_MESSAGE);
    						break;
						}
						else {
							JOptionPane.showMessageDialog(null, "修改密碼失敗! 舊密碼錯誤", "Edit Member", JOptionPane.ERROR_MESSAGE);
							break;
						}
    				
					case 1:
    					users.get(i).setName(JOptionPane.showInputDialog(null, "請輸入名稱 : ", "Edit Member", JOptionPane.QUESTION_MESSAGE));
    					JOptionPane.showMessageDialog(null, "修改名稱完成!", "Edit Member", JOptionPane.INFORMATION_MESSAGE);
    					break;
    				    				    			
    				case 2:
    					users.get(i).setEmail(JOptionPane.showInputDialog(null, "請輸入email : ", "Edit Member", JOptionPane.QUESTION_MESSAGE));
    					JOptionPane.showMessageDialog(null, "修改email完成!", "Edit Member", JOptionPane.INFORMATION_MESSAGE);
    					break;
        
    				case 3:
    					users.get(i).setPhone(JOptionPane.showInputDialog(null, "請輸電話 : ", "Edit Member", JOptionPane.QUESTION_MESSAGE));
    					JOptionPane.showMessageDialog(null, "修改電話完成!", "Edit Member", JOptionPane.INFORMATION_MESSAGE);
    					break;
    					
    				default :
    					input = 4;
    					JOptionPane.showMessageDialog(null, "離開修改系統!", "Edit Member", JOptionPane.INFORMATION_MESSAGE);
    					break;
    				}
    			}
    			break;
    		}
    	}
    	if( count == 0){JOptionPane.showMessageDialog(null, "會員修改失敗! 未找到此會員");}
    }
    public void viewInfo(ArrayList<Users> users, int check){
		String output = ("使用者名稱:"+users.get(check).getName()+"\n"
		+"身分:"+users.get(check).getIdentity()+"\n"
		+"帳號:"+users.get(check).getAccount()+"\n"
		+"密碼:"+users.get(check).getPassword()+"\n"
		+"電話:"+users.get(check).getPhone()+"\n"
		+"電子信箱:"+users.get(check).getEmail()+"\n"
		+((users.get(check).getIdentity().equals("Admin")) ?  "" : "應繳罰款 : "+users.get(check).getFine()+" 元\n" ));
		
		String [] option = {"編輯資料","離開"};
		int input = JOptionPane.showOptionDialog(null, output, "User Information", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[1]);

		if(input == 0){
			editMember(users);
			viewInfo(users, check);
		}
		else if(input == 1){
			JOptionPane.showMessageDialog(null, "離開查看個人資訊!", "User Information", JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			System.exit(0);
		}
				
	}
	/* 
	public void addFine(int dollars, ArrayList<Users> users, int check){
		users.get(check).setFine(users.get(check).getFine()+dollars);
		System.out.println("罰金:"+users.get(check).getFine()+" 元\n");
	}

	public void deleteUser(ArrayList<Users> users, int check) {
		users.remove(check);
		System.out.println("刪除成功!");
	}
	*/
	public void printBooklist(ArrayList<Book> booklist) throws FileNotFoundException {
		String status = "";
		String print = "%-30s\t %-30s\t %-20s %-15s %-20s %-10s %-10s\n";
		String title = "%-30s\t %-30s\t %-20s %-15s %-20s %-10s %-10s\n\n";
		
		PrintStream ps = new PrintStream("D:BookList.txt");
		ps.printf(title, "Book Name :", "Author :", "Publisher :", "Book ID :", "Book Type :", "Store Address :", "Status :");
		for (int i = 0; i < booklist.size(); i++) {
			if(booklist.get(i).getHasLended() == 0){status = "在架上";}
			else if(booklist.get(i).getHasLended() == 1){status = "已借出";}
			else if(booklist.get(i).getHasLended() == 2){status = "已預約";}
			else{status = "未知狀態";}
			ps.printf(print, booklist.get(i).getName(), booklist.get(i).getAuthor(), booklist.get(i).getPub(), booklist.get(i).getId(),
					booklist.get(i).getType(), booklist.get(i).getAddress(), status);
		}
		ps.close();
		System.out.println("書籍列表已列出至D:BookList.txt");
	}
	
    
}