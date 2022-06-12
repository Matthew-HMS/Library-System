import java.time.LocalDate;
import java.util.*;
import javax.swing.*;

public abstract class Member extends Users{

    public Member(){}
    public Member(String account, String password, String name, String email, String phone, String identity){
        super(account, password, name, email, phone, identity);
    }
    
				
	
    public void payFine(ArrayList<Users> users){
    	String input = JOptionPane.showInputDialog(null, "確認罰金已繳納?\n請輸入yes (若輸入任意其他字串則取消) : ", "Pay fine", JOptionPane.QUESTION_MESSAGE);
		if (input.equals("yes")) {JOptionPane.showMessageDialog(null, "請等候管理員確認 確認完畢後將可恢復借閱功能", "Pay fine", JOptionPane.INFORMATION_MESSAGE);}
    }
    
    public void borrowBook(ArrayList<Book> booklist, ArrayList<Book>lineup, Users user) {
    	String [] option = {"書名","ID"};
    	int searchWay = JOptionPane.showOptionDialog(null, "請選擇使用書名或ID借閱", "Borrow book", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, null);
    	String id = "";
    	String name = "";
    	String input = "" ;
    	switch (searchWay) {
    	
    	case 0:
			name = JOptionPane.showInputDialog(null, "請輸入欲借閱之書名 : ", "Borrow book", JOptionPane.QUESTION_MESSAGE);
        	int count = 0; int i = 0; int indexofbook = 0; boolean hasborrowed = false;
        	for( i = 0; i < booklist.size() ; i++){
        		if(booklist.get(i).getName().equals(name) && booklist.get(i).getHasLended() == 0){
        			input = JOptionPane.showInputDialog(null, "確認借閱?\n請輸入yes (若輸入任意其他字串則取消) : ", "Borrow book", JOptionPane.QUESTION_MESSAGE);
        			if (input.equals("yes")) { 
        				for(int j =0; j<user.borrowlist.size(); j++) {if (user.borrowlist.get(j).getName().equals(input) && user.borrowlist.get(j).getHasLended() == 2 ) {user.borrowlist.remove(j);break;}}
        				booklist.get(i).setHasLended(1);
        				LocalDate d = LocalDate.now();
        				booklist.get(i).setBorrowDate(d);
        				user.borrowlist.add(booklist.get(i));
        				user.borrowrecord.add(booklist.get(i));
        				JOptionPane.showMessageDialog(null, "借閱成功", "Borrow book", JOptionPane.INFORMATION_MESSAGE);
        			}
        			count++; hasborrowed = true;
        			break;
        		}
        		else if(booklist.get(i).getName().equals(name) && booklist.get(i).getHasLended() == 1) {count++;}
        	}
        	if(count != 0 && hasborrowed == false) {
        		input = JOptionPane.showInputDialog(null, "此書已全數被借閱 需預訂?\n請輸入yes (若輸入任意其他字串則取消) : ", "Borrow book", JOptionPane.QUESTION_MESSAGE);
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
            		JOptionPane.showMessageDialog(null, "書本預訂成功!", "Borrow book", JOptionPane.INFORMATION_MESSAGE);
            		hasborrowed = true;
            		break;
        	}
        	if(count == 0){JOptionPane.showMessageDialog(null, "無法借閱，無法找到此書","Borrow book", JOptionPane.ERROR_MESSAGE);}
    		break;
    	
    	case 1:
			id = JOptionPane.showInputDialog(null, "請輸入欲借閱書本之ID : ", "Borrow book", JOptionPane.QUESTION_MESSAGE);
			
        	int count2 = 0;
        	for(i = 0; i < booklist.size() ; i++){
        		if(booklist.get(i).getId().equals(id) && booklist.get(i).getHasLended() == 0){
        			input = JOptionPane.showInputDialog(null, "確認借閱?\n請輸入yes (若輸入任意其他字串則取消) : ", "Borrow book", JOptionPane.QUESTION_MESSAGE);
                	if (input.equals("yes")) {
                		booklist.get(i).setHasLended(1);
                		LocalDate d = LocalDate.now();
                		booklist.get(i).setBorrowDate(d);
                		Book borrowbook = (Book)booklist.get(i).clone();
                		user.borrowlist.add(borrowbook);
                		user.borrowrecord.add(borrowbook);
                		JOptionPane.showMessageDialog(null, "借閱成功", "Borrow book", JOptionPane.INFORMATION_MESSAGE);
                	}
                	count2 ++; break;
        		}
        		else if(booklist.get(i).getId().equals(id) && booklist.get(i).getHasLended() == 1) {JOptionPane.showMessageDialog(null, "無法借閱，此書已被借出","Borrow book",JOptionPane.ERROR_MESSAGE);count2++;break;}
        	}
        	if(count2 == 0) {JOptionPane.showMessageDialog(null, "無法借閱，無法找到此書","Borrow book", JOptionPane.ERROR_MESSAGE);}
    		break;
    	}
    }
    public void returnBook(ArrayList<Book> booklist, ArrayList<Book>lineup, Users user) {
    	String id = ""; String input = "";
    	boolean hasborrow = false;
    	id = JOptionPane.showInputDialog(null, "請輸入欲歸還書本之ID : ", "Return book", JOptionPane.QUESTION_MESSAGE);
    	int count = 0;
    	for(int i = 0; i < booklist.size(); i++) {
    		if(booklist.get(i).getId().equals(id) == true) {
    			count=1;
    			if(booklist.get(i).getHasLended() == 1){
    				for(int j = 0; j < user.borrowlist.size(); j++) {
    					if(user.borrowlist.get(j).getId().equals(id) == true && user.borrowlist.get(j).getHasLended() == 1) {
    						hasborrow = true;
    						input = JOptionPane.showInputDialog(null, "確認歸還?\n請輸入yes (若輸入任意其他字串則取消) : ", "Return book", JOptionPane.QUESTION_MESSAGE);
    						if (input.equals("yes")) {
    							Book returnbook = (Book)user.borrowlist.get(j).clone();
    							LocalDate d = LocalDate.now();
    							returnbook.setHasLended(3);
    							returnbook.setReturnDate(d);
    							for(int k = 0; k<user.borrowlist.size(); k++) {if(user.borrowrecord.get(k).getId().equals(id) == true) {user.borrowrecord.set(k,returnbook); break;}}
    							user.borrowlist.remove(j);
    							booklist.get(i).setHasLended(0);
    							booklist.get(i).setBorrowDate(null);
    							JOptionPane.showMessageDialog(null, "歸還成功", "Return book", JOptionPane.INFORMATION_MESSAGE);
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
    				if(hasborrow == false) {JOptionPane.showMessageDialog(null, "無法歸還 未借閱此書!","Return book", JOptionPane.ERROR_MESSAGE); break;}
    			}
    			else {JOptionPane.showMessageDialog(null, "無法歸還 此書未被借閱!","Return book", JOptionPane.ERROR_MESSAGE);break;}
    		}
    	}
    	if(count == 0) {JOptionPane.showMessageDialog(null, "無法歸還 未找到此書!","Return book", JOptionPane.ERROR_MESSAGE);}
    }
    
    public void cancelReserve(ArrayList<Book> lineup, Users user) {
    	String input = ""; boolean hasreserve = false; int count = 0;
    	input = JOptionPane.showInputDialog(null, "請輸入欲取消預約之書名 : ", "Cancel reserve", JOptionPane.QUESTION_MESSAGE);
    	for(int i = 0; i < user.borrowlist.size() ; i++) {
    		if(user.borrowlist.get(i).getName().equals(input) == true) {count ++;}
    		if(user.borrowlist.get(i).getName().equals(input) == true && user.borrowlist.get(i).getHasLended() == 2) {
    			hasreserve = true;
    			input = JOptionPane.showInputDialog(null, "確認取消預約?\n請輸入yes (若輸入任意其他字串則取消) : ", "Cancel reserve", JOptionPane.QUESTION_MESSAGE);
				if (input.equals("yes")) {
					user.borrowlist.remove(i);
					for(int j = 0; j < lineup.size(); j++) {if (lineup.get(j).getReserveMember().equals(user)){lineup.remove(j);break;}}//取消排隊
					JOptionPane.showMessageDialog(null, "取消預約成功!", "Cancel reserve", JOptionPane.INFORMATION_MESSAGE);
				}
				count++; break;
    		}
    	}
    	if(hasreserve = false) {JOptionPane.showMessageDialog(null, "無法取消預約 未預約此書!", "Cancel reserve", JOptionPane.ERROR_MESSAGE);}
    	if(count == 0) {JOptionPane.showMessageDialog(null, "無法取消預約 未找到此書!", "Cancel reserve", JOptionPane.ERROR_MESSAGE);}
    }
    
	public int getBorrowLimit() {return 0;}
	public int getFinePerDay() {return 0;}
	public void viewInfo(ArrayList<Users> users, ArrayList<String> askforresetfine,int check){
		String output = ("使用者名稱:"+users.get(check).getName()+"\n"
				+"身分 : "+users.get(check).getIdentity()+"\n"
				+"帳號 : "+users.get(check).getAccount()+"\n"
				+"密碼 : "+users.get(check).getPassword()+"\n"
				+"電話 : "+users.get(check).getPhone()+"\n"
				+"電子信箱 : "+users.get(check).getEmail()+"\n"
				+((users.get(check).getIdentity().equals("Admin")) ?  "" : "應繳罰款 : "+users.get(check).getFine()+" 元\n" )
				+"借閱紀錄 : "+users.get(check).borrowrecord.toString()+"\n");

				String [] option = {"編輯資料","要求清除未繳罰金紀錄","離開"};

		int input = JOptionPane.showOptionDialog(null, output, "User Information", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[1]);

		if(input == 0){
			editMember(users);
			viewInfo(users, askforresetfine, check);
		}
		else if(input == 1){
			askForResetFine(users, askforresetfine, check);
		}
		else if(input == 2){
			JOptionPane.showMessageDialog(null, "離開查看個人資訊!", "User Information", JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			System.exit(0);
		}
				
	}
	public void resetFine(ArrayList<Users> users , ArrayList<String> askforresetfine) {};
	public void askForResetFine(ArrayList<Users> users, ArrayList<String> askforresetfine, int check) {
		if(users.get(check).getFine() != 0) {
			String account = users.get(check).getAccount();
			askforresetfine.add(account);
			for(int i =0; i < users.size(); i++) {
				if(users.get(i).getIdentity().equals("Admin") == true) {users.get(i).addNotice("會員帳號 : "+users.get(check).getAccount()+"要求清除罰金 請確認是否已繳納!\n");}
			}
			JOptionPane.showMessageDialog(null, "已要求清除罰金紀錄 請等待管理員確認!", "User Information", JOptionPane.INFORMATION_MESSAGE);
		}
		else {JOptionPane.showMessageDialog(null, "無需繳納罰金!", "User Information", JOptionPane.INFORMATION_MESSAGE);}
	}
}
