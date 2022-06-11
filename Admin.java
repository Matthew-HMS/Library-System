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
        System.out.print("請輸入ID : ");
        String id = s.nextLine();
        boolean idExist = false;
        for (int i = 0; i < booklist.size(); i++) {if (booklist.get(i).getId().equals(id)) {idExist = true;}}
        
        if(idExist == false) {
        System.out.print("請輸入書名  : ");
        String name = s.nextLine();
        System.out.print("請輸入圖書分類 : ");
        String type = s.nextLine();
        System.out.print("請輸入作者 : ");
        String author = s.nextLine();
        System.out.print("請輸入出版社 : ");
        String pub = s.nextLine();
        
        int hasLended = 0;
        
        System.out.print("請輸入存放區域 : ");
        String address = s.nextLine();
        
        Book book = new Book(id, name, type, author, pub, hasLended, address);
        booklist.add(book);
        System.out.println("書本加入成功!");
        }
        else {System.out.println("書本加入失敗!");}
    }//添加書本到陣列中
        
    public void editBook(ArrayList<Book> booklist) {
    	System.out.print("請輸入欲更改資料之書本ID : ");
        String id = s.nextLine();
    	int input = 0;
    	int count = 0;
    	for (int i = 0; i < booklist.size(); i++) {
    		if(booklist.get(i).getId().equals(id)) {
    			count = 1;
    			while(input != 6) {
    				System.out.print("要更改什麼?\n1.ID\n2.書名\n3.分類\n4.借閱狀態\n5.圖書館存放區域\n6.離開\n請輸入 : ");
    				String inputS = s.nextLine();
    				input = Integer.parseInt(inputS);
    				switch(input){
    				
    				case 1:
    					System.out.print("請輸入ID : ");
    					id = s.nextLine();
    					boolean idExist = false;
    					for (int j = 0; j < booklist.size(); j++) {if (booklist.get(j).getId().equals(id)) {idExist = true;}}
    					if(idExist == false) {
    						booklist.get(i).setId(id);
    						System.out.println("修改ID完成!");
    					}
    					else {System.out.println("修改ID失敗! 此ID已存在");}
    					break;
    				
    				case 2:
    					System.out.print("請輸入書名 : ");
    					booklist.get(i).setName(s.nextLine());
    					System.out.println("修改書名完成!");
    					break;
    				
    				case 3:
    					System.out.print("請輸入分類 : ");
    					booklist.get(i).setType(s.nextLine());
    					System.out.println("修改分類完成!");
    					break;
    				
    				case 4:
    					System.out.print("請輸入借閱狀態\n0.在架上\n1.已借出\n2.已預定\n請輸入 : ");
    					String hasLendedS = s.nextLine();
    					int hasLended = Integer.parseInt(hasLendedS);
    					if(hasLended >=0 &&hasLended <=2) {
    						booklist.get(i).setHasLended(hasLended);
    						System.out.println("修改借閱狀態完成!");
    					}
    					else {System.out.println("輸入錯誤 修改借閱狀態失敗!");}
    					break;
        
    				case 5:
    					System.out.print("請輸入存放區域 : ");
    					booklist.get(i).setAddress(s.nextLine());
    					System.out.println("修改存放區域完成!");
    					break;
    					
    				default :
    					input = 6;
    					System.out.println("離開修改系統!");
    					break;
    				}
    			}
    			break;
    		}
    	}
    	if( count == 0){System.out.println("書本修改失敗! 未找到此書");}
    }

    public void deleteBook(ArrayList<Book> booklist){
       	System.out.print("請輸入欲刪除之書本ID : ");
        String id = s.nextLine();
    	String input = "";
    	int count = 0;
    	for (int i = 0 ; i < booklist.size() ; i++) {
    		if(booklist.get(i).getId().equals(id)) {
    			count = 1;
    			System.out.print("確認刪除?\n請輸入\"yes\"(若輸入任意其他字串則取消) : ");
    			input = s.nextLine();
    			switch(input){
				case "yes":
					booklist.remove(i);
					System.out.println("書本刪除成功!");
					break;
				default:
					System.out.println("取消刪除書本!");
					break;
    			}break;
			}
    	}
    	if (count == 0){System.out.println("書本刪除失敗! 未找到此書");}
    }

	public void viewInfo(ArrayList<Users> users, int check){
		String output = ("使用者名稱:"+users.get(check).getName()+"\n"
		+"身分:"+users.get(check).getIdentity()+"\n"
		+"帳號:"+users.get(check).getAccount()+"\n"
		+"密碼:"+users.get(check).getPassword()+"\n"
		+"電話:"+users.get(check).getPhone()+"\n"
		+"電子信箱:"+users.get(check).getEmail()+"\n"
		+((users.get(check).getIdentity().equals("Admin")) ?  "" : "應繳罰款 : "+users.get(check).getFine()+" 元\n" ));
		
		String [] option = {"編輯資料","清除罰金紀錄","刪除帳號","離開"};
		int input = JOptionPane.showOptionDialog(null, output, "User Information", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[1]);

		if(input == 0){
			editMember(users);
			viewInfo(users, check);
		}
		else if(input == 1){
			users.get(check).setFine(0);
			viewInfo(users, check);
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

    public void searchBook(){}
	public void searchMember(ArrayList<Users> users,ArrayList<String> askforresetfine) throws FileNotFoundException {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("查詢會員\n1.會員名字查詢\n2.會員帳號查詢\n3.會員信箱查詢\n4.會員電話查詢\n5.確認已繳納之會員罰金\n6.離開\n7.列出所有會員");
		System.out.println("請輸入查詢方法:");
		String searchWay = scan.nextLine();
		boolean exist = false;
		do {
			
			if(searchWay.equals("1")) {
				System.out.println("請輸入欲查詢會員的名字:");
				String memberName = scan.nextLine();
				for(int i = 0; i < users.size();i++) {
					if(users.get(i).getName().equals(memberName)) {
						viewInfo(users, i);
						exist = true;
					}//if_name
				}//for
				if(!exist) {System.out.println("查無此會員!");}
			}//if_search1
			else if(searchWay.equals("2")) {
				System.out.println("請輸入欲查詢會員的帳號:");
				String memberAccount = scan.nextLine();
				for(int i = 0; i < users.size();i++) {
					if(users.get(i).getAccount().equals(memberAccount)) {
						viewInfo(users, askforresetfine, i);
						exist = true;
					}//if_account
				}//for
				if(!exist) {System.out.println("查無此會員!");}
			}
			else if(searchWay.equals("3")) {
				System.out.println("請輸入欲查詢會員的信箱:");
				String memberEmail = scan.nextLine();
				for(int i = 0; i < users.size();i++) {
					if(users.get(i).getEmail().equals(memberEmail)) {
						viewInfo(users, askforresetfine, i);
						exist = true;
					}//if_email
				}//for
				if(!exist) {System.out.println("查無此會員!");}
			}
			else if(searchWay.equals("4")) {
				System.out.println("請輸入查詢會員的電話:");
				String memberPhone = scan.nextLine();
				for(int i = 0; i < users.size();i++) {
					if(users.get(i).getPhone().equals(memberPhone)) {
						viewInfo(users, askforresetfine, i);
						exist = true;
					}//if_phone
				}//for
				if(!exist) {System.out.println("查無此會員!");}
			}
			else if(searchWay.equals("5")){
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
				else{System.out.println("無會員提出清除罰金紀錄要求!");}
			}
			else if(searchWay.equals("6")) {
				System.out.println("你已離開查詢會員功能");
			}
			else if(searchWay.equals("7")){
				printMember(users);
			}
			else {
				searchWay = "-1";
				System.out.println("請輸入正確選項");
			}
		}while(searchWay.equals("-1"));
		
		
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
		System.out.println("會員列表已列出至D:MemberList.txt");
	}

	public void resetFine(ArrayList<Users> users , ArrayList<String> askforresetfine)
    {
		int check = 0; int j = 0;
		for(int i = 0; i < users.size(); i++) {
			for(j = 0; j<askforresetfine.size(); j++)
			if (users.get(i).getAccount().equals(askforresetfine.get(j))) {check = i; break;}
		}
    	users.get(check).checkFine(users, check);
    	if(users.get(check).getFine() != 0) {
    		System.out.print("請確認此會員罰金是否已繳清?\n若已繳清 請輸入\"yes\"以清除未繳罰金(若輸入任意其他字串則取消) :");
    		String input = scan.nextLine();
    		if (input.equals("yes")) {
    			users.get(check).setFine(0);
				askforresetfine.remove(j);
    			System.out.println("罰金已歸零!");}
    		else {System.out.println("罰金未歸零!");}
    	}
    	else {System.out.println("此會員無未繳罰金!");}
    }
	public int getBorrowLimit() {return 0;}
	public int getFinePerDay() {return 0;}
	public void askForResetFine(ArrayList<Users> users, ArrayList<String> askforresetfine, int check) {
	}

}