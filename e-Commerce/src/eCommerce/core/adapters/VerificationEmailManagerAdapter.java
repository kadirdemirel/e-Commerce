package eCommerce.core.adapters;

import eCommerce.core.abstracts.VerificationEmailService;
import eCommerce.verificaitonEmail.VerificationEmailManager;

public class VerificationEmailManagerAdapter implements VerificationEmailService {

	VerificationEmailManager email = new VerificationEmailManager();

	@Override
	public Boolean verificationEmail(String message) {
		email.verificationEmail(message);
		return true;
	

	}

}
