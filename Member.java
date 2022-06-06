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
    	System.out.print("請選擇使用書名或ID借閱\n輸入\n1.書名\n2.ID\n請輸入:");
    	String searchwayS = s.nextLine();
    	int searchway = Integer.parseInt(searchwayS);
    	String id = "";
    	String name = "";
    	String input = "" ;
    	switch (searchway) {
    	
    	case 1:
        	System.out.print("請輸入書名 :");
        	int count = 0;
        	name = s.nextLine();
        	for(int i = 0; i < booklist.size() ; i++){
        		if(booklist.get(i).getName().equals(name) && booklist.get(i).getHasLended() == 0){
                	System.out.print("確認借閱?\n請輸入yes: (若輸入任意其他字串則取消)");
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
        		
        		else if(i == booklist.size()-1 && count > 0) { System.out.print("無法借閱，此書已全數被借出或預定");}
        	}
        	if(count == 0){ System.out.print("無法借閱，無法找到此書");}
    		break;

    	case 2:
        	System.out.print("請輸入ID :");
        	id = s.nextLine();
        	int count2 = 0;
        	for(int i = 0; i < booklist.size() ; i++){
        		if(booklist.get(i).getId().equals(id) && booklist.get(i).getHasLended() == 0){
                	System.out.print("確認借閱?\n請輸入yes: (若輸入任意其他字串則取消)");
                	input = s.nextLine();
                	if (input.equals("yes")) {
                		booklist.get(i).setHasLended(1);
                    	System.out.println("書本借閱成功!");
            			count2 = 1;
                	}
        			break;
        		}
        		
        		else if(booklist.get(i).getId().equals(id) && booklist.get(i).getHasLended() == 1) {System.out.print("無法借閱，此書已被借出");count2 = 1;break;}
        		
        		else if(booklist.get(i).getId().equals(id) && booklist.get(i).getHasLended() == 2) {System.out.print("無法借閱，此書已被預訂");count2 = 1;break;}	
        	}
    		if(count2 == 0) {System.out.print("無法借閱，無法找到此書");}
    		break;
    	}
    }
    public void returnBook(ArrayList<Book> booklist) {
    	String id = "";
    	String input = "";
    	System.out.print("請輸入ID :");
    	id = s.nextLine();
    	int count = 0;
    	for(int i = 0; i < booklist.size() ; i++){
    		if(booklist.get(i).getId().equals(id) && booklist.get(i).getHasLended() == 1){
            	System.out.print("確認歸還?\n請輸入yes: (若輸入任意其他字串則取消)");
            	input = s.nextLine();
            	if (input.equals("yes")) {
            		booklist.get(i).setHasLended(0);
                	System.out.println("書本歸還成功!");
                	count = 1;
            	}
    		}
    		
    		else if(booklist.get(i).getId().equals(id) && booklist.get(i).getHasLended() == 0) {System.out.print("無法歸還，此書未被借出");}
    		
    		else if(booklist.get(i).getId().equals(id) && booklist.get(i).getHasLended() == 2) {System.out.print("已取消預定");}
    		
    		if(count == 0) {System.out.print("無法歸還，無法找到此書");}
    	}
    	
    }
	public void editMember(ArrayList<Users> users) {
    	System.out.print("請輸入欲更改Member姓名 :");
        String name = s.nextLine();
    	int input = 0;
    	int count = 0;
    	for (int i = 0; i < users.size(); i++) {
    		if(users.get(i).getName().equals(name)) {
    			count = 1;
    			while(input != 6) {
    				System.out.print("要更改什麼?\n1.account\n2.password\n3.name\n4.email\n5.phone\n6.離開\n請輸入: ");
    				String inputs = s.nextLine();
    				input = Integer.parseInt(inputs);
    				switch(input){
    				
    				case 1:
					
    					System.out.print("請輸入ID : ");
    					String account = s.nextLine();
    					boolean accExist = false;
    					for (int j = 0; j <users.size(); j++) {if (users.get(j).getAccount().equals(account)) {accExist = true;}}
    					if(accExist == false) {
    						users.get(i).setAccount(account);
    						System.out.println("修改帳號完成!");
    					}
    					else {System.out.println("修改帳號失敗! 此帳號已存在");}
    					break;							
    				
					case 2:
    					System.out.println("請輸入密麻 : ");
    					users.get(i).setPassword(s.nextLine());
    					System.out.println("修改密麻完成!");
    					break;
    				
					case 3:
    					System.out.println("請輸入名稱 : ");
    					users.get(i).setName(s.nextLine());
    					System.out.println("修改名稱完成!");
    					break;
    				    				    			
    				case 4:
    					System.out.println("請輸入email : ");
    					users.get(i).setEmail(s.nextLine());
    					System.out.println("修改email完成!");
    		
    					break;
        
    				case 5:
    					System.out.println("請輸電話 : ");
    					users.get(i).setPhone(s.nextLine());
    					System.out.println("修改電話完成!");
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
    	if( count == 0){System.out.println("會員修改失敗! 未找到此會員");}
    }
	public void editMember() {
	}
    
}
