import java.util.*;

import javax.swing.JOptionPane;

import org.apache.commons.collections4.functors.NullIsFalsePredicate;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDate;


public abstract class Users {
    private String name;
    private String password;
    private String account;//ID
    private String identity;//Admin or member
    private String email;
    private String phone;
	private int fine = 0;
	private String notice="";
	ArrayList<Book> borrowlist = new ArrayList<Book>();
	ArrayList<Book> borrowrecord = new ArrayList<Book>();

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
    
    public void setName(String name){this.name = name;}
    public String getName(){return name;}
    public void setPassword(String password){this.password = password;}
    public String getPassword(){return password;}
    public void setAccount(String account){this.account = account;}
    public String getAccount(){return account;}
    public void setEmail(String email) {this.email = email;}
    public String getEmail() {return email;}
    public void setPhone(String phone) {this.phone = phone;}
    public String getPhone() {return phone;}
    public void setIdentity(String identity){this.identity = identity;}
    public String getIdentity(){return identity;}
	public void setFine(int fine){this.fine = fine;}
	public void addFine(int fine){this.fine += fine;}
	public int getFine(){return fine;}
	public void addNotice(String notice) {this.notice += notice;}
	public void eraseNotice() {this.notice = "";}
	public String getNotice() {return this.notice;}

    public void searchBook(ArrayList<Book> booklist) throws Exception{
        
		String [] options = {"���y�W�٬d��","���yID�d��","�@�̦W�٬d��","�C�X�Ҧ��î�","��^"};
		int searchWay = JOptionPane.showOptionDialog(null, "�п�ܬd�ߤ�k :", "Search Book", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[3]);
		String status = "";
		int count = 0;

		do {
			if (searchWay == 0) {

				String bookName = JOptionPane.showInputDialog(null, "�п�J�d�߮��y���W�� :", "Search Book", JOptionPane.QUESTION_MESSAGE);
				bookName = bookName.toLowerCase();
				
				
				// �Ϯ��]���o����
				String output = "";
				count = 0;
				for (int i = 0; i < booklist.size(); i++) {
					if (booklist.get(i).getName().toLowerCase().contains(bookName)) {
						if(booklist.get(i).getHasLended() == 0){status = "�b�[�W";}
						else if(booklist.get(i).getHasLended() == 1){status = "�w�ɥX";}
						else if(booklist.get(i).getHasLended() == 2){status = "�w�w��";}
						else{status = "�������A";}
						output += ("�ѦW :  " + booklist.get(i).getName() + "\t�@��:" + booklist.get(i).getAuthor() +"\t�X����:" 
							+ booklist.get(i).getPub() + "\tID:" + booklist.get(i).getId() + "\t�ϮѤ���:" + booklist.get(i).getType() + "\t�s��ϰ�:" 
							+ booklist.get(i).getAddress() + "\t���y���A:" + status + "\n");
					}
					// �Ϯ��]�S���o����
					else {count++;}
				} // for_i
				
               	if (count == booklist.size()) {
                    JOptionPane.showMessageDialog(null, "�Q����p�A���]�S���z�Ҭd�ߤ����y", "Search Book", JOptionPane.ERROR_MESSAGE);
                }
				else{
                   	JOptionPane.showMessageDialog(null, output + "�H�W�O�A���j�M���G", "Search Book", JOptionPane.INFORMATION_MESSAGE);
            	}
			} // end if_1

			else if (searchWay == 1) {
				count = 0;
				String bookId = JOptionPane.showInputDialog(null, "�п�J�d�߮��y��ID :", "Search Book", JOptionPane.QUESTION_MESSAGE);
				for (int i = 0; i < booklist.size(); i++) {
					if (booklist.get(i).getId().equals(bookId)) {
						// ��X�ѥ����
						if(booklist.get(i).getHasLended() == 0){status = "�b�[�W";}
						else if(booklist.get(i).getHasLended() == 1){status = "�w�ɥX";}
						else if(booklist.get(i).getHasLended() == 2){status = "�w�w��";}
						else{status = "�������A";}
						JOptionPane.showMessageDialog(null,"�ѦW :" + booklist.get(i).getName() + "\t�@��:" + booklist.get(i).getAuthor() +"\t�X����:" 
							+ booklist.get(i).getPub() + "\tID:" + booklist.get(i).getId() + "\t�ϮѤ���:" + booklist.get(i).getType() + "\t�s��ϰ�:" 
							+ booklist.get(i).getAddress() + "\t���y���A:" + status + "\n" + "�H�W�O�A���j�M���G");
						break;
					}
				}
				if (count == booklist.size()) {
                    JOptionPane.showMessageDialog(null, "�Q����p�A���]�S���z�Ҭd�ߤ����y", "Search Book", JOptionPane.ERROR_MESSAGE);
                }
			} 
			else if (searchWay == 2) {
				count = 0;
				String output = "";
				String author = JOptionPane.showInputDialog(null, "�п�J�@�̦W�٬d�� :", "Search Book", JOptionPane.QUESTION_MESSAGE);
				for (int i = 0; i < booklist.size(); i++) {
					if (booklist.get(i).getAuthor().equals(author)) {
						// ��X�ѥ����
						if(booklist.get(i).getHasLended() == 0){status = "�b�[�W";}
						else if(booklist.get(i).getHasLended() == 1){status = "�w�ɥX";}
						else if(booklist.get(i).getHasLended() == 2){status = "�w�w��";}
						else{status = "�������A";}
						output += ("�ѦW :" + booklist.get(i).getName() + "\t�@��:" + booklist.get(i).getAuthor() +"\t�X����:" 
							+ booklist.get(i).getPub() + "\tID:" + booklist.get(i).getId() + "\t�ϮѤ���:" + booklist.get(i).getType() + "\t�s��ϰ�:" 
							+ booklist.get(i).getAddress() + "\t���y���A:" + status + "\n");
					}
					else {count++;}
				}
				if (count == booklist.size()) {
                    JOptionPane.showMessageDialog(null, "�Q����p�A���]�S���z�Ҭd�ߤ����y", "Search Book", JOptionPane.ERROR_MESSAGE);
                }
				else{
                   	JOptionPane.showMessageDialog(null, output + "�H�W�O�A���j�M���G", "Search Book", JOptionPane.INFORMATION_MESSAGE);
            	}
			}
            else if (searchWay == 3) {
                Excel.ExportExcel(booklist);
            }
            else if (searchWay == 4) {
                JOptionPane.showMessageDialog(null, "�z�w���}�d�߮��y�\��", "Search Book", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
			else {
				JOptionPane.showMessageDialog(null, "�п�J���T�ﶵ", "Search Book", JOptionPane.ERROR_MESSAGE);
				searchWay = -1;
			}
		} while (searchWay == -1);
    }

	public void editMember(ArrayList<Users> users) {
        String account = JOptionPane.showInputDialog(null, "�п�J�����Member�b�� :", "Edit Member", JOptionPane.QUESTION_MESSAGE);
    	int input = 0;
    	int count = 0;
    	for (int i = 0; i < users.size(); i++) {
    		if(users.get(i).getAccount().equals(account)) {
    			count = 1;
    			while(input != 4) {
					String [] option = {"Password","Name","Email","Phone","���}"};
					input = JOptionPane.showOptionDialog(null, "�n��擄��?", "Edit Member", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[4]);
    				switch(input){	
    				
					case 0:
    					
						String oldPassword = JOptionPane.showInputDialog(null, "�п�J�±K�X : ", "Edit Member", JOptionPane.QUESTION_MESSAGE);
						if(users.get(i).getPassword().equals(oldPassword)) {
							users.get(i).setPassword(JOptionPane.showInputDialog(null, "�п�J�s�K�X : ", "Edit Member", JOptionPane.QUESTION_MESSAGE));
    						JOptionPane.showMessageDialog(null, "�ק�K�X����!", "Edit Member", JOptionPane.INFORMATION_MESSAGE);
    						break;
						}
						else {
							JOptionPane.showMessageDialog(null, "�ק�K�X����! �±K�X���~", "Edit Member", JOptionPane.ERROR_MESSAGE);
							break;
						}
    				
					case 1:
    					users.get(i).setName(JOptionPane.showInputDialog(null, "�п�J�W�� : ", "Edit Member", JOptionPane.QUESTION_MESSAGE));
    					JOptionPane.showMessageDialog(null, "�ק�W�٧���!", "Edit Member", JOptionPane.INFORMATION_MESSAGE);
    					break;
    				    				    			
    				case 2:
    					users.get(i).setEmail(JOptionPane.showInputDialog(null, "�п�Jemail : ", "Edit Member", JOptionPane.QUESTION_MESSAGE));
    					JOptionPane.showMessageDialog(null, "�ק�email����!", "Edit Member", JOptionPane.INFORMATION_MESSAGE);
    					break;
        
    				case 3:
    					users.get(i).setPhone(JOptionPane.showInputDialog(null, "�п�q�� : ", "Edit Member", JOptionPane.QUESTION_MESSAGE));
    					JOptionPane.showMessageDialog(null, "�ק�q�ܧ���!", "Edit Member", JOptionPane.INFORMATION_MESSAGE);
    					break;
    					
    				default :
    					input = 4;
    					JOptionPane.showMessageDialog(null, "���}�ק�t��!", "Edit Member", JOptionPane.INFORMATION_MESSAGE);
    					break;
    				}
    			}
    			break;
    		}
    	}
    	if( count == 0){JOptionPane.showMessageDialog(null, "�|���ק異��! ����즹�|��");}
    }
    

	public void checkFine( ArrayList<Users> users, int check) {
		int fine = 0;
		LocalDate d = LocalDate.now();
		for(int i = 0; i< users.get(check).borrowlist.size(); i++ ) {
			int borrowdays =0;
			LocalDate borrowdate = users.get(check).borrowlist.get(i).getBorrowDate();
			while(d.equals(borrowdate) == false) {borrowdate = borrowdate.plusDays(1);borrowdays++;}
			if (borrowdays > 14) {fine += (borrowdays-14) * users.get(check).getFinePerDay();}
			users.get(check).addFine(fine);
		}
	}

	
	public void deleteUser(ArrayList<Users> users, int check) {

		String delword = JOptionPane.showInputDialog(null, "�п�J�K�X :", "Delete User", JOptionPane.QUESTION_MESSAGE);
		if(delword.equals(users.get(check).getPassword())){
			int delete = JOptionPane.showConfirmDialog(null, "�T�{�R�����b��?", "Delete User", JOptionPane.YES_NO_OPTION);
			if(delete == 0){
				users.remove(check);
				JOptionPane.showMessageDialog(null, "�R�����\!");
				check = -1;
			}
			else if(delete == 1){
				JOptionPane.showMessageDialog(null, "�R������!");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "�R���b������! �K�X���~", "Delete User", JOptionPane.ERROR_MESSAGE);
		}
		
		
	}

	public void printBooklist(ArrayList<Book> booklist) throws FileNotFoundException {
		String status = "";
		String print = "%-30s\t %-30s\t %-20s %-15s %-20s %-10s %-10s\n";
		String title = "%-30s\t %-30s\t %-20s %-15s %-20s %-10s %-10s\n\n";
		
		PrintStream ps = new PrintStream("D:BookList.txt");
		ps.printf(title, "Book Name :", "Author :", "Publisher :", "Book ID :", "Book Type :", "Store Address :", "Status :");
		for (int i = 0; i < booklist.size(); i++) {
			if(booklist.get(i).getHasLended() == 0){status = "�b�[�W";}
			else if(booklist.get(i).getHasLended() == 1){status = "�w�ɥX";}
			else if(booklist.get(i).getHasLended() == 2){status = "�w�w��";}
			else{status = "�������A";}
			ps.printf(print, booklist.get(i).getName(), booklist.get(i).getAuthor(), booklist.get(i).getPub(), booklist.get(i).getId(),
					booklist.get(i).getType(), booklist.get(i).getAddress(), status);
		}
		ps.close();

	}

	public abstract int getBorrowLimit();
	public abstract int getFinePerDay();
	public abstract void resetFine(ArrayList<Users> users , ArrayList<String> askforresetfine);
	public abstract void askForResetFine(ArrayList<Users> users, ArrayList<String> askforresetfine, int check);
	
    
}