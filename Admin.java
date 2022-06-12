import java.util.*;
import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Admin extends Users{
	Scanner s = new Scanner(System.in);
    public Admin(){}
    
    public Admin(String account, String password, String name, String email, String phone, String identity){
        super(account, password, name, email, phone, identity);
    }
    
    public void addBook (ArrayList<Book> booklist){
        
        String id = JOptionPane.showInputDialog(null, "請輸入ID : ", "add Book", JOptionPane.INFORMATION_MESSAGE);
        boolean idExist = false;
        for (int i = 0; i < booklist.size(); i++) {if (booklist.get(i).getId().equals(id)) {idExist = true;}}
        
        if(idExist == false) {
        String name = JOptionPane.showInputDialog(null, "請輸入書名  : ", "add Book", JOptionPane.INFORMATION_MESSAGE);
        String type = JOptionPane.showInputDialog(null, "請輸入圖書分類 : ", "add Book", JOptionPane.INFORMATION_MESSAGE);
        String author = JOptionPane.showInputDialog(null, "請輸入作者 : ", "add Book", JOptionPane.INFORMATION_MESSAGE);
        String pub = JOptionPane.showInputDialog(null, "請輸入出版社 : ", "add Book", JOptionPane.INFORMATION_MESSAGE);
        
        int hasLended = 0;
        String address = JOptionPane.showInputDialog(null, "請輸入存放區域 : ", "add Book", JOptionPane.INFORMATION_MESSAGE);
        
        Book book = new Book(id, name, type, author, pub, hasLended, address);
        booklist.add(book);
        JOptionPane.showMessageDialog(null,"書本加入成功!", "add Book", JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			JOptionPane.showMessageDialog(null,"書本加入失敗!書本ID已存在", "add Book", JOptionPane.INFORMATION_MESSAGE);
		}
        
    }//添加書本到陣列中
        
    public void editBook(ArrayList<Book> booklist) {
    	String id = JOptionPane.showInputDialog(null, "請輸入欲更改資料之書本ID : ", "edit Book", JOptionPane.INFORMATION_MESSAGE);
    	int input = 0;
    	int count = 0;
    	for (int i = 0; i < booklist.size(); i++) {
    		if(booklist.get(i).getId().equals(id)) {
    			count = 1;
    			while(input != 5) {
					String option [] = {"ID","書名", "圖書分類", "借閱狀態", "存放區域", "返回"};
    				input = JOptionPane.showOptionDialog(null,"要更改什麼?", "edit Book", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[5]);
    				switch(input){
    				
    				case 0:

    					id = JOptionPane.showInputDialog(null, "請輸入新ID : ", "edit Book", JOptionPane.INFORMATION_MESSAGE);
    					boolean idExist = false;
    					for (int j = 0; j < booklist.size(); j++) {if (booklist.get(j).getId().equals(id)) {idExist = true;}}
    					if(idExist == false) {
    						booklist.get(i).setId(id);
    						JOptionPane.showMessageDialog(null, "修改ID完成!", "edit Book", JOptionPane.INFORMATION_MESSAGE);
    					}
    					else {JOptionPane.showMessageDialog(null, "修改ID失敗! 此ID已存在", "edit Book", JOptionPane.INFORMATION_MESSAGE);}
    					break;
    				
    				case 1:
    					
    					booklist.get(i).setName(JOptionPane.showInputDialog(null, "請輸入新書名 : ", "edit Book", JOptionPane.INFORMATION_MESSAGE));
    					JOptionPane.showMessageDialog(null, "修改書名完成!", "edit Book", JOptionPane.INFORMATION_MESSAGE);
    					break;
    				
    				case 2:
						booklist.get(i).setName(JOptionPane.showInputDialog(null, "請輸入新分類 : ", "edit Book", JOptionPane.INFORMATION_MESSAGE));
    					JOptionPane.showMessageDialog(null, "修改分類完成!", "edit Book", JOptionPane.INFORMATION_MESSAGE);
    					break;
    				
    				case 3:
						String option2 [] = {"在架上", "已借出","已預定"};
    					int hasLended = JOptionPane.showOptionDialog(null, "請選擇借閱狀態", "edit Book", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, option2, option2[0]);
    					if(hasLended >=0 &&hasLended <=2) {
    						booklist.get(i).setHasLended(hasLended);
    						JOptionPane.showMessageDialog(null, "修改借閱狀態完成!", "edit Book", JOptionPane.INFORMATION_MESSAGE);
    					}
    					else {System.exit(0);}
    					break;
        
    				case 4:

    					booklist.get(i).setAddress(JOptionPane.showInputDialog(null, "請輸入新存放區域 : ", "edit Book", JOptionPane.INFORMATION_MESSAGE));
    					JOptionPane.showMessageDialog(null, "修改存放區域完成!", "edit Book", JOptionPane.INFORMATION_MESSAGE);
    					break;
    					
    				default :
    					input = 5;
    					JOptionPane.showMessageDialog(null, "離開修改系統!", "edit Book", JOptionPane.INFORMATION_MESSAGE);
    					break;
    				}
    			}
    			break;
    		}
    	}
    	if( count == 0){JOptionPane.showMessageDialog(null, "書本修改失敗! 未找到此書", "edit Book", JOptionPane.ERROR_MESSAGE);}
    }

    public void deleteBook(ArrayList<Book> booklist){
		String id = JOptionPane.showInputDialog(null, "請輸入欲刪除之書本ID : ", "delete Book", JOptionPane.INFORMATION_MESSAGE);

    	String input = "";
    	int count = 0;
    	for (int i = 0 ; i < booklist.size() ; i++) {
    		if(booklist.get(i).getId().equals(id)) {
    			count = 1;
    			input = JOptionPane.showInputDialog(null, "確認刪除?\n請輸入\"yes\"(若輸入任意其他字串則取消) : ", "delete Book", JOptionPane.INFORMATION_MESSAGE);

    			switch(input){
				case "yes":
					booklist.remove(i);
					JOptionPane.showMessageDialog(null, "書本刪除成功!", "delete Book", JOptionPane.INFORMATION_MESSAGE);
					break;
				default:
					JOptionPane.showMessageDialog(null, "取消刪除書本!", "delete Book", JOptionPane.INFORMATION_MESSAGE);
					break;
    			}break;
			}
    	}
    	if (count == 0){JOptionPane.showMessageDialog(null, "書本刪除失敗! 未找到此書", "delete Book", JOptionPane.ERROR_MESSAGE);}
    }

	public void viewInfoAdmin(ArrayList<Users> users, ArrayList<String> askforresetfine,int check){
		String output = ("管理員資訊\n"+"使用者名稱:"+users.get(check).getName()+"\n"
		+"身分 : "+users.get(check).getIdentity()+"\n"
		+"帳號 : "+users.get(check).getAccount()+"\n"
		+"密碼 : "+users.get(check).getPassword()+"\n"
		+"電話 : "+users.get(check).getPhone()+"\n"
		+"電子信箱 : "+users.get(check).getEmail()+"\n"
		+((users.get(check).getIdentity().equals("Admin")) ?  "" : "應繳罰款 : "+users.get(check).getFine()+" 元\n" ));
	
		String [] option = {"編輯資料","刪除帳號","離開"};
		int input = JOptionPane.showOptionDialog(null, output, "User Information", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[1]);
	
		if(input == 0){
			editMember(users);
			viewInfoAdmin(users,askforresetfine,check);
		}
		else if(input == 1){
			deleteUser(users, check);
		}
		else if(input == 2){
			JOptionPane.showMessageDialog(null, "離開查看個人資訊!", "User Information", JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			System.exit(0);
		}
	}

	public void viewInfo(ArrayList<Users> users, ArrayList<String> askforresetfine,int check){
		String output = ("使用者名稱:"+users.get(check).getName()+"\n"
		+"身分 : "+users.get(check).getIdentity()+"\n"
		+"帳號 : "+users.get(check).getAccount()+"\n"
		+"密碼 : "+users.get(check).getPassword()+"\n"
		+"電話 : "+users.get(check).getPhone()+"\n"
		+"電子信箱 : "+users.get(check).getEmail()+"\n"
		+((users.get(check).getIdentity().equals("Admin")) ?  "" : "應繳罰款 : "+users.get(check).getFine()+" 元\n" ));
		
		String [] option = {"編輯資料","清除罰金紀錄","刪除帳號","離開"};
		int input = JOptionPane.showOptionDialog(null, output, "User Information", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[1]);

		if(input == 0){
			editMember(users);
			viewInfo(users,askforresetfine,check);
		}
		else if(input == 1){
			resetFine(users, askforresetfine);
		}
		else if(input == 2){
			deleteUser(users, check);
		}
		else if(input == 3){
			JOptionPane.showMessageDialog(null, "離開查看個人資訊!", "User Information", JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			System.exit(0);
		}
				
	}

	public void searchMember(ArrayList<Users> users,ArrayList<String> askforresetfine) throws Exception {
		
		String [] option = {"會員名字查詢","會員帳號查詢","會員信箱查詢","會員電話查詢","確認已繳納之會員罰金","列出所有會員","離開"};
		int searchWay = JOptionPane.showOptionDialog(null, "請選擇查詢方法", "search Member", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[1]);
		boolean exist = false;
		do {
			
			if(searchWay == 0) {

				String memberName = JOptionPane.showInputDialog(null, "請輸入欲查詢會員的名字:", "search Member", JOptionPane.QUESTION_MESSAGE);
				for(int i = 0; i < users.size();i++) {
					if(users.get(i).getName().equals(memberName)) {
						viewInfo(users,askforresetfine,i);
						exist = true;
					}//if_name
				}//for
				if(!exist) {JOptionPane.showMessageDialog(null, "查無此會員!", "search Member", JOptionPane.ERROR_MESSAGE);}
			}//if_search1
			else if(searchWay == 1) {
				
				String memberAccount = JOptionPane.showInputDialog(null, "請輸入欲查詢會員的帳號:", "search Member", JOptionPane.QUESTION_MESSAGE);
				for(int i = 0; i < users.size();i++) {
					if(users.get(i).getAccount().equals(memberAccount)) {
						viewInfo(users,askforresetfine,i);
						exist = true;
					}//if_account
				}//for
				if(!exist) {JOptionPane.showMessageDialog(null, "查無此會員!", "search Member", JOptionPane.ERROR_MESSAGE);}
			}
			else if(searchWay == 2) {
				String memberEmail = JOptionPane.showInputDialog(null, "請輸入欲查詢會員的信箱:", "search Member", JOptionPane.QUESTION_MESSAGE);

				for(int i = 0; i < users.size();i++) {
					if(users.get(i).getEmail().equals(memberEmail)) {
						viewInfo(users,askforresetfine,i);
						exist = true;
					}//if_email
				}//for
				if(!exist) {JOptionPane.showMessageDialog(null, "查無此會員!", "search Member", JOptionPane.ERROR_MESSAGE);}
			}
			else if(searchWay == 3) {

				String memberPhone = JOptionPane.showInputDialog(null, "請輸入查詢會員的電話:", "search Member", JOptionPane.QUESTION_MESSAGE);
				for(int i = 0; i < users.size();i++) {
					if(users.get(i).getPhone().equals(memberPhone)) {
						viewInfo(users,askforresetfine,i);
						exist = true;
					}//if_phone
				}//for
				if(!exist) {JOptionPane.showMessageDialog(null, "查無此會員!", "search Member", JOptionPane.ERROR_MESSAGE);}
			}
			else if(searchWay == 4) {
				if(askforresetfine.size()>0) {
					for(int i =0; i<askforresetfine.size(); i++) {
						resetFine(users , askforresetfine);
					}
					for(int i =0; i < users.size(); i++) {
						if(users.get(i).getIdentity().equals("Admin") == true) {
							users.get(i).eraseNotice();
						}
					}
				}
				else{JOptionPane.showMessageDialog(null, "無會員提出清除罰金紀錄要求!", "search Member", JOptionPane.ERROR_MESSAGE);}
			}
			else if(searchWay == 5){
				printMember(users);
			}
			else if(searchWay == 6) {
				JOptionPane.showMessageDialog(null, "你已離開查詢會員功能", "search Member", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				System.exit(0);
			}
		}while(searchWay == -1);
		
		
	}

	public void printMember(ArrayList<Users> users) throws FileNotFoundException {
		String print = "%-20s %-20s %-20s %-20s %-20s %-20s\n";
		String title = "%-20s %-20s %-20s %-20s %-20s %-20s\n\n";
		
		PrintStream ps = new PrintStream("D:MemberList.txt");
		ps.printf(title, "Name", "Identity", "Account", "Password", "Phone", "Email");
		for(int i = 0; i < users.size(); i++){
			ps.printf(print, users.get(i).getName(), users.get(i).getIdentity(), users.get(i).getAccount(), users.get(i).getPassword(), users.get(i).getPhone(), users.get(i).getEmail());
		}
		ps.close();
		JOptionPane.showMessageDialog(null, "會員列表已列出至D:MemberList.txt", "print Member", JOptionPane.INFORMATION_MESSAGE);
	}

	public void resetFine(ArrayList<Users> users , ArrayList<String> askforresetfine)
    {
		int check = 0; int j = 0;
		for(int i = 0; i < users.size(); i++) {
			for(j = 0; j<askforresetfine.size(); j++)
			if (users.get(i).getAccount().equals(askforresetfine.get(j))) {check = i; break;}
		}
    	if(users.get(check).getFine() != 0) {
    		String input = JOptionPane.showInputDialog(null, "請確認會員 : "+ users.get(check).getAccount() + " 是否已歸還書籍、罰金是否已繳清?\n若已歸還與繳清 請輸入\"yes\"以清除未繳罰金(若輸入任意其他字串則取消) :", "reset Fine", JOptionPane.QUESTION_MESSAGE);
    		if (input.equals("yes")) {
    			users.get(check).setFine(0);
				askforresetfine.remove(j);
    			JOptionPane.showMessageDialog(null, "罰金已歸零!", "reset Fine", JOptionPane.INFORMATION_MESSAGE);
    		}
    		else {JOptionPane.showMessageDialog(null, "罰金未歸零!", "reset Fine", JOptionPane.INFORMATION_MESSAGE);}
    	}
    	else {JOptionPane.showMessageDialog(null, "此會員無未繳罰金!", "reset Fine", JOptionPane.INFORMATION_MESSAGE);}
    }
	public int getBorrowLimit() {return 0;}
	public int getFinePerDay() {return 0;}
	public void askForResetFine(ArrayList<Users> users, ArrayList<String> askforresetfine, int check) {
	}

}
