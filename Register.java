
import java.util.*;

public class Register {
    
    Scanner s = new Scanner(System.in);
    Random r = new Random();
    //SendMail mail = new SendMail();

    public void register(ArrayList<Users> users){
        System.out.print("Your national ID : ");
        String account = s.nextLine();
        System.out.print("Your account password : ");
        String password = s.nextLine();
        System.out.print("Your name : ");
        String name = s.nextLine();
        System.out.print("Your email : ");
        String email = s.nextLine();
        System.out.print("Your phone number: ");
        String phone = s.nextLine();
        System.out.print("Your identity : ");
        String identity = s.nextLine();
        if (account.equals("") || password.equals("") || name.equals("") || email.equals("") || phone.equals("") || identity.equals("")){
            System.out.println("Register failed! You didn't input all information required.");
            
        }
        else if (!identity.equals("Admin") && !identity.equals("Student") && !identity.equals("Teacher") && !identity.equals("Staff")){
            System.out.println("Register failed! Your identity is incorrect.");
            
        }
        else {
            for (int i = 0; i < users.size(); i++){
                if (account.equals(users.get(i).getAccount())){
                    System.out.println("Register failed! This account has been used.");
                    
                }
                
            }
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
            System.out.println("Your verification code has been sent to your email.");
            System.out.print("Please input your verification code : ");
            String input = s.nextLine();
            if (input.equals(codeString)){
                confirm = true;
            }
             
            if (confirm){
                Users user = new Student(account, password, name, email, phone, identity);
                System.out.println("You have successfully registered! You will receive an email notification.");
                text = "<h1>Welomce,  " + name + " </h1><h2> This email notice you that you have succesfully registered in Central Library. </h2><h2>Your Registration information :</h2>Identity : "+ identity +"<br>Account number : " + account + "<br>Email : "+ email + "<br>Phone number : "+ phone  + "<br><br><br>Click <a href=\"https://www.lib.ncu.edu.tw/\">here</a> for more information about Central Library.<br> Click <a href=\"mailto:matthew.in.ncu@g.ncu.edu.tw\">here</a> if you want to contact us." ;
                //mail.send(email, "Your account has been activated", text);
                users.add(user);
            }
            else{
                System.out.println("Register failed! Your verification code is incorrect.");
            }
            */
            Users user = new Student(account, password, name, email, phone, identity);
            System.out.println("You have successfully registered! You will receive an email notification.");
            //text = "<h1>Welomce,  " + name + " </h1><br><h2> This email notice you that you have succesfully registered in Central Library. </h2><br><h2>Your Registration information :</h2><br>Identity : "+ identity +"<br>Account number : " + account + "<br>Email : "+ email + "<br>Phone number : "+ phone  + "<br><br><br>Click <a href=\"https://www.lib.ncu.edu.tw/\">here</a> for more information about Central Library.<br> Click <a href=\"mailto:matthew.in.ncu@g.ncu.edu.tw\">here</a> if you want to contact us." ;
            //mail.send(email, "Your account has been activated", text);
            users.add(user);

        }
        
    }
    
    public int login(ArrayList<Users> users){
        
        System.out.println("Please input your account number : ");
        String account = s.nextLine();
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getAccount().equals(account)){
                System.out.println("Please input your password : ");
                String password = s.nextLine();
                if(users.get(i).getPassword().equals(password)){
                    System.out.println("Login successfully!");
                    return i;
                }
                else{
                    System.out.println("Wrong password!");
                    return -1;
                }
            }
            
        }
        System.out.println("No such account!");
        return -1;
    }
    
}
