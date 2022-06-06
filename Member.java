import java.util.*;

public class Member extends Users {
    Scanner s = new Scanner(System.in);

    public Member(){}
    public Member(String account, String password, String name, String email, String phone, String identity){
        super(account, password, name, email, phone, identity);
    }
    
    public String toString(){
        return getAccount() + " " + getName() + " " + getPhone();
    }
    public void viewInfo(){}
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
        	int count = 0;
        	name = s.nextLine();
        	for(int i = 0; i < booklist.size() ; i++){
        		if(booklist.get(i).getName().equals(name) && booklist.get(i).getHasLended() == 0){
                	System.out.print("確認借閱?\n請輸入yes (若輸入任意其他字串則取消) : ");
                	input = s.nextLine();
                	if (input.equals("yes")) { booklist.get(i).setHasLended(1);}
                	System.out.println("書本借閱成功!");
                	count++;
                	break;
        		}
        		
        		else if(booklist.get(i).getName().equals(name) && (booklist.get(i).getHasLended() == 1 || booklist.get(i).getHasLended() == 2)) {
        			count ++;
        			continue;
        		}
        		
        		else if(i == booklist.size()-1 && count > 0) { System.out.println("無法借閱，此書已全數被借出或預定");}
        	}
        	if(count == 0){ System.out.print("無法借閱，無法找到此書");}
    		break;

    	case 2:
        	System.out.print("請輸入欲借閱書本之ID : ");
        	id = s.nextLine();
        	int count2 = 0;
        	for(int i = 0; i < booklist.size() ; i++){
        		if(booklist.get(i).getId().equals(id) && booklist.get(i).getHasLended() == 0){
                	System.out.print("確認借閱?\n請輸入yes (若輸入任意其他字串則取消) : ");
                	input = s.nextLine();
                	if (input.equals("yes")) {
                		booklist.get(i).setHasLended(1);
                    	System.out.println("書本借閱成功!");
            			count2 = 1;
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
    					break;
    				}
    			}
				else if(booklist.get(i).getHasLended() == 0) {System.out.println("無法歸還，此書未被借出"); break;}
				else if(booklist.get(i).getHasLended() == 2) {System.out.println("已取消預定!"); break;}
    		}
    	}
		if(count == 0) {System.out.println("無法歸還，無法找到此書");}
    }
    
}
