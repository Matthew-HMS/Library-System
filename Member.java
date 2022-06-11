import java.time.LocalDate;
import java.util.*;

public class Member extends Users{

	Scanner s = new Scanner(System.in);
    public Member(){}
    public Member(String account, String password, String name, String email, String phone, String identity){
        super(account, password, name, email, phone, identity);
    }
    public void viewInfo(ArrayList<Users> users, int check){
		super.viewInfo(users, check);
	}
    public void payFine(ArrayList<Users> users){
    	System.out.print("確認罰金已繳納?\n請輸入yes (若輸入任意其他字串則取消) : ");
    	String input = s.nextLine();
		if (input.equals("yes")) {System.out.println("請等候管理員確認 確認完畢後將可恢復借閱功能");}
    }
    public void borrowBook(ArrayList<Book> booklist, ArrayList<Book>lineup, Users user) {
    	System.out.print("請選擇使用書名或ID借閱\n1.書名\n2.ID\n請輸入 :");
    	String searchWay = s.nextLine();
    	String id = "";
    	String name = "";
    	String input = "" ;
    	switch (searchWay) {
    	
    	case "1":
        	System.out.print("請輸入欲借閱之書名 : ");
        	int count = 0; int i = 0; int indexofbook = 0; boolean hasborrowed = false;
        	name = s.nextLine();
        	for( i = 0; i < booklist.size() ; i++){
        		if(booklist.get(i).getName().equals(name) && booklist.get(i).getHasLended() == 0){
        			System.out.print("確認借閱?\n請輸入yes (若輸入任意其他字串則取消) : ");
        			input = s.nextLine();
        			if (input.equals("yes")) { 
        				for(int j =0; j<user.borrowlist.size(); j++) {if (user.borrowlist.get(j).getName().equals(input) && user.borrowlist.get(j).getHasLended() == 2 ) {user.borrowlist.remove(j);break;}}
        				booklist.get(i).setHasLended(1);
        				LocalDate d = LocalDate.now();
        				booklist.get(i).setBorrowDate(d);
        				user.borrowlist.add(booklist.get(i));
        				user.borrowrecord.add(booklist.get(i));
            				System.out.println("書本借閱成功!");
        			}
        			count++; hasborrowed = true;
        			break;
        		}
        		else if(booklist.get(i).getName().equals(name) && booklist.get(i).getHasLended() == 1) {count++;}
        	}
        	if(count != 0 && hasborrowed == false) {
            	System.out.print("此書已全數被借閱 需預訂?\n請輸入yes (若輸入任意其他字串則取消) : ");
            	input = s.nextLine();
            	int k = 0;
            	if (input.equals("yes")) { 
                	for(k = 0; k<booklist.size(); k++) {if(booklist.get(k).getName().equals(name) == true ) {break;}}
            		LocalDate d = LocalDate.now();
            		Book reservebook = (Book)booklist.get(k).clone();
            		reservebook.setBorrowDate(null);
            		reservebook.setReserveDate(d);
            		reservebook.setHasLended(2);
            		reservebook.setReserveMember(user);
            		user.borrowlist.add(reservebook);
            		user.borrowrecord.add(reservebook);
            		lineup.add(reservebook);
            		}
            		System.out.println("書本預訂成功!");
            		hasborrowed = true;
            		break;
        	}
        	if(count == 0){System.out.println("無法借閱，無法找到此書");}
    		break;

    	case "2":
        	System.out.print("請輸入欲借閱書本之ID : ");
        	id = s.nextLine();
        	int count2 = 0;
        	for(i = 0; i < booklist.size() ; i++){
        		if(booklist.get(i).getId().equals(id) && booklist.get(i).getHasLended() == 0){
        			System.out.print("確認借閱?\n請輸入yes (若輸入任意其他字串則取消) : ");
                	input = s.nextLine();
                	if (input.equals("yes")) {
                		booklist.get(i).setHasLended(1);
                		LocalDate d = LocalDate.now();
                		booklist.get(i).setBorrowDate(d);
                		Book borrowbook = (Book)booklist.get(i).clone();
                		user.borrowlist.add(borrowbook);
                		user.borrowrecord.add(borrowbook);
                		System.out.println("書本借閱成功!");
                	}
                	count2 ++; break;
        		}
        		else if(booklist.get(i).getId().equals(id) && booklist.get(i).getHasLended() == 1) {System.out.println("無法借閱，此書已被借出");count2++;break;}
        	}
    		if(count2 == 0) {System.out.println("無法借閱，無法找到此書");}
    		break;
    	}
    }
    public void returnBook(ArrayList<Book> booklist, ArrayList<Book>lineup, Users user) {
    	String id = ""; String input = "";
    	boolean hasborrow = false;
    	System.out.print("請輸入欲歸還書本之ID : ");
    	id = s.nextLine();
    	int count = 0;
    	for(int i = 0; i < booklist.size(); i++) {
    		if(booklist.get(i).getId().equals(id) == true) {
    			count=1;
    			if(booklist.get(i).getHasLended() == 1){
    				for(int j = 0; j < user.borrowlist.size(); j++) {
    					if(user.borrowlist.get(j).getId().equals(id) == true && user.borrowlist.get(j).getHasLended() == 1) {
    						hasborrow = true;
    						System.out.print("確認歸還?\n請輸入yes (若輸入任意其他字串則取消) : ");
    						input = s.nextLine();
    						if (input.equals("yes")) {
    							Book returnbook = (Book)user.borrowlist.get(j).clone();
    							LocalDate d = LocalDate.now();
    							user.borrowlist.get(j).setHasLended(3);
    							user.borrowlist.get(j).setReturnDate(d);
    							for(int k = 0; k<user.borrowlist.size(); k++) {if(user.borrowrecord.get(k).getId().equals(id) == true) {user.borrowrecord.set(k,returnbook); break;}}
    							user.borrowlist.remove(j);
    							booklist.get(i).setHasLended(0);
    							System.out.println("書本歸還成功!");
    							for(int k =0; k<lineup.size(); k++) {
    								if(lineup.get(k).getId().equals(id) == true) {
    									lineup.get(k).getReserveMember().addNotice("您預約的\""+lineup.get(k).getName()+"\"已歸還 可借閱\n");
    									break;
    									}
    								}
                    		}
                    	break;
    					}
    				}
    			if(hasborrow == false) {System.out.println("無法歸還 未借閱此書!");break;}
    			}
    			else {System.out.println("無法歸還 此書未被借閱!");break;}
    		}
    	}
    	if(count == 0) {System.out.println("無法歸還 未找到此書!");}
    }
    
    public void cancelReserve(ArrayList<Book> lineup, Users user) {
    	String input = ""; boolean hasreserve = false; int count = 0;
    	System.out.print("請輸入欲取消預約之書名 : ");
    	input = s.nextLine();
    	for(int i = 0; i < user.borrowlist.size() ; i++) {
    		if(user.borrowlist.get(i).getName().equals(input) == true) {count ++;}
    		if(user.borrowlist.get(i).getName().equals(input) == true && user.borrowlist.get(i).getHasLended() == 2) {
    			hasreserve = true;
    			System.out.print("確認取消預約?\n請輸入yes (若輸入任意其他字串則取消) : ");
				input = s.nextLine();
				if (input.equals("yes")) {
					user.borrowlist.remove(i);
					for(int j = 0; j < lineup.size(); j++) {if (lineup.get(j).getReserveMember().equals(user)){lineup.remove(j);break;}}//取消排隊
					System.out.print("取消預約成功!");
				}
				count++; break;
    		}
    	}
    	if(hasreserve = false) {System.out.println("無法取消預約 未預約此書!");}
    	if(count == 0) {System.out.println("無法取消預約 未找到此書!");}
    }
    
	public int getBorrowLimit() {return 0;}
	public int getFinePerDay() {return 0;}
	public void resetFine(ArrayList<Users> users, int check) {}
}
