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
        
        String id = JOptionPane.showInputDialog(null, "�п�JID : ", "add Book", JOptionPane.INFORMATION_MESSAGE);
        boolean idExist = false;
        for (int i = 0; i < booklist.size(); i++) {if (booklist.get(i).getId().equals(id)) {idExist = true;}}
        
        if(idExist == false) {
        String name = JOptionPane.showInputDialog(null, "�п�J�ѦW  : ", "add Book", JOptionPane.INFORMATION_MESSAGE);
        String type = JOptionPane.showInputDialog(null, "�п�J�ϮѤ��� : ", "add Book", JOptionPane.INFORMATION_MESSAGE);
        String author = JOptionPane.showInputDialog(null, "�п�J�@�� : ", "add Book", JOptionPane.INFORMATION_MESSAGE);
        String pub = JOptionPane.showInputDialog(null, "�п�J�X���� : ", "add Book", JOptionPane.INFORMATION_MESSAGE);
        
        int hasLended = 0;
        String address = JOptionPane.showInputDialog(null, "�п�J�s��ϰ� : ", "add Book", JOptionPane.INFORMATION_MESSAGE);
        
        Book book = new Book(id, name, type, author, pub, hasLended, address);
        booklist.add(book);
        JOptionPane.showMessageDialog(null,"�ѥ��[�J���\!", "add Book", JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			JOptionPane.showMessageDialog(null,"�ѥ��[�J����!�ѥ�ID�w�s�b", "add Book", JOptionPane.INFORMATION_MESSAGE);
		}
        
    }//�K�[�ѥ���}�C��
        
    public void editBook(ArrayList<Book> booklist) {
    	String id = JOptionPane.showInputDialog(null, "�п�J������Ƥ��ѥ�ID : ", "edit Book", JOptionPane.INFORMATION_MESSAGE);
    	int input = 0;
    	int count = 0;
    	for (int i = 0; i < booklist.size(); i++) {
    		if(booklist.get(i).getId().equals(id)) {
    			count = 1;
    			while(input != 5) {
					String option [] = {"ID","�ѦW", "�ϮѤ���", "�ɾ\���A", "�s��ϰ�", "��^"};
    				input = JOptionPane.showOptionDialog(null,"�n��擄��?", "edit Book", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[5]);
    				switch(input){
    				
    				case 0:

    					id = JOptionPane.showInputDialog(null, "�п�J�sID : ", "edit Book", JOptionPane.INFORMATION_MESSAGE);
    					boolean idExist = false;
    					for (int j = 0; j < booklist.size(); j++) {if (booklist.get(j).getId().equals(id)) {idExist = true;}}
    					if(idExist == false) {
    						booklist.get(i).setId(id);
    						JOptionPane.showMessageDialog(null, "�ק�ID����!", "edit Book", JOptionPane.INFORMATION_MESSAGE);
    					}
    					else {JOptionPane.showMessageDialog(null, "�ק�ID����! ��ID�w�s�b", "edit Book", JOptionPane.INFORMATION_MESSAGE);}
    					break;
    				
    				case 1:
    					
    					booklist.get(i).setName(JOptionPane.showInputDialog(null, "�п�J�s�ѦW : ", "edit Book", JOptionPane.INFORMATION_MESSAGE));
    					JOptionPane.showMessageDialog(null, "�ק�ѦW����!", "edit Book", JOptionPane.INFORMATION_MESSAGE);
    					break;
    				
    				case 2:
						booklist.get(i).setName(JOptionPane.showInputDialog(null, "�п�J�s���� : ", "edit Book", JOptionPane.INFORMATION_MESSAGE));
    					JOptionPane.showMessageDialog(null, "�ק��������!", "edit Book", JOptionPane.INFORMATION_MESSAGE);
    					break;
    				
    				case 3:
						String option2 [] = {"�b�[�W", "�w�ɥX","�w�w�w"};
    					int hasLended = JOptionPane.showOptionDialog(null, "�п�ܭɾ\���A", "edit Book", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, option2, option2[0]);
    					if(hasLended >=0 &&hasLended <=2) {
    						booklist.get(i).setHasLended(hasLended);
    						JOptionPane.showMessageDialog(null, "�ק�ɾ\���A����!", "edit Book", JOptionPane.INFORMATION_MESSAGE);
    					}
    					else {System.exit(0);}
    					break;
        
    				case 4:

    					booklist.get(i).setAddress(JOptionPane.showInputDialog(null, "�п�J�s�s��ϰ� : ", "edit Book", JOptionPane.INFORMATION_MESSAGE));
    					JOptionPane.showMessageDialog(null, "�ק�s��ϰ짹��!", "edit Book", JOptionPane.INFORMATION_MESSAGE);
    					break;
    					
    				default :
    					input = 5;
    					JOptionPane.showMessageDialog(null, "���}�ק�t��!", "edit Book", JOptionPane.INFORMATION_MESSAGE);
    					break;
    				}
    			}
    			break;
    		}
    	}
    	if( count == 0){JOptionPane.showMessageDialog(null, "�ѥ��ק異��! ����즹��", "edit Book", JOptionPane.ERROR_MESSAGE);}
    }

    public void deleteBook(ArrayList<Book> booklist){
		String id = JOptionPane.showInputDialog(null, "�п�J���R�����ѥ�ID : ", "delete Book", JOptionPane.INFORMATION_MESSAGE);

    	String input = "";
    	int count = 0;
    	for (int i = 0 ; i < booklist.size() ; i++) {
    		if(booklist.get(i).getId().equals(id)) {
    			count = 1;
    			input = JOptionPane.showInputDialog(null, "�T�{�R��?\n�п�J\"yes\"(�Y��J���N��L�r��h����) : ", "delete Book", JOptionPane.INFORMATION_MESSAGE);

    			switch(input){
				case "yes":
					booklist.remove(i);
					JOptionPane.showMessageDialog(null, "�ѥ��R�����\!", "delete Book", JOptionPane.INFORMATION_MESSAGE);
					break;
				default:
					JOptionPane.showMessageDialog(null, "�����R���ѥ�!", "delete Book", JOptionPane.INFORMATION_MESSAGE);
					break;
    			}break;
			}
    	}
    	if (count == 0){JOptionPane.showMessageDialog(null, "�ѥ��R������! ����즹��", "delete Book", JOptionPane.ERROR_MESSAGE);}
    }

	public void viewInfoAdmin(ArrayList<Users> users, ArrayList<String> askforresetfine,int check){
		String output = ("�޲z����T\n"+"�ϥΪ̦W��:"+users.get(check).getName()+"\n"
		+"���� : "+users.get(check).getIdentity()+"\n"
		+"�b�� : "+users.get(check).getAccount()+"\n"
		+"�K�X : "+users.get(check).getPassword()+"\n"
		+"�q�� : "+users.get(check).getPhone()+"\n"
		+"�q�l�H�c : "+users.get(check).getEmail()+"\n"
		+((users.get(check).getIdentity().equals("Admin")) ?  "" : "��ú�@�� : "+users.get(check).getFine()+" ��\n" ));
	
		String [] option = {"�s����","�R���b��","���}"};
		int input = JOptionPane.showOptionDialog(null, output, "User Information", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[1]);
	
		if(input == 0){
			editMember(users);
			viewInfoAdmin(users,askforresetfine,check);
		}
		else if(input == 1){
			deleteUser(users, check);
		}
		else if(input == 2){
			JOptionPane.showMessageDialog(null, "���}�d�ݭӤH��T!", "User Information", JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			System.exit(0);
		}
	}

	public void viewInfo(ArrayList<Users> users, ArrayList<String> askforresetfine,int check){
		String output = ("�ϥΪ̦W��:"+users.get(check).getName()+"\n"
		+"���� : "+users.get(check).getIdentity()+"\n"
		+"�b�� : "+users.get(check).getAccount()+"\n"
		+"�K�X : "+users.get(check).getPassword()+"\n"
		+"�q�� : "+users.get(check).getPhone()+"\n"
		+"�q�l�H�c : "+users.get(check).getEmail()+"\n"
		+((users.get(check).getIdentity().equals("Admin")) ?  "" : "��ú�@�� : "+users.get(check).getFine()+" ��\n" ));
		
		String [] option = {"�s����","�M���@������","�R���b��","���}"};
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
			JOptionPane.showMessageDialog(null, "���}�d�ݭӤH��T!", "User Information", JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			System.exit(0);
		}
				
	}

	public void searchMember(ArrayList<Users> users,ArrayList<String> askforresetfine) throws Exception {
		
		String [] option = {"�|���W�r�d��","�|���b���d��","�|���H�c�d��","�|���q�ܬd��","�T�{�wú�Ǥ��|���@��","�C�X�Ҧ��|��","���}"};
		int searchWay = JOptionPane.showOptionDialog(null, "�п�ܬd�ߤ�k", "search Member", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[1]);
		boolean exist = false;
		do {
			
			if(searchWay == 0) {

				String memberName = JOptionPane.showInputDialog(null, "�п�J���d�߷|�����W�r:", "search Member", JOptionPane.QUESTION_MESSAGE);
				for(int i = 0; i < users.size();i++) {
					if(users.get(i).getName().equals(memberName)) {
						viewInfo(users,askforresetfine,i);
						exist = true;
					}//if_name
				}//for
				if(!exist) {JOptionPane.showMessageDialog(null, "�d�L���|��!", "search Member", JOptionPane.ERROR_MESSAGE);}
			}//if_search1
			else if(searchWay == 1) {
				
				String memberAccount = JOptionPane.showInputDialog(null, "�п�J���d�߷|�����b��:", "search Member", JOptionPane.QUESTION_MESSAGE);
				for(int i = 0; i < users.size();i++) {
					if(users.get(i).getAccount().equals(memberAccount)) {
						viewInfo(users,askforresetfine,i);
						exist = true;
					}//if_account
				}//for
				if(!exist) {JOptionPane.showMessageDialog(null, "�d�L���|��!", "search Member", JOptionPane.ERROR_MESSAGE);}
			}
			else if(searchWay == 2) {
				String memberEmail = JOptionPane.showInputDialog(null, "�п�J���d�߷|�����H�c:", "search Member", JOptionPane.QUESTION_MESSAGE);

				for(int i = 0; i < users.size();i++) {
					if(users.get(i).getEmail().equals(memberEmail)) {
						viewInfo(users,askforresetfine,i);
						exist = true;
					}//if_email
				}//for
				if(!exist) {JOptionPane.showMessageDialog(null, "�d�L���|��!", "search Member", JOptionPane.ERROR_MESSAGE);}
			}
			else if(searchWay == 3) {

				String memberPhone = JOptionPane.showInputDialog(null, "�п�J�d�߷|�����q��:", "search Member", JOptionPane.QUESTION_MESSAGE);
				for(int i = 0; i < users.size();i++) {
					if(users.get(i).getPhone().equals(memberPhone)) {
						viewInfo(users,askforresetfine,i);
						exist = true;
					}//if_phone
				}//for
				if(!exist) {JOptionPane.showMessageDialog(null, "�d�L���|��!", "search Member", JOptionPane.ERROR_MESSAGE);}
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
				else{JOptionPane.showMessageDialog(null, "�L�|�����X�M���@�������n�D!", "search Member", JOptionPane.ERROR_MESSAGE);}
			}
			else if(searchWay == 5){
				printMember.printMember(users);
			}
			else if(searchWay == 6) {
				JOptionPane.showMessageDialog(null, "�A�w���}�d�߷|���\��", "search Member", JOptionPane.INFORMATION_MESSAGE);
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
		JOptionPane.showMessageDialog(null, "�|���C��w�C�X��D:MemberList.txt", "print Member", JOptionPane.INFORMATION_MESSAGE);
	}

	public void resetFine(ArrayList<Users> users , ArrayList<String> askforresetfine)
    {
		int check = 0; int j = 0;
		for(int i = 0; i < users.size(); i++) {
			for(j = 0; j<askforresetfine.size(); j++)
			if (users.get(i).getAccount().equals(askforresetfine.get(j))) {check = i; break;}
		}

    	if(users.get(check).getFine() != 0) {
    		String input = JOptionPane.showInputDialog(null, "�нT�{�|�� : "+ users.get(check).getAccount() + " �O�_�w�k�ٮ��y�B�@���O�_�wú�M?\n�Y�w�k�ٻPú�M �п�J\"yes\"�H�M����ú�@��(�Y��J���N��L�r��h����) :", "reset Fine", JOptionPane.QUESTION_MESSAGE);
    		if (input.equals("yes")) {
    			users.get(check).setFine(0);
				askforresetfine.remove(j);
    			JOptionPane.showMessageDialog(null, "�@���w�k�s!", "reset Fine", JOptionPane.INFORMATION_MESSAGE);}
    		else {JOptionPane.showMessageDialog(null, "�@�����k�s!", "reset Fine", JOptionPane.INFORMATION_MESSAGE);}
    	}
    	else {JOptionPane.showMessageDialog(null, "���|���L��ú�@��!", "reset Fine", JOptionPane.INFORMATION_MESSAGE);}
    }
	public int getBorrowLimit() {return 0;}
	public int getFinePerDay() {return 0;}
	public void askForResetFine(ArrayList<Users> users, ArrayList<String> askforresetfine, int check) {
	}

}