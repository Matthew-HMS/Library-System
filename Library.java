import java.util.*;
import javax.swing.*;

public class Library {
    public static void main(String [] args){

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
                String [] option = {"登入"," 註冊","查詢書籍","離開系統"};
                input = JOptionPane.showOptionDialog(null, "歡迎來到央央圖書館借還書系統","Central Library", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, option, option[3]);
                switch(input){
                    case 0:
                        check = r.login(users);
                        break;
                    case 1:
                        r.register(users);
                        break;
                    case 2:
                        user.searchBook(booklist);
                        break;
                    case 3:
                        int quit = JOptionPane.showConfirmDialog(null, "確定要離開嗎?","Central Library", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if(quit == 0){JOptionPane.showMessageDialog(null, "感謝您的使用","Exit",JOptionPane.INFORMATION_MESSAGE);System.exit(0);}
                        else{break;}
                    default:
                        System.exit(0);
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
    
        }while(input != 3);
    }
}

