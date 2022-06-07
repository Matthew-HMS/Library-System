package Try;

import java.util.ArrayList;

import Library.Users;

public class viewInfo {
	public void viewInfo(ArrayList<Users> users, int check){
		System.out.println("使用者名稱:"+users.get(check).getName()+"\n"
				+"身分:"+users.get(check).getIdentity()+"\n"
				+"帳號:"+users.get(check).getAccount()+"\n"
				+"密碼:"+users.get(check).getPassword()+"\n"
				+"電話:"+users.get(check).getPhone()+"\n"
				+"電子信箱:"+users.get(check).getEmail()+"\n");	
	}
}
