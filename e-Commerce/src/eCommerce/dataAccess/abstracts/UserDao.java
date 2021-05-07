package eCommerce.dataAccess.abstracts;

import eCommerce.entities.concretes.User;

public interface UserDao {
	
	void signUp(User user);
	void login(User user);
	

}
