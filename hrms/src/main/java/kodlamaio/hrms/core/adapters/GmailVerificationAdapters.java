package kodlamaio.hrms.core.adapters;

import kodlamaio.hrms.business.abstracts.MailVerificationService;
import kodlamaio.hrms.mailVerification.GmailVerificationService;

public class GmailVerificationAdapters implements MailVerificationService{

	@Override
	public String sendVerificationCode(String email) {
GmailVerificationService service =new GmailVerificationService();
return service.sendVerificationCode(email);

	
	
	}

}
