package Try;

import java.util.ArrayList;

import Library.Users;

public class viewInfo {
	public void viewInfo(ArrayList<Users> users, int check){
		System.out.println("�ϥΪ̦W��:"+users.get(check).getName()+"\n"
				+"����:"+users.get(check).getIdentity()+"\n"
				+"�b��:"+users.get(check).getAccount()+"\n"
				+"�K�X:"+users.get(check).getPassword()+"\n"
				+"�q��:"+users.get(check).getPhone()+"\n"
				+"�q�l�H�c:"+users.get(check).getEmail()+"\n");	
	}
}
