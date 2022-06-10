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
        booklist.add(new Book("001","小王子","adventure","安托萬·迪·聖-修伯里"," Reynal & Hitchcock",0,"A"));
        booklist.add(new Book("002","小王子2","funny","安托萬·迪·聖-修伯里"," Reynal & Hitchcock",0,"B"));
        booklist.add(new Book("003","傲慢與偏見","	self-knowledge","珍·奥斯汀","Whitehall",0,"A"));
        booklist.add(new Book("004","水滸傳","classic","施耐庵","南海印刷",0,"A"));
        booklist.add(new Book("005","哈利波特－消失的密室","adventure","J·K·羅琳","布盧姆茨伯里出版社",0,"C"));
        booklist.add(new Book("006","哈利波特－神秘的魔法石","adventure","J·K·羅琳","布盧姆茨伯里出版社",0,"D"));
        booklist.add(new Book("007","哈利波特－阿茲卡班的逃犯","adventure","J·K·羅琳","布盧姆茨伯里出版社",0,"B"));
        booklist.add(new Book("008","魔戒","mystery","J·R·R·托爾金","聯經出版公司",0,"A"));
        booklist.add(new Book("009","達文西密碼","scary","丹·布朗","時報出版",0,"B"));
        booklist.add(new Book("010","福爾摩斯","detective","亞瑟．柯南．道爾","遠流",0,"A"));
        
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
                    System.out.println("輸入\n1.新增書籍\n2.修改書籍\n3.刪除書籍\n4.查詢書籍(借還記錄)\n5.查詢會員(借還記錄)\n6.登出\n7.離開\n8.查看、更改個人資訊");
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
                            admin.searchMember(users);
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
                    System.out.println("輸入\n1.借書\n2.還書\n3.查詢書籍\n4.查看、更改個人資訊(借還記錄)\n5.登出\n6.離開");
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
                            check = -1;
                            break;
                        case 6:
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

