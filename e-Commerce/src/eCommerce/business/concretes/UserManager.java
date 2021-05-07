package eCommerce.business.concretes;

import eCommerce.business.abstracts.UserService;
import eCommerce.core.abstracts.GoogleAccountService;
import eCommerce.core.abstracts.VerificationEmailService;
import eCommerce.dataAccess.abstracts.UserDao;
import eCommerce.entities.concretes.User;

public class UserManager implements UserService {
	UserDao userDao;
	VerificationEmailService verificationEmailService;
	GoogleAccountService googleAccountService;

	public UserManager(UserDao userDao, VerificationEmailService verificationEmailService,
			GoogleAccountService googleAccountService) {
		this.userDao = userDao;
		this.verificationEmailService = verificationEmailService;
		this.googleAccountService = googleAccountService;
	}

	@Override
	public void login(User user, String email, String password) {

		if (validasyonEmail(user.getEmail())) {
			if (validasyonEmailOrPassword(email, password)) {
				this.userDao.login(user);

			} else {
				System.out.println("Kullanýcý adý veya parola hatalý");
			}
		}

		else {
			System.out.println("Lütfen geçerli bir email giriniz.");
			return;
		}

	}

	@Override
	public void signUpGoogle(User user, String string) {

		if (googleAccountLogin()) {
			if (validasyonEmail(user.getEmail())) {
				if (checkIfEmail(user.getEmail(), string)) {
					if (user.getPassword().length() >= 6 && user.getName().length() >= 2
							&& user.getLastName().length() >= 2 && user.getName() != null && user.getLastName() != null
							&& user.getEmail() != null && user.getPassword() != null) {

						if (verificationLink()) {
							this.userDao.signUp(user);

						} else {
							System.out.println("Kullanýcý doðrulama linkine týklamadýðý için kaydý geçersiz sayýldý.");
						}

					} else {
						System.out.println("Hatalý giriþ yaptýnýz, lütfen tekrar deneyiniz.");
						return;
					}

				} else {
					System.out.println("Kayýtlý email girdiniz, lütfen farklý bir email giriniz.");
					return;
				}

			} else {
				System.out.println("Lütfen geçerli bir email giriniz.");
				return;
			}
		} else {
			System.out.println("Google ile giriþ baþarýsýz.");
		}
	}

	@Override
	public void signUp(User user, String string) {
		if (validasyonEmail(user.getEmail())) {
			if (checkIfEmail(user.getEmail(), string)) {
				if (user.getPassword().length() >= 6 && user.getName().length() >= 2 && user.getLastName().length() >= 2
						&& user.getName() != null && user.getLastName() != null && user.getEmail() != null
						&& user.getPassword() != null) {

					if (verificationLink()) {

						this.userDao.signUp(user);
					} else {
						System.out.println("Kullanýcý doðrulama linkine týklamadýðý için kaydý geçersiz sayýldý.");
					}

				} else {
					System.out.println("Hatalý giriþ yaptýnýz, lütfen tekrar deneyiniz.");
					return;
				}

			} else {
				System.out.println("Kayýtlý email girdiniz, lütfen farklý bir email giriniz.");
				return;
			}

		} else {
			System.out.println("Lütfen geçerli bir email giriniz.");
			return;
		}

	}

	private boolean validasyonEmail(String email) {
		int at;
		int nokta;

		boolean at_var = true;
		boolean nokta_var = true;
		boolean emailgirildi = true;

		if (email.length() == 0) {
			emailgirildi = false;
		}

		at = email.indexOf("@", 1);

		if (at == -1) {
			at_var = false;
		}

		nokta = email.indexOf(".", 1);

		if (nokta == -1) {
			nokta_var = false;
		}

		if (emailgirildi & at_var & nokta_var) {
			return true;
		} else {
			return false;
		}
	}

	private boolean checkIfEmail(String email, String string) {
		if (email != string) {
			return true;
		} else {
			return false;
		}
	}

	private boolean verificationLink() {
		Boolean aBoolean = this.verificationEmailService.verificationEmail("Link kullanýcýya ulaþtý.");
		if (aBoolean) {
			System.out.println("Kullanýcý doðrulama linkine týkladý.");
			return true;
		} else {

			return false;
		}
	}

	private boolean googleAccountLogin() {
		Boolean aBoolean = this.googleAccountService.googleAccount("Google ile kayýt seçeneði seçildi.");
		if (aBoolean) {

			return true;
		} else {

			return false;
		}
	}

	private boolean validasyonEmailOrPassword(String email, String password) {
		if (email != null && password != null) {
			return true;

		} else {
			return false;
		}
	}

}
