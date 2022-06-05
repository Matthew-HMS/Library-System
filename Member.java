package main;

import java.util.*;

public abstract class Member extends Users {
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
    	System.out.print("請選擇使用書名或ID借閱 :\n輸入\n1.書名\n2.ID");
    	int searchway = s.nextInt();
    	int id;
    	String name = "";
    	String input = "" ;
    	switch (searchway) {
    	
    	case 1:
        	System.out.print("請輸入書名 :");
        	int count = 0;
        	name = s.nextLine();
        	for(int i = 0; i < booklist.size() ; i++){
        		if(booklist.get(i).getName().equals(name) && booklist.get(i).getHasLended() == 0){
                	System.out.print("確認借閱?\n請輸入\"yes\": (若輸入任意其他字串則取消)");
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
        	id = s.nextInt();
        	for(int i = 0; i < booklist.size() ; i++){
        		if(booklist.get(i).getId() == id && booklist.get(i).getHasLended() == 0)
        		{
                	System.out.print("確認借閱?\n請輸入\"yes\": (若輸入任意其他字串則取消)");
                	input = s.nextLine();
                	if (input.equals("yes")) {
                		booklist.get(i).setHasLended(1);
                    	System.out.println("書本借閱成功!");
                	}
        		}
        		
        		else if(booklist.get(i).getId() == id && booklist.get(i).getHasLended() == 1) {System.out.print("無法借閱，此書已被借出");}
        		
        		else if(booklist.get(i).getId() == id && booklist.get(i).getHasLended() == 2) {System.out.print("無法借閱，此書已被預訂");}
        		
        		else {System.out.print("無法借閱，無法找到此書");}
        	}
    		break;
    	}
    }
    public void returnBook(ArrayList<Book> booklist) {
    	int id;
    	String input = "";
    	System.out.print("請輸入ID :");
    	id = s.nextInt();
    	for(int i = 0; i < booklist.size() ; i++){
    		if(booklist.get(i).getId() == id && booklist.get(i).getHasLended() == 1){
            	System.out.print("確認歸還?\n請輸入\"yes\": (若輸入任意其他字串則取消)");
            	input = s.nextLine();
            	if (input.equals("yes")) {
            		booklist.get(i).setHasLended(0);
                	System.out.println("書本歸還成功!");
            	}
    		}
    		
    		else if(booklist.get(i).getId() == id && booklist.get(i).getHasLended() == 0) {System.out.print("無法歸還，此書未被借出");}
    		
    		else if(booklist.get(i).getId() == id && booklist.get(i).getHasLended() == 2) {System.out.print("已取消預定");}
    		
    		else {System.out.print("無法歸還，無法找到此書");}
    	}
    	
    }
    
}
