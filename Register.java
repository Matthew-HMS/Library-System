import java.util.*;

public class Register {//can't repeat account
    
    //private Users users [] = new Users[30];
    Scanner s = new Scanner(System.in);

    public Users register(ArrayList<Users> users){
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
            return null;
        }
        else if (!identity.equals("Admin") && !identity.equals("Student") && !identity.equals("Teacher") && !identity.equals("Staff")){
            System.out.println("Register failed! Your identity is incorrect.");
            return null;
        }
        else {
            for (int i = 0; i < users.size(); i++){
                if (account.equals(users.get(i).getAccount())){
                    System.out.println("Register failed! This account has been used.");
                    return null;
                }
                
            }
            Users user = new Student(account, password, name, email, phone, identity);
            System.out.println("You have successfully registered!");
            return user;
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
