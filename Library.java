import java.util.*;

public class Library {
    public static void main(String [] args){
        System.out.println("歡迎來到央央圖書館借還書系統");

        ArrayList<Users> users = new ArrayList<Users>();//array to store users
        Scanner s = new Scanner(System.in);
        int input = 0;
        Users user = new Users();
        Register r = new Register();
        int check = -1;//check = -1 means user is not login, check != 0 means user is login
        do{
            do{
                System.out.println("\n輸入\n1.登入\n2.註冊\n3.搜尋書籍\n4.離開");
                input = s.nextInt();
                switch(input){
                    case 1:
                        check = r.login();
                        break;
                    case 2:
                        user = r.register();
                        if (user != null){
                            users.add(user);
                        }
                        break;
                    case 3:
                        user.searchBook();
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
                System.out.println("輸入\n1.新增書籍\n2.修改書籍\n3.刪除書籍\n4.搜尋書籍\n5.查看個人資訊(所有借還記錄)\n6.登出\n7.離開");
                input = s.nextInt();
                do{
                    switch(input){
                        case 1:
                            user.addBook();
                            break;
                        case 2:
                            user.EditBook();
                            break;
                        case 3:
                            user.DeleteBook();
                            break;
                        case 4:
                            user.searchBook();
                            break;
                        case 5:
                            user.viewInfo();
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
            else{
                
                System.out.println("您現在已登入為會員");
                System.out.println("輸入\n1.借書\n2.還書\n3.搜尋書籍\n4.查看個人資訊(借還記錄)\n5.登出\n6.離開");
                input = s.nextInt();
                
                if(users.get(check).getIdentity().equals("Student")){user = new Student();}
                else if(users.get(check).getIdentity().equals("Teacher")){user = new Teacher();}
                else if(users.get(check).getIdentity().equals("Staff")){user = new Staff();}
                
                do{
                    switch(input){
                        case 1:
                            user.borrowBook();
                            break;
                        case 2:
                            user.returnBook();
                            break;
                        case 3:
                            user.searchBook();
                            break;
                        case 4:
                            user.viewInfo();
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
