import java.util.*;

public class Library {
    public static void main(String [] args){
        System.out.println("歡迎來到央央圖書館借還書系統");

        ArrayList<Users> users = new ArrayList<Users>();//array to store users
        ArrayList<Book> booklist = new ArrayList<Book>();//array to store book
        Scanner s = new Scanner(System.in);
        int input = 0;
        Users user = new Student();
        Register r = new Register();

        users.add(new Student("L123","0000","Owner","test@gmail.com","0987654321","Admin"));
        users.add(new Student("B123","0000","Owner","student@gmail.com","0912345678","Student"));
        booklist.add(new Book("001","little prince","funny","jk rowling","crown pub",0,"A"));
        booklist.add(new Book("002","little prince 2","funny","jk rowling","crown pub",0,"B"));
        booklist.add(new Book("003","Little Red Riding Hood","scary","no idea","test pub",0,"A"));
        
        int check = -1;//check = -1 means user is not login, check != 0 means user is login
        do{
            do{
                System.out.println("\n輸入\n1.登入\n2.註冊\n3.搜尋書籍\n4.離開");
                input = s.nextInt();
                switch(input){
                    case 1:
                        check = r.login(users);
                        break;
                    case 2:
                        r.register(users);
                        break;
                    case 3:
                        user.searchBook(booklist);
                        break;
                    case 4:
                        System.out.println("感謝您的使用");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("輸入錯誤");
                        break;
        
        
                }
            }while(check == -1);
            
            if(users.get(check).getIdentity().equals("Admin")){
                System.out.println("您現在已登入為管理員");
                Admin admin = new Admin();
                do{
                    System.out.println("輸入\n1.新增書籍\n2.修改書籍\n3.刪除書籍\n4.查詢書籍(借還記錄)\n5.查詢會員(借還記錄)\n6.登出\n7.離開\n8.查看個人資訊(修改資料)");
                    input = s.nextInt();
                    switch(input){
                        case 1:
                            admin.addBook(booklist);
                            break;
                        case 2:
                            admin.editBook(booklist);
                            break;
                        case 3:
                            admin.deleteBook(booklist);
                            break;
                        case 4:
                            admin.searchBook(booklist);
                            break;
                        case 5:
                            admin.searchUser(users);
                            break;
                        case 6:
                            check = -1;
                            break;
                        case 7:
                            System.out.println("感謝您的使用");
                            System.exit(0);
                            break;
                        case 8:
                            admin.viewInfo(users,check);
                            break;
                        default:
                            System.out.println("輸入錯誤");
                            break;
                    }
                }while(check != -1);
            }
            else{
                
                
                Member member = new Student();
                if(users.get(check).getIdentity().equals("Student")){
                    member = new Student();
                    System.out.println("登入身分 : 學生");
                }
                else if(users.get(check).getIdentity().equals("Teacher")){
                    member = new Teacher();
                    System.out.println("登入身分 : 教師");
                }
                else if(users.get(check).getIdentity().equals("Staff")){
                    member = new Staff();
                    System.out.println("登入身分 : 職員");
                }
                
                do{
                    System.out.println("輸入\n1.借書\n2.還書\n3.查詢書籍\n4.查看個人資訊(借還記錄)\n5.更改個人資訊\n6.登出\n7.離開");
                    input = s.nextInt();
                    switch(input){
                        case 1:
                            member.borrowBook(booklist);
                            break;
                        case 2:
                            member.returnBook(booklist);
                            break;
                        case 3:
                            member.searchBook(booklist);
                            break;
                        case 4:
                            member.viewInfo(users,check);
                            break;
                        case 5:
                            user.editMember(users);
                        break;
                            
                        case 6:
                            check = -1;
                            break;
                        case 7:
                            System.out.println("感謝您的使用");
                            System.exit(0);
                            break;
                        default:
                            System.out.println("輸入錯誤");
                            break;
                    }
                }while(check != -1);
            }
    
        }while(input != 4);
    }
}

