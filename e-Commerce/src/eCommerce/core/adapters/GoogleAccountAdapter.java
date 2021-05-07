package eCommerce.core.adapters;

import eCommerce.core.abstracts.GoogleAccountService;
import eCommerce.googleAccount.GoogleAccountManager;

public class GoogleAccountAdapter implements GoogleAccountService {

	GoogleAccountManager googleAccountManager=new GoogleAccountManager();
	@Override
	public boolean googleAccount(String message) {
		googleAccountManager.googleAccount(message);
		return true;
	}

}
