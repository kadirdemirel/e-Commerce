package eCommerce.dataAccess.concretes;

import eCommerce.dataAccess.abstracts.UserDao;
import eCommerce.entities.concretes.User;

public class HiberNateUserDao implements UserDao {


	@Override
	public void signUp(User user) {
		System.out.println(user.getName() + " " + user.getLastName() + " adl� kullan�c� sisteme kay�t oldu.");

		
	}

	@Override
	public void login(User user) {
		System.out.println(user.getName()+" "+user.getLastName()+" adl� kullan�c� sisteme giri� yapt�.");
		
	}

}
