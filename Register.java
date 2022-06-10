
import java.util.*;
import javax.swing.*;

public class Register {
    
    Scanner s = new Scanner(System.in);
    Random r = new Random();
    SendMail mail = new SendMail();

    public void register(ArrayList<Users> users){
        boolean repeat = true;
        boolean haveacc = false;
        do{
            String account = JOptionPane.showInputDialog(null, "Your account number(National ID) : ", "Register", JOptionPane.QUESTION_MESSAGE);
            for (int i = 0; i < users.size(); i++){
                if (account.equals(users.get(i).getAccount())){
                    JOptionPane.showMessageDialog(null, "Register failed! This account has been used.", "Register", JOptionPane.ERROR_MESSAGE);
                    haveacc = true;
                    break;
                    
                }

            }
            if(haveacc){haveacc = false;continue;}

            String password = JOptionPane.showInputDialog(null, "Your password : ", "Register", JOptionPane.QUESTION_MESSAGE);
            String confirmpassword = JOptionPane.showInputDialog(null, "Confirm password : ", "Register", JOptionPane.QUESTION_MESSAGE);
            if(!password.equals(confirmpassword)){
                JOptionPane.showMessageDialog(null, "Register failed! Your password didn't match.", "Register", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            String name = JOptionPane.showInputDialog(null, "Your name : ", "Register", JOptionPane.QUESTION_MESSAGE);
            String email = JOptionPane.showInputDialog(null, "Your email : ", "Register", JOptionPane.QUESTION_MESSAGE);
            String phone = JOptionPane.showInputDialog(null, "Your phone number : ", "Register", JOptionPane.QUESTION_MESSAGE);
            String [] option = {"Admin", "Student", "Teacher", "Staff"};
            int input = JOptionPane.showOptionDialog(null, "Your identity : ", "Register", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, null);

            String identity = "";
            if(input == 0){identity = "Admin";}
            else if(input == 1){identity = "Student";}
            else if(input == 2){identity = "Teacher";}
            else if(input == 3){identity = "Staff";}
            else{System.exit(0);}
            
            if (account.equals("") || password.equals("") || name.equals("") || email.equals("") || phone.equals("") || identity.equals("")){
                JOptionPane.showMessageDialog(null, "Register failed! You didn't input all information required.", "Register", JOptionPane.ERROR_MESSAGE);
                repeat = false;
            }
            else if (!identity.equals("Admin") && !identity.equals("Student") && !identity.equals("Teacher") && !identity.equals("Staff")){
                JOptionPane.showMessageDialog(null, "Register failed! Your identity is incorrect.", "Register", JOptionPane.ERROR_MESSAGE);
                repeat = false;
            }
            else {

                /* 
                boolean confirm = false;
                int [] code = new int[6];
                for(int i = 0; i < 6; i++){
                    code[i] = r.nextInt(10);
                }
                String codeString = "";
                for(int i = 0; i < 6; i++){
                    codeString += code[i];
                }
                String text = "<h2>This is your verification code: </h2>" + "<h1>" + codeString + "</h1>";
                mail.send(email,"Welcome to Central Library" ,text);
                JOptionPane.showMessageDialog(null, "Your verification code has been sent to your email.", "Register", JOptionPane.INFORMATION_MESSAGE);
                String pwInput = JOptionPane.showInputDialog(null, "Please input your verification code : ", "Register", JOptionPane.QUESTION_MESSAGE);
                if (pwInput.equals(codeString)){
                    confirm = true;
                }

                if (confirm){
                    Users user = new Student(account, password, name, email, phone, identity);
                    JOptionPane.showMessageDialog(null, "You have successfully registered! You will receive an email notification.", "Register", JOptionPane.INFORMATION_MESSAGE);
                    text = "<h1>Welomce,  " + name + " </h1><h2> This email notice you that you have successfully registered in Central Library. </h2><h2>Your Registration information :</h2>Identity : "+ identity +"<br>Account number : " + account + "<br>Email : "+ email + "<br>Phone number : "+ phone  + "<br><br><br>Click <a href=\"https://www.lib.ncu.edu.tw/\">here</a> for more information about Central Library.<br> Click <a href=\"mailto:matthew.in.ncu@g.ncu.edu.tw\">here</a> if you want to contact us." ;
                    mail.send(email, "Your account has been activated", text);
                    users.add(user);
                    repeat = false;
                }
                else{
                    JOptionPane.showMessageDialog(null, "Register failed! Your verification code is incorrect.", "Register", JOptionPane.ERROR_MESSAGE);
                    repeat = false;
                }
                */
                Users user = new Student(account, password, name, email, phone, identity);
                System.out.println("You have successfully registered! You will receive an email notification.");
                users.add(user);
                repeat = false;

        }
        }while(repeat);
        
    }
    
    public int login(ArrayList<Users> users){
        
        String account = JOptionPane.showInputDialog(null, "Please input your account number : ", "Login", JOptionPane.QUESTION_MESSAGE);
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getAccount().equals(account)){
                String password = JOptionPane.showInputDialog(null, "Please input your password : ", "Login", JOptionPane.QUESTION_MESSAGE);
                if(users.get(i).getPassword().equals(password)){
                    JOptionPane.showMessageDialog(null, "Login successfully!", "Login", JOptionPane.INFORMATION_MESSAGE);
                    return i;
                }
                else{
                    JOptionPane.showMessageDialog(null, "Wrong password!", "Login", JOptionPane.ERROR_MESSAGE);
                    return -1;
                }
            }
            
        }
        JOptionPane.showMessageDialog(null, "No such account!", "Login", JOptionPane.ERROR_MESSAGE);
        return -1;
    }
    
}