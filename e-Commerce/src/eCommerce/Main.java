package eCommerce;

import java.util.ArrayList;
import java.util.Scanner;

import org.graalvm.compiler.core.common.cfg.Loop;

import com.sun.org.apache.bcel.internal.generic.GOTO;

import eCommerce.business.abstracts.UserService;
import eCommerce.business.concretes.UserManager;
import eCommerce.core.adapters.GoogleAccountAdapter;
import eCommerce.core.adapters.VerificationEmailManagerAdapter;
import eCommerce.dataAccess.concretes.HiberNateUserDao;
import eCommerce.entities.concretes.User;

public class Main {

	public static void main(String[] args) {

		UserService userService = new UserManager(new HiberNateUserDao(), new VerificationEmailManagerAdapter(),
				new GoogleAccountAdapter());

		User user = new User(1, "Kadir", "Demirel", "kadirdemirel33@gmail.com", "kadir1234");
		User user2 = new User(2, "Kadir", "Demirel", "kadirdemirel34@gmail.com", "kadir1234");
		User user3 = new User(3, "Kadir", "Demirel", "kadirdemirel25@gmail.com", "kadir1234");

		ArrayList<User> userList = new ArrayList<User>();
		userList.add(user);
		userList.add(user2);

		int secim = 0;
		String email, password;

		Scanner scanner = new Scanner(System.in);
		System.out.println("1-Giriþ Yap");
		System.out.println("2-Google ile kayýt ol");
		System.out.println("3-Kayýt ol");
		System.out.println("4-Kullanýcý Listesi");

		secim = scanner.nextInt();
		switch (secim) {
		case 1: {
			System.out.println("Email giriniz");
			email = scanner.next();
			System.out.println("Þifre giriniz");
			password = scanner.next();

			userService.login(user2,email, password);

			break;
		}
		case 2: {
			userService.signUpGoogle(user3, userList.get(0).getEmail());

			break;
		}
		case 3: {
			userService.signUp(user3, userList.get(0).getEmail());
			break;
		}
		case 4: {
			userList.add(user3);

			for (User users : userList) {
				System.out.println(users.getName() + " " + users.getLastName() + " " + users.getEmail());

			}
			break;
		}

		default:
			System.out.println("Yanlýþ Seçim !");
			break;
		}
	}

}
