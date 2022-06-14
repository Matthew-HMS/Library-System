import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.*;
import javax.swing.*;
import org.apache.logging.log4j.*;


public class Library {
    public static void main(String [] args) throws Exception{

        ArrayList<Users> users = new ArrayList<Users>();//array to store users
        ArrayList<Book> booklist = new ArrayList<Book>();//array to store book
        ArrayList<Book>lineup = new ArrayList<Book>();//array to store the book line up by someone
        ArrayList<String> askforresetfine = new ArrayList<String>(); 

        int input = 0;
        Users user = new Student();
        Register r = new Register();
        SendMail mail = new SendMail();
        LocalDate d = LocalDate.now();
        users.add(new Student("L123","0000","Owner","test@gmail.com","0987654321","Admin"));
        users.add(new Student("B123","1111","王小名","student@gmail.com","0912345678","Student"));
        users.add(new Student("C123","0000","河夢咻","teacher@gmail.com","0912312312","Teacher"));
        users.add(new Student("D123","0000","磺伯汗","staff@gmail.com","0945645645","Staff"));
        users.add(new Student("E123","0000","簧罐針","student2@gmail.com","0978978978","Student"));
        users.add(new Student("F123","0000","夜辛皿","student3@gmail.com","0936525844","Student"));
        booklist.add(new Book("001","小王子","冒險","安托萬·迪","尖端出版",0,"A"));
        booklist.add(new Book("002","小王子2","有趣","安托萬·迪","尖端出版",0,"B"));
        booklist.add(new Book("003","傲慢與偏見","知識","珍","Whitehall",0,"A"));
        booklist.add(new Book("004","水滸傳","經典文學","施耐庵","南海印刷",0,"A"));
        booklist.add(new Book("005","哈利波特－消失的密室","冒險","J·K·羅琳","布盧姆茨伯里出版社",0,"C"));
        booklist.add(new Book("006","哈利波特－神秘的魔法石","冒險","J·K·羅琳","布盧姆茨伯里出版社",0,"D"));
        booklist.add(new Book("007","哈利波特－阿茲卡班的逃犯","冒險","J·K·羅琳","布盧姆茨伯里出版社",0,"B"));
        booklist.add(new Book("008","魔戒","懸疑","J·R·R·托爾金","聯經出版公司",0,"A"));
        booklist.add(new Book("009","達文西密碼","恐怖","丹·布朗","時報出版",0,"B"));
        booklist.add(new Book("011","福爾摩斯","懸疑","柯南．道爾","遠流",0,"A"));
        booklist.add(new Book("012","原子習慣","知識","詹姆斯","方智",0,"C"));
        booklist.add(new Book("013","被討厭的勇氣","知識","岸見一郎","遠流",0,"A"));
        booklist.add(new Book("014","小紅帽","經典文學","格林","海南印刷",0,"B"));
        booklist.add(new Book("015","沙丘I","科幻","法蘭克","大家出版",0,"D"));
        booklist.add(new Book("016","沙丘II","科幻","法蘭克","大家出版",0,"D"));
        booklist.add(new Book("017","白色巨塔","心理","侯文詠","皇冠",0,"A"));
        booklist.add(new Book("018","心靈雞湯","心理","戴利","遠流",0,"C"));
        booklist.add(new Book("019","富爸爸有錢有理","知識","羅勃特","高寶",0,"A"));
        booklist.add(new Book("020","協槓青年","知識","柯南","遠流",0,"B"));
        
        int check = -1;//check = -1 means user is not login, check != 0 means user is login
        do{
            do{
                for(int i = 0; i < users.size(); i++){
                    for(int j = 0; j< users.get(i).borrowlist.size(); j++) {
                        int borrowdays =0;
                        LocalDate borrowdate = users.get(i).borrowlist.get(j).getBorrowDate();
                        String text = "<h2> Hello, "+ users.get(i).getName() +" </h2><h2>此郵件通知您借閱的書籍即將到期 </h2>您的借閱書籍為 : <br>"+ users.get(i).borrowlist.get(j)  + "<br><br><br>Click <a href=\"https://www.lib.ncu.edu.tw/\">here</a> for more information about Central Library.<br> Click <a href=\"mailto:matthew.in.ncu@g.ncu.edu.tw\">here</a> if you want to contact us." ;
                        while(d.equals(borrowdate) == false) {borrowdate = borrowdate.plusDays(1);borrowdays++;}
                        if (borrowdays == 11) { mail.send(users.get(i).getEmail(), "Your book haven't returned!", text);}
                    }
                }
            	Logger log = LogManager.getLogger(Excel.class.getName());
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
                Admin admin = new Admin();
                if(users.get(check).getNotice() != "") {JOptionPane.showMessageDialog(null, "提醒 : "+users.get(check).getNotice());}
                do{
                    String [] option = {"新增書籍","修改書籍","刪除書籍","查詢書籍","查詢會員(借還記錄)","查看、更改個人資訊","登出","離開系統"};
                    input = JOptionPane.showOptionDialog(null, "歡迎回來，" + users.get(check).getName() + "\n登入身分 : 管理員" , "Central Library", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, option, option[7]);
                    switch(input){
                        case 0:
                            admin.addBook(booklist);
                            break;
                        case 1:
                            admin.editBook(booklist);
                            break;
                        case 2:
                            admin.deleteBook(booklist);
                            break;
                        case 3:
                            admin.searchBook(booklist);
                            break;
                        case 4:
                            admin.searchMember(users, askforresetfine);
                            break;
                        case 5:
                            admin.viewInfo(users,askforresetfine,check);
                            break;
                        case 6:
                            check = -1;
                            break;
                        case 7:
                            int quit = JOptionPane.showConfirmDialog(null, "確定要離開嗎?","Central Library", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                            if(quit == 0){JOptionPane.showMessageDialog(null, "感謝您的使用","Exit",JOptionPane.INFORMATION_MESSAGE);System.exit(0);}
                            else{break;}
                        default:
                            System.exit(0);
                            break;
                    }
                }while(check != -1);
            }
            else{

                Member member = new Student();

                if(users.get(check).getIdentity().equals("Student")){
                    member = new Student();
                    
                    
                }
                else if(users.get(check).getIdentity().equals("Teacher")){
                    member = new Teacher();
                    
                }
                else if(users.get(check).getIdentity().equals("Staff")){
                    member = new Staff();
                   
                }

                users.get(check).checkFine(users, check);
                if(users.get(check).getNotice() != "" && users.get(check).getFine() == 0) {JOptionPane.showMessageDialog(null, "提醒 : "+ users.get(check).getNotice(), "Central Library", JOptionPane.INFORMATION_MESSAGE);}
                else if(users.get(check).getNotice() == "" && users.get(check).getFine() != 0) {JOptionPane.showMessageDialog(null, "提醒 : 您有書籍逾期未歸還 需繳納罰金 : "+Integer.toString(users.get(check).getFine())+"元 如未繳清罰金將無法借閱書籍!", "Central Library", JOptionPane.ERROR_MESSAGE);}
                else if(users.get(check).getNotice() != "" && users.get(check).getFine() != 0) {JOptionPane.showMessageDialog(null, "提醒 : "+ users.get(check).getNotice()+"\n您有書籍逾期未歸還 需繳納罰金 : "+Integer.toString(users.get(check).getFine())+"元 如未繳清罰金將無法借閱書籍!","Central Library", JOptionPane.ERROR_MESSAGE);}
                do{
                    String [] option = {"借書","還書","取消預約書籍","查詢書籍","查看、更改個人資訊","刪除帳號","登出","離開系統"};
                    input = JOptionPane.showOptionDialog(null, "歡迎回來， " + users.get(check).getName() + "\n今日日期 : "+ LocalDate.now() +"\n登入身分 : " + users.get(check).getIdentity() , "Central Library", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, option, option[7]);
                    switch(input){
                        case 0:
                            if(users.get(check).getFine() ==0 && (users.get(check).borrowlist.size()<users.get(check).getBorrowLimit())) {member.borrowBook(booklist, lineup, users.get(check));}
                            else if(users.get(check).getFine()!=0){JOptionPane.showMessageDialog(null, "您有罰金未繳 請先繳納後始得恢復借閱功能!");}
                            else if(users.get(check).borrowlist.size()>=users.get(check).getBorrowLimit()){JOptionPane.showMessageDialog(null, "您已超越借書上限 請先還書!");}
                            break;
                        case 1:
                            member.returnBook(booklist, lineup, users.get(check));
                            break;
                        case 2:
                            member.cancelReserve(lineup,users.get(check));
                            break;
                        case 3:
                            member.searchBook(booklist);
                            break;
                        case 4:
                            member.viewInfo(users,askforresetfine,check);
                            break;
                        case 5:
                        	member.deleteUser(users, check);
                        case 6:
                            check = -1;
                            break;
                        case 7:
                            int quit = JOptionPane.showConfirmDialog(null, "確定要離開嗎?","Central Library", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                            if(quit == 0){JOptionPane.showMessageDialog(null, "感謝您的使用","Exit",JOptionPane.INFORMATION_MESSAGE);System.exit(0);}
                            else{break;}
                        default:
                            System.exit(0);
                            break;
                    }
                }while(check != -1);
            }
    
        }while(input != 3);
    }
}
