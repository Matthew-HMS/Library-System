import java.util.*;

public abstract class Users {
    private String name;
    private String password;
    private String account;//ID
    private String identity;//Admim or member
    private String email;
    private String phone;

    
    public Users(){}
    public Users(String account, String password, String name, String email, String phone, String identity){
        setAccount(account);
        setPassword(password);
        setName(name);
        setEmail(email);
        setPhone(phone);
        setIdentity(identity);
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return password;
    }
    public void setAccount(String account){
        this.account = account;
    }
    public String getAccount(){
        return account;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public String getPhone() {
        return phone;
    }
    public void setIdentity(String identity){
        this.identity = identity;
    }
    public String getIdentity(){
        return identity;
    }

    public void searchBook(ArrayList<Book> booklist){
        Scanner scan = new Scanner(System.in);

		System.out.println("查詢書籍\n1.書籍名稱查詢\n2.書籍ID查詢\n3.列出所有藏書\n4.返回");
		System.out.println("請輸入查詢方法:");
		String searchWay = "";

		do {
			searchWay = scan.nextLine();// 改成String 比較不會出錯
			if (searchWay.equals("1")) {
				System.out.println("請輸入查詢書籍之名稱:");
				String bookName = scan.nextLine();
				//int remainNum = 0;// 計算還剩下幾本
				int count = 0;
				// 圖書館有這本書
				for (int i = 0; i < booklist.size(); i++) {
					if (booklist.get(i).getName().contains(bookName)) {
						//remainNum++;//
						System.out.printf("書名:%s\t作者:%s\t出版社:%s\tID:%s\t圖書分類:%s\t存放區域:%s\n",
								booklist.get(i).getName(), booklist.get(i).getAuthor(),
								booklist.get(i).getPub(), booklist.get(i).getId(), booklist.get(i).getType(),
								booklist.get(i).getAddress());// 列印出所有相同名稱之書籍
					}
                    //if (remainNum > 0) {
                    //	System.out.printf("總共還有%d可以借", remainNum);
                    //} else {
                    //	System.out.printf("十分抱歉，本館的%s都被借光了", bookName);
                    //}

					
					// 圖書館沒有這本書
					else {
						count++;
					}
			
					
				} // for_i
                if (count == booklist.size()) {
                    System.out.println("十分抱歉，本館沒有您所查詢之書籍");
                }
				else{
                    System.out.println("以上是你的搜尋結果");
                }
			} // end if_1

			else if (searchWay.equals("2")) {
				System.out.println("請輸入查詢書籍之ID:");
				String bookId = scan.nextLine();
				for (int i = 0; i < booklist.size(); i++) {
					if (booklist.get(i).getId().equals(bookId)) {
						// 輸出書本資料
						System.out.printf("書名:%s\t作者:%s\t出版社:%s\tID:%s\t圖書分類:%s\t存放區域:%s\n", booklist.get(i).getName(),
								booklist.get(i).getAuthor(), booklist.get(i).getPub(), booklist.get(i).getId(),
								booklist.get(i).getType(), booklist.get(i).getAddress());// 列印出所有相同名稱之書籍
						break;
					}
				}
			} 
            else if (searchWay.equals("3")) {
                for (int i = 0; i < booklist.size(); i++) {
                    System.out.printf("書名:%s\t作者:%s\t出版社:%s\tID:%s\t圖書分類:%s\t存放區域:%s\n", booklist.get(i).getName(),
                            booklist.get(i).getAuthor(), booklist.get(i).getPub(), booklist.get(i).getId(),
                            booklist.get(i).getType(), booklist.get(i).getAddress());// 列印出所有相同名稱之書籍
                }
            }
            else if (searchWay.equals("4")) {
                System.out.println("您已離開查詢書籍功能");
                break;
            }
			else {
				System.out.println("請輸入正確選項");
				searchWay = "-1";
			}
		} while (searchWay.equals("-1"));
    }

	public void editMember(ArrayList<Users> users) {
        Scanner s = new Scanner(System.in);
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
					
    					System.out.print("請輸入帳號 : ");
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
    					System.out.println("請輸入密碼 : ");
    					users.get(i).setPassword(s.nextLine());
    					System.out.println("修改密碼完成!");
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
    public abstract void viewInfo();
	
    
}