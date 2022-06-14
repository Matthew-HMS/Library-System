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
        users.add(new Student("B123","1111","���p�W","student@gmail.com","0912345678","Student"));
        users.add(new Student("C123","0000","�e�ګ�","teacher@gmail.com","0912312312","Teacher"));
        users.add(new Student("D123","0000","�D�B��","staff@gmail.com","0945645645","Staff"));
        users.add(new Student("E123","0000","®���w","student2@gmail.com","0978978978","Student"));
        users.add(new Student("F123","0000","�]����","student3@gmail.com","0936525844","Student"));
        booklist.add(new Book("001","�p���l","�_�I","�w���U�P�}","�y�ݥX��",0,"A"));
        booklist.add(new Book("002","�p���l2","����","�w���U�P�}","�y�ݥX��",0,"B"));
        booklist.add(new Book("003","�ƺC�P����","����","��","Whitehall",0,"A"));
        booklist.add(new Book("004","���q��","�g����","�I�@�g","�n���L��",0,"A"));
        booklist.add(new Book("005","���Q�i�S�Ю������K��","�_�I","J�PK�Pù�Y","���c�i���B���X����",0,"C"));
        booklist.add(new Book("006","���Q�i�S�Я������]�k��","�_�I","J�PK�Pù�Y","���c�i���B���X����",0,"D"));
        booklist.add(new Book("007","���Q�i�S�Ъ����d�Z���k��","�_�I","J�PK�Pù�Y","���c�i���B���X����",0,"B"));
        booklist.add(new Book("008","�]��","�a��","J�PR�PR�P������","�p�g�X�����q",0,"A"));
        booklist.add(new Book("009","�F���K�X","����","���P����","�ɳ��X��",0,"B"));
        booklist.add(new Book("011","�ֺ�����","�a��","�_�n�D�D��","���y",0,"A"));
        booklist.add(new Book("012","��l�ߺD","����","��i��","�贼",0,"C"));
        booklist.add(new Book("013","�Q�Q�����i��","����","�����@��","���y",0,"A"));
        booklist.add(new Book("014","�p���U","�g����","��L","���n�L��",0,"B"));
        booklist.add(new Book("015","�F�CI","���","�k���J","�j�a�X��",0,"D"));
        booklist.add(new Book("016","�F�CII","���","�k���J","�j�a�X��",0,"D"));
        booklist.add(new Book("017","�զ⥨��","�߲z","�J���","�ӫa",0,"A"));
        booklist.add(new Book("018","���F����","�߲z","���Q","���y",0,"C"));
        booklist.add(new Book("019","�I�����������z","����","ù�k�S","���_",0,"A"));
        booklist.add(new Book("020","��b�C�~","����","�_�n","���y",0,"B"));
        
        int check = -1;//check = -1 means user is not login, check != 0 means user is login
        do{
            do{
                for(int i = 0; i < users.size(); i++){
                    for(int j = 0; j< users.get(i).borrowlist.size(); j++) {
                        int borrowdays =0;
                        LocalDate borrowdate = users.get(i).borrowlist.get(j).getBorrowDate();
                        String text = "<h2> Hello, "+ users.get(i).getName() +" </h2><h2>���l��q���z�ɾ\�����y�Y�N��� </h2>�z���ɾ\���y�� : <br>"+ users.get(i).borrowlist.get(j)  + "<br><br><br>Click <a href=\"https://www.lib.ncu.edu.tw/\">here</a> for more information about Central Library.<br> Click <a href=\"mailto:matthew.in.ncu@g.ncu.edu.tw\">here</a> if you want to contact us." ;
                        while(d.equals(borrowdate) == false) {borrowdate = borrowdate.plusDays(1);borrowdays++;}
                        if (borrowdays == 11) { mail.send(users.get(i).getEmail(), "Your book haven't returned!", text);}
                    }
                }
            	Logger log = LogManager.getLogger(Excel.class.getName());
                String [] option = {"�n�J"," ���U","�d�߮��y","���}�t��"};
                input = JOptionPane.showOptionDialog(null, "�w��Ө쥡���Ϯ��]���ٮѨt��","Central Library", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, option, option[3]);
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
                        int quit = JOptionPane.showConfirmDialog(null, "�T�w�n���}��?","Central Library", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if(quit == 0){JOptionPane.showMessageDialog(null, "�P�±z���ϥ�","Exit",JOptionPane.INFORMATION_MESSAGE);System.exit(0);}
                        else{break;}
                    default:
                        System.exit(0);
                        break;
                }
            }while(check == -1);
            
            if(users.get(check).getIdentity().equals("Admin")){
                Admin admin = new Admin();
                if(users.get(check).getNotice() != "") {JOptionPane.showMessageDialog(null, "���� : "+users.get(check).getNotice());}
                do{
                    String [] option = {"�s�W���y","�ק���y","�R�����y","�d�߮��y","�d�߷|��(���ٰO��)","�d�ݡB���ӤH��T","�n�X","���}�t��"};
                    input = JOptionPane.showOptionDialog(null, "�w��^�ӡA" + users.get(check).getName() + "\n�n�J���� : �޲z��" , "Central Library", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, option, option[7]);
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
                            int quit = JOptionPane.showConfirmDialog(null, "�T�w�n���}��?","Central Library", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                            if(quit == 0){JOptionPane.showMessageDialog(null, "�P�±z���ϥ�","Exit",JOptionPane.INFORMATION_MESSAGE);System.exit(0);}
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
                if(users.get(check).getNotice() != "" && users.get(check).getFine() == 0) {JOptionPane.showMessageDialog(null, "���� : "+ users.get(check).getNotice(), "Central Library", JOptionPane.INFORMATION_MESSAGE);}
                else if(users.get(check).getNotice() == "" && users.get(check).getFine() != 0) {JOptionPane.showMessageDialog(null, "���� : �z�����y�O�����k�� ��ú�ǻ@�� : "+Integer.toString(users.get(check).getFine())+"�� �p��ú�M�@���N�L�k�ɾ\���y!", "Central Library", JOptionPane.ERROR_MESSAGE);}
                else if(users.get(check).getNotice() != "" && users.get(check).getFine() != 0) {JOptionPane.showMessageDialog(null, "���� : "+ users.get(check).getNotice()+"\n�z�����y�O�����k�� ��ú�ǻ@�� : "+Integer.toString(users.get(check).getFine())+"�� �p��ú�M�@���N�L�k�ɾ\���y!","Central Library", JOptionPane.ERROR_MESSAGE);}
                do{
                    String [] option = {"�ɮ�","�ٮ�","�����w�����y","�d�߮��y","�d�ݡB���ӤH��T","�R���b��","�n�X","���}�t��"};
                    users.get(check).checkFine(users, check);
                    input = JOptionPane.showOptionDialog(null, "�w��^�ӡA " + users.get(check).getName() + "\n������ : "+ LocalDate.now() +"\n�n�J���� : " + users.get(check).getIdentity() , "Central Library", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, option, option[7]);
                    switch(input){
                        case 0:
                            if(users.get(check).getFine() ==0 && (users.get(check).borrowlist.size()<users.get(check).getBorrowLimit())) {member.borrowBook(booklist, lineup, users.get(check));}
                            else if(users.get(check).getFine()!=0){JOptionPane.showMessageDialog(null, "�z���@����ú �Х�ú�ǫ�l�o��_�ɾ\�\��!");}
                            else if(users.get(check).borrowlist.size()>=users.get(check).getBorrowLimit()){JOptionPane.showMessageDialog(null, "�z�w�W�V�ɮѤW�� �Х��ٮ�!");}
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
                            int quit = JOptionPane.showConfirmDialog(null, "�T�w�n���}��?","Central Library", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                            if(quit == 0){JOptionPane.showMessageDialog(null, "�P�±z���ϥ�","Exit",JOptionPane.INFORMATION_MESSAGE);System.exit(0);}
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