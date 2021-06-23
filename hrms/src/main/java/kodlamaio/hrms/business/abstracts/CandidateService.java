package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.concretes.Photo;

public interface CandidateService extends UserService<Candidate>{
	
	Result add(String email, String password, String passwordRepat, String firtsName, String lastName,
			String identityId, int birthYear);
	Result verifyAccountByVerificationCode(String email,String code);
}
