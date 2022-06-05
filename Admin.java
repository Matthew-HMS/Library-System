import java.util.*;

public class Admin extends Users{
	Scanner s = new Scanner(System.in);
    public Admin(){}
    
    public Admin(String account, String password, String name, String email, String phone, String identity){
        super(account, password, name, email, phone, identity);
    }
    
    public void addBook (ArrayList<Book> booklist){
        System.out.print("請輸入ID : ");
        int id = s.nextInt();
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
    }//添加書本到陣列中
        
    public void editBook(ArrayList<Book> booklist) {
    	System.out.print("請輸入欲更改資料之書本ID :");
    	int id = s.nextInt();
    	int input = 0;
    	for (int i = 0; i < booklist.size(); i++) {
    		if(booklist.get(i).getId() == id) {
    		do {
    			System.out.print("要更改什麼? :輸入\n1.ID\n2.書名\n3.分類\n4.借閱狀態\n5.圖書館存放區域\n6.離開 ");
    			input = s.nextInt();
    			switch(input){
    			
    			case 1:
    				System.out.print("請輸入ID : ");
    				booklist.get(i).setId(s.nextInt());
    				break;
    				
    			case 2:
    				System.out.print("請輸入書名 : ");
    				booklist.get(i).setName(s.nextLine());
    				break;
    				
    			case 3:
    				System.out.print("請輸入分類 : ");
    				booklist.get(i).setType(s.nextLine());
    				break;
    				
    			case 4:
    				System.out.print("請輸入借閱狀態\n0.在架上\n1.已借出\n2.已預定 : ");
    				booklist.get(i).setHasLended(s.nextInt());
    				break;
        
    			case 5:
    				System.out.print("請輸入存放區域 : ");
    				booklist.get(i).setAddress(s.nextLine());
    				break;
    			
    			default :
    				input = 6;
    				break;
    				
    		}}while(input == 6);
    	}}
    }

    public void deleteBook(ArrayList<Book> booklist){
       	System.out.print("請輸入欲刪除之書本ID :");
    	int id = s.nextInt();
    	String input = "";
    	for (int i = 0 ; i < booklist.size() ; i++) {
    		if(booklist.get(i).getId() == id) {
			System.out.print("確認刪除?\n請輸入\"yes\": (若輸入任意其他字串則取消)");
			input = s.nextLine();
			switch(input){
			case "yes":
				booklist.remove(i);
				break;
			default:
				break;
			}}
    	}
    }

    public void viewInfo(){
    }

    public void searchBook(){
    }

}
