import java.time.LocalDate;
import java.util.*;

public class Member extends Users {
    Scanner s = new Scanner(System.in);

    public Member(){}
    public Member(String account, String password, String name, String email, String phone, String identity){
        super(account, password, name, email, phone, identity);
    }
    public void viewInfo(ArrayList<Users> users, int check){
		super.viewInfo(users, check);
	}
    public void payFine(){}
    public void borrowBook(ArrayList<Book> booklist) {
    	System.out.print("請選擇使用書名或ID借閱\n1.書名\n2.ID\n請輸入 :");
    	String searchwayS = s.nextLine();
    	int searchway = Integer.parseInt(searchwayS);
    	String id = "";
    	String name = "";
    	String input = "" ;
    	switch (searchway) {
    	
    	case 1:
        	System.out.print("請輸入欲借閱之書名 : ");
        	int count = 0; boolean hasborrowed = false;
        	name = s.nextLine();
        	for(int i = 0; i < booklist.size() ; i++){
        		if(booklist.get(i).getName().equals(name) && booklist.get(i).getHasLended() == 0){
        			System.out.print("借閱或預訂?\n1.借閱\n2.預訂\n請輸入 : ");
        			String BorrowOrReserveS = s.nextLine();
                	int BorrowOrReserve = Integer.parseInt(BorrowOrReserveS);
        			switch(BorrowOrReserve) {
        			case 1:
        				System.out.print("確認借閱?\n請輸入yes (若輸入任意其他字串則取消) : ");
        				input = s.nextLine();
        				if (input.equals("yes")) { 
        					booklist.get(i).setHasLended(1);
        					LocalDate d = LocalDate.now();
        					booklist.get(i).setBorrowDate(d);
        				}
        				System.out.println("書本借閱成功!");
        				count++; hasborrowed = true;
        				break;
        			case 2:
        				System.out.print("確認預訂?\n請輸入yes (若輸入任意其他字串則取消) : ");
        				input = s.nextLine();
        				if (input.equals("yes")) { 
    					booklist.get(i).setHasLended(2);
    					LocalDate d = LocalDate.now();
    					booklist.get(i).setReserveDate(d);
        				}
        				System.out.println("書本預訂成功!");
        				count++; hasborrowed = true;
        				break;
        			default:
            			System.out.println("輸入錯誤!");
            			break;
        			}
        		break;
        		}
        		else if(booklist.get(i).getName().equals(name) && (booklist.get(i).getHasLended() == 1 || booklist.get(i).getHasLended() == 2)) {
        			count ++;
        			continue;
        		}
        	}
        	if(count == 0){System.out.print("無法借閱，無法找到此書");}
        	else if(count != 0 && hasborrowed == false ) {System.out.println("無法借閱，此書已全數被借出或預定");}
    		break;

    	case 2:
        	System.out.print("請輸入欲借閱書本之ID : ");
        	id = s.nextLine();
        	int count2 = 0;
        	for(int i = 0; i < booklist.size() ; i++){
        		if(booklist.get(i).getId().equals(id) && booklist.get(i).getHasLended() == 0){
        			System.out.print("借閱或預訂?\n1.借閱\n2.預訂\n請輸入 : ");
        			String BorrowOrReserveS = s.nextLine();
                	int BorrowOrReserve = Integer.parseInt(BorrowOrReserveS);
                	switch(BorrowOrReserve) {
                		case 1:
                			System.out.print("確認借閱?\n請輸入yes (若輸入任意其他字串則取消) : ");
                			input = s.nextLine();
                			if (input.equals("yes")) {
                				booklist.get(i).setHasLended(1);
                				LocalDate d = LocalDate.now();
                				booklist.get(i).setBorrowDate(d);
                				System.out.println("書本借閱成功!");
                				count2 = 1;
                			}
                	
                		case 2:
                			System.out.print("確認預訂?\n請輸入yes (若輸入任意其他字串則取消) : ");
                			input = s.nextLine();
                			if (input.equals("yes")) {
                			booklist.get(i).setHasLended(2);
                    		LocalDate d = LocalDate.now();
                    		booklist.get(i).setReserveDate(d);
                    		System.out.println("書本預訂成功!");
            				count2 = 1;
                			}
                			break;
                		
                		default:
                			System.out.println("輸入錯誤!");
                			break;
                	}
        			break;
        		}
        	
        		else if(booklist.get(i).getId().equals(id) && booklist.get(i).getHasLended() == 1) {System.out.println("無法借閱，此書已被借出");count2 = 1;break;}
        		
        		else if(booklist.get(i).getId().equals(id) && booklist.get(i).getHasLended() == 2) {System.out.println("無法借閱，此書已被預訂");count2 = 1;break;}	
        	}
    		if(count2 == 0) {System.out.println("無法借閱，無法找到此書");}
    		break;
    	}
    }
    public void returnBook(ArrayList<Book> booklist) {
    	String id = "";
    	String input = "";
    	System.out.print("請輸入欲歸還書本之ID : ");
    	id = s.nextLine();
    	int count = 0;
    	for(int i = 0; i < booklist.size() ; i++){
    		if(booklist.get(i).getId().equals(id)) {
    			count = 1;
    			if (booklist.get(i).getHasLended() == 1){
    				System.out.println("確認歸還?\n請輸入yes (若輸入任意其他字串則取消) : ");
    				input = s.nextLine();
    				if (input.equals("yes")) {
    					booklist.get(i).setHasLended(0);
    					System.out.println("書本歸還成功!");
    				}
    			else {System.out.println("取消歸還書本!");}
				break;
    			}
			else if(booklist.get(i).getHasLended() == 0) {System.out.println("無法歸還，此書未被借出"); break;}
			else if(booklist.get(i).getHasLended() == 2) {
				System.out.print("確認取消預訂?\n請輸入yes (若輸入任意其他字串則取消) : ");
                input = s.nextLine();
                if (input.equals("yes")) {
				booklist.get(i).setHasLended(0);
				System.out.println("已取消預訂!");
				}
                else {System.out.println("預訂未取消!");}
                break;
			}
    		}
    	}
	if(count == 0) {System.out.println("無法歸還，無法找到此書");}
    }
}