import java.util.*;

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
	public int getFine(){return fine;}
	public void addNotice(String notice) {this.notice += notice;}
	public String getNotice() {return this.notice;}

    public void searchBook(ArrayList<Book> booklist){
        

		System.out.println("查詢書籍\n1.書籍名稱查詢\n2.書籍ID查詢\n3.列出所有藏書\n4.返回");
		System.out.println("請輸入查詢方法:");
		String searchWay = "";
		String status = "";

		do {
			searchWay = scan.nextLine();// 改成String 比較不會出錯
			if (searchWay.equals("1")) {
				System.out.println("請輸入查詢書籍之名稱:");
				String bookName = scan.nextLine().toLowerCase();
				//int remainNum = 0;// 計算還剩下幾本
				int count = 0;
				
				// 圖書館有這本書
				for (int i = 0; i < booklist.size(); i++) {
					if (booklist.get(i).getName().contains(bookName)) {
						if(booklist.get(i).getHasLended() == 0){status = "在架上";}
						else if(booklist.get(i).getHasLended() == 1){status = "已借出";}
						else if(booklist.get(i).getHasLended() == 2){status = "已預約";}
						else{status = "未知狀態";}
						System.out.printf("書名:%s\t作者:%s\t出版社:%s\tID:%s\t圖書分類:%s\t存放區域:%s\t書籍狀態:%s\n",
								booklist.get(i).getName(), booklist.get(i).getAuthor(),
								booklist.get(i).getPub(), booklist.get(i).getId(), booklist.get(i).getType(),
								booklist.get(i).getAddress(),status);// 列印出所有相同名稱之書籍
					}
					// 圖書館沒有這本書
					else {count++;}
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
						if(booklist.get(i).getHasLended() == 0){status = "在架上";}
						else if(booklist.get(i).getHasLended() == 1){status = "已借出";}
						else if(booklist.get(i).getHasLended() == 2){status = "已預約";}
						else{status = "未知狀態";}
						System.out.printf("書名:%s\t作者:%s\t出版社:%s\tID:%s\t圖書分類:%s\t存放區域:%s\t書籍狀態:%s\n", booklist.get(i).getName(),
								booklist.get(i).getAuthor(), booklist.get(i).getPub(), booklist.get(i).getId(),
								booklist.get(i).getType(), booklist.get(i).getAddress(),status);// 列印出所有相同名稱之書籍
						break;
					}
				}
			} 
            else if (searchWay.equals("3")) {
                for (int i = 0; i < booklist.size(); i++) {
					if(booklist.get(i).getHasLended() == 0){status = "在架上";}
					else if(booklist.get(i).getHasLended() == 1){status = "已借出";}
					else if(booklist.get(i).getHasLended() == 2){status = "已預約";}
					else{status = "未知狀態";}
                    System.out.printf("書名:%s\t作者:%s\t出版社:%s\tID:%s\t圖書分類:%s\t存放區域:%s\t書籍狀態:%s\n", booklist.get(i).getName(),
                            booklist.get(i).getAuthor(), booklist.get(i).getPub(), booklist.get(i).getId(),
                            booklist.get(i).getType(), booklist.get(i).getAddress(),status);// 列印出所有相同名稱之書籍
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
    	System.out.print("請輸入欲更改Member帳號 :");
        String account = s.nextLine();
    	int input = 0;
    	int count = 0;
    	for (int i = 0; i < users.size(); i++) {
    		if(users.get(i).getAccount().equals(account)) {
    			count = 1;
    			while(input != 5) {
    				System.out.print("要更改什麼?\n1.password\n2.name\n3.email\n4.phone\n5.離開\n請輸入: ");
    				String inputs = s.nextLine();
    				input = Integer.parseInt(inputs);
    				switch(input){
    				
    				/* 
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
						*/							
    				
					case 1:
    					System.out.println("請輸入舊密碼 : ");
						String oldPassword = s.nextLine();
						if(users.get(i).getPassword().equals(oldPassword)) {
							System.out.println("請輸入新密碼 : ");
							users.get(i).setPassword(s.nextLine());
    						System.out.println("修改密碼完成!");
    						break;
						}
						else {System.out.println("修改密碼失敗! 舊密碼錯誤");break;}
    					
    				
					case 2:
    					System.out.println("請輸入名稱 : ");
    					users.get(i).setName(s.nextLine());
    					System.out.println("修改名稱完成!");
    					break;
    				    				    			
    				case 3:
    					System.out.println("請輸入email : ");
    					users.get(i).setEmail(s.nextLine());
    					System.out.println("修改email完成!");
    		
    					break;
        
    				case 4:
    					System.out.println("請輸電話 : ");
    					users.get(i).setPhone(s.nextLine());
    					System.out.println("修改電話完成!");
    					break;
    					
    				default :
    					input = 5;
    					System.out.println("離開修改系統!");
    					break;
    				}
    			}
    			break;
    		}
    	}
    	if( count == 0){System.out.println("會員修改失敗! 未找到此會員");}
    }
    public void viewInfo(ArrayList<Users> users, int check){
		System.out.println("使用者名稱:"+users.get(check).getName()+"\n"
				+"身分:"+users.get(check).getIdentity()+"\n"
				+"帳號:"+users.get(check).getAccount()+"\n"
				+"密碼:"+users.get(check).getPassword()+"\n"
				+"電話:"+users.get(check).getPhone()+"\n"
				+"電子信箱:"+users.get(check).getEmail()+"\n"
				+((users.get(check).getIdentity().equals("Admin")) ?  "" : "應繳罰款 : "+users.get(check).getFine()+" 元\n" ));	
		System.out.println("1.編輯資料\n2.清除罰金紀錄\n3.離開");
		String input = "";
		input = scan.nextLine();
		if(input.equals("1")){
			editMember(users);
			viewInfo(users, check);
		}
		else if(input.equals("2")){
			resetFine(users,check);
		}
		else if(input.equals("3")){
			System.out.println("離開查看個人資訊!");
		}
		else {
			System.out.println("輸入錯誤!");
		}
				
	}
	public void checkFine( ArrayList<Users> users, int check) {
		int fine = 0;
		for(int i = 0; i< users.get(check).borrowlist.size(); i++ ) {
			int overduedays =users.get(check).borrowlist.get(i).getReturnDueDate().compareTo(users.get(check).borrowlist.get(i).getBorrowDate());
			if (overduedays > 14) {fine += (overduedays-14) * users.get(check).getFinePerDay();}
		}
		users.get(check).setFine(fine);
	}

	public void deleteUser(ArrayList<Users> users, int check) {
		users.remove(check);
		System.out.println("刪除成功!");
	}

	public abstract void resetFine(ArrayList<Users> users, int check);
	public abstract int getBorrowLimit();
	public abstract int getFinePerDay();
    
}
