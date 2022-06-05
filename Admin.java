import java.util.*;

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
    	System.out.print("請輸入欲更改資料之書本ID :");
        String id = s.nextLine();
    	int input = 0;
    	int count = 0;
    	for (int i = 0; i < booklist.size(); i++) {
    		if(booklist.get(i).getId().equals(id)) {
    			count = 1;
    			while(input != 6) {
    				System.out.print("要更改什麼?\n1.ID\n2.書名\n3.分類\n4.借閱狀態\n5.圖書館存放區域\n6.離開\n請輸入: ");
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
    					System.out.print("請輸入借閱狀態\n0.在架上\n1.已借出\n2.已預定 : ");
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
       	System.out.print("請輸入欲刪除之書本ID :");
        String id = s.nextLine();
    	String input = "";
    	int count = 0;
    	for (int i = 0 ; i < booklist.size() ; i++) {
    		if(booklist.get(i).getId().equals(id)) {
    			count = 1;
    			System.out.print("確認刪除?\n請輸入\"yes\"(若輸入任意其他字串則取消) :");
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

    public void viewInfo(){
    }

    public void searchBook(){
    }

}
