package eCommerce.business.abstracts;



import eCommerce.entities.concretes.User;

public interface UserService {
	void signUp(User user,String string);
	void signUpGoogle(User user,String string);
	void login(User user,String email,String password);


}
