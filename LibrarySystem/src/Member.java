import java.time.LocalDate;
import java.util.*;
import javax.swing.*;

public abstract class Member extends Users{

    public Member(){}
    public Member(String account, String password, String name, String email, String phone, String identity){
        super(account, password, name, email, phone, identity);
    }
    
				
	
    public void payFine(ArrayList<Users> users){
    	String input = JOptionPane.showInputDialog(null, "�T�{�@���wú��?\n�п�Jyes (�Y��J���N��L�r��h����) : ", "Pay fine", JOptionPane.QUESTION_MESSAGE);
		if (input.equals("yes")) {JOptionPane.showMessageDialog(null, "�е��Ժ޲z���T�{ �T�{������N�i��_�ɾ\�\��", "Pay fine", JOptionPane.INFORMATION_MESSAGE);}
    }
    public void borrowBook(ArrayList<Book> booklist, ArrayList<Book>lineup, Users user) {
    	
		String [] option = {"�ѦW","ID"};
    	int searchWay = JOptionPane.showOptionDialog(null, "�п�ܨϥήѦW��ID�ɾ\", "Borrow book", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, null);
    	String id = "";
    	String name = "";
    	String input = "" ;
    	switch (searchWay) {
    	
    	case 0:
			name = JOptionPane.showInputDialog(null, "�п�J���ɾ\���ѦW : ", "Borrow book", JOptionPane.QUESTION_MESSAGE);
        	int count = 0; int i = 0; int indexofbook = 0; boolean hasborrowed = false;

        	for( i = 0; i < booklist.size() ; i++){
        		if(booklist.get(i).getName().equals(name) && booklist.get(i).getHasLended() == 0){

        			input = JOptionPane.showInputDialog(null, "�T�{�ɾ\?\n�п�Jyes (�Y��J���N��L�r��h����) : ", "Borrow book", JOptionPane.QUESTION_MESSAGE);
        			if (input.equals("yes")) { 
        				for(int j =0; j<user.borrowlist.size(); j++) {if (user.borrowlist.get(j).getName().equals(input) && user.borrowlist.get(j).getHasLended() == 2 ) {user.borrowlist.remove(j);break;}}
        				booklist.get(i).setHasLended(1);
        				LocalDate d = LocalDate.now();
        				booklist.get(i).setBorrowDate(d);
        				user.borrowlist.add(booklist.get(i));
        				user.borrowrecord.add(booklist.get(i));
            			JOptionPane.showMessageDialog(null, "�ɾ\���\", "Borrow book", JOptionPane.INFORMATION_MESSAGE);
        			}
        			count++; hasborrowed = true;
        			break;
        		}
        		else if(booklist.get(i).getName().equals(name) && booklist.get(i).getHasLended() == 1) {count++;}
        	}
        	if(count != 0 && hasborrowed == false) {

            	input = JOptionPane.showInputDialog(null, "���Ѥw���ƳQ�ɾ\ �ݹw�q?\n�п�Jyes (�Y��J���N��L�r��h����) : ", "Borrow book", JOptionPane.QUESTION_MESSAGE);
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
            		JOptionPane.showMessageDialog(null, "�ѥ��w�q���\!", "Borrow book", JOptionPane.INFORMATION_MESSAGE);
            		hasborrowed = true;
            		break;
        	}
        	if(count == 0){JOptionPane.showMessageDialog(null, "�L�k�ɾ\�A�L�k��즹��","Borrow book", JOptionPane.ERROR_MESSAGE);}
    		break;

    	case 1:
			id = JOptionPane.showInputDialog(null, "�п�J���ɾ\�ѥ���ID : ", "Borrow book", JOptionPane.QUESTION_MESSAGE);

        	int count2 = 0;
        	for(i = 0; i < booklist.size() ; i++){
        		if(booklist.get(i).getId().equals(id) && booklist.get(i).getHasLended() == 0){
        			input = JOptionPane.showInputDialog(null, "�T�{�ɾ\?\n�п�Jyes (�Y��J���N��L�r��h����) : ", "Borrow book", JOptionPane.QUESTION_MESSAGE);

                	if (input.equals("yes")) {
                		booklist.get(i).setHasLended(1);
                		LocalDate d = LocalDate.now();
                		booklist.get(i).setBorrowDate(d);
                		Book borrowbook = (Book)booklist.get(i).clone();
                		user.borrowlist.add(borrowbook);
                		user.borrowrecord.add(borrowbook);
                		JOptionPane.showMessageDialog(null, "�ɾ\���\", "Borrow book", JOptionPane.INFORMATION_MESSAGE);
                	}
                	count2 ++; break;
        		}
        		else if(booklist.get(i).getId().equals(id) && booklist.get(i).getHasLended() == 1) {JOptionPane.showMessageDialog(null, "�L�k�ɾ\�A���Ѥw�Q�ɥX","Borrow book",JOptionPane.ERROR_MESSAGE);count2++;break;}
        	}
    		if(count2 == 0) {JOptionPane.showMessageDialog(null, "�L�k�ɾ\�A�L�k��즹��","Borrow book", JOptionPane.ERROR_MESSAGE);}
    		break;
    	}
    }
    public void returnBook(ArrayList<Book> booklist, ArrayList<Book>lineup, Users user) {
    	String id = ""; String input = "";
    	boolean hasborrow = false;

    	id = JOptionPane.showInputDialog(null, "�п�J���k�ٮѥ���ID : ", "Return book", JOptionPane.QUESTION_MESSAGE);
    	int count = 0;
    	for(int i = 0; i < booklist.size(); i++) {
    		if(booklist.get(i).getId().equals(id) == true) {
    			count=1;
    			if(booklist.get(i).getHasLended() == 1){
    				for(int j = 0; j < user.borrowlist.size(); j++) {
    					if(user.borrowlist.get(j).getId().equals(id) == true && user.borrowlist.get(j).getHasLended() == 1) {
    						hasborrow = true;
    						input = JOptionPane.showInputDialog(null, "�T�{�k��?\n�п�Jyes (�Y��J���N��L�r��h����) : ", "Return book", JOptionPane.QUESTION_MESSAGE);

    						if (input.equals("yes")) {
    							Book returnbook = (Book)user.borrowlist.get(j).clone();
    							LocalDate d = LocalDate.now();
    							returnbook.setHasLended(3);
    							returnbook.setReturnDate(d);
    							for(int k = 0; k<user.borrowlist.size(); k++) {if(user.borrowrecord.get(k).getId().equals(id) == true) {user.borrowrecord.set(k,returnbook); break;}}
								user.borrowlist.remove(j);
								
    							booklist.get(i).setHasLended(0);
    							booklist.get(i).setBorrowDate(null);
    							JOptionPane.showMessageDialog(null, "�k�٦��\", "Return book", JOptionPane.INFORMATION_MESSAGE);
    							for(int k =0; k<lineup.size(); k++) {
    								if(lineup.get(k).getId().equals(id) == true) {
    									lineup.get(k).getReserveMember().addNotice("�z�w����\""+lineup.get(k).getName()+"\"�w�k�� �i�ɾ\\n");
    									break;
    									}
    								}
                    		}
                    	break;
    					}
    				}
    			if(hasborrow == false) {JOptionPane.showMessageDialog(null, "�L�k�k�� ���ɾ\����!","Return book", JOptionPane.ERROR_MESSAGE); break;}
    			}
    			else {JOptionPane.showMessageDialog(null, "�L�k�k�� ���ѥ��Q�ɾ\!","Return book", JOptionPane.ERROR_MESSAGE);break;}
    		}
    	}
    	if(count == 0) {JOptionPane.showMessageDialog(null, "�L�k�k�� ����즹��!","Return book", JOptionPane.ERROR_MESSAGE);}
    }
    
    public void cancelReserve(ArrayList<Book> lineup, Users user) {
    	String input = ""; boolean hasreserve = false; int count = 0;

    	input = JOptionPane.showInputDialog(null, "�п�J�������w�����ѦW : ", "Cancel reserve", JOptionPane.QUESTION_MESSAGE);
    	for(int i = 0; i < user.borrowlist.size() ; i++) {
    		if(user.borrowlist.get(i).getName().equals(input) == true) {count ++;}
    		if(user.borrowlist.get(i).getName().equals(input) == true && user.borrowlist.get(i).getHasLended() == 2) {
    			hasreserve = true;
    			input = JOptionPane.showInputDialog(null, "�T�{�����w��?\n�п�Jyes (�Y��J���N��L�r��h����) : ", "Cancel reserve", JOptionPane.QUESTION_MESSAGE);

				if (input.equals("yes")) {
					user.borrowlist.remove(i);
					for(int j = 0; j < lineup.size(); j++) {if (lineup.get(j).getReserveMember().equals(user)){lineup.remove(j);break;}}//�����ƶ�
					JOptionPane.showMessageDialog(null, "�����w�����\!", "Cancel reserve", JOptionPane.INFORMATION_MESSAGE);
				}
				count++; break;
    		}
    	}
    	if(hasreserve = false) {JOptionPane.showMessageDialog(null, "�L�k�����w�� ���w������!", "Cancel reserve", JOptionPane.ERROR_MESSAGE);}
    	if(count == 0) {JOptionPane.showMessageDialog(null, "�L�k�����w�� ����즹��!", "Cancel reserve", JOptionPane.ERROR_MESSAGE);}
    }
    
	public int getBorrowLimit() {return 0;}
	public int getFinePerDay() {return 0;}
	public void viewInfo(ArrayList<Users> users, ArrayList<String> askforresetfine,int check){
		String output = ("�ϥΪ̦W��:"+users.get(check).getName()+"\n"
		+"���� : "+users.get(check).getIdentity()+"\n"
		+"�b�� : "+users.get(check).getAccount()+"\n"
		+"�K�X : "+users.get(check).getPassword()+"\n"
		+"�q�� : "+users.get(check).getPhone()+"\n"
		+"�q�l�H�c : "+users.get(check).getEmail()+"\n"
		+((users.get(check).getIdentity().equals("Admin")) ?  "" : "��ú�@�� : "+users.get(check).getFine()+" ��\n" )
		+"�ɾ\���� : "+"\n");
		for(int i = 0; i< users.get(check).borrowrecord.size(); i++) {
			output += users.get(check).borrowrecord.get(i).toString();
		}

		String [] option = {"�s����","�n�D�M����ú�@������","���}"};
		int input = JOptionPane.showOptionDialog(null, output, "User Information", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[1]);

		if(input == 0){
			editMember(users);
			viewInfo(users, askforresetfine, check);
		}
		else if(input == 1){
			askForResetFine(users, askforresetfine, check);
		}
		else if(input == 2){
			JOptionPane.showMessageDialog(null, "���}�d�ݭӤH��T!", "User Information", JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			System.exit(0);
		}
				
	}
	public void resetFine(ArrayList<Users> users , ArrayList<String> askforresetfine) {};
	public void askForResetFine(ArrayList<Users> users, ArrayList<String> askforresetfine, int check) {
		String option [] = {"�O","�_"};
		int input = JOptionPane.showOptionDialog(null, "�нT�{�O�_�w�k�ٮ��y�A��ú�M�@��"  , "Central Library", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, option, option[1]);
		if(input == 0){
			if(users.get(check).getFine() != 0) {
				String account = users.get(check).getAccount();
				askforresetfine.add(account);
				for(int i =0; i < users.size(); i++) {
					if(users.get(i).getIdentity().equals("Admin") == true) {users.get(i).addNotice("�|���b�� : "+account+"�n�D�M���@�� �нT�{�O�_�wú��!\n");}
				}
				JOptionPane.showMessageDialog(null, "�w�n�D�M���@������ �е��ݺ޲z���T�{!", "User Information", JOptionPane.INFORMATION_MESSAGE);
			}
			else {JOptionPane.showMessageDialog(null, "�L��ú�ǻ@��!", "User Information", JOptionPane.INFORMATION_MESSAGE);}
		}
	}
}