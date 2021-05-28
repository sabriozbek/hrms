package kodlamaio.hrms.business.validationRules;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;

import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CandidateDao;
import kodlamaio.hrms.dataAccess.abstracts.UserDao;
import kodlamaio.hrms.entities.concretes.Candidate;
import lombok.experimental.var;

public class CandidateValidator extends UserValidator{
private CandidateDao candidateDao;
private UserDao userDao;

@Autowired
public CandidateValidator(CandidateDao candidateDao, UserDao userDao) {
	
	this.candidateDao = candidateDao;
	this.userDao = userDao;
}


public Result areAllInformationFilledOnRegister(Candidate candidate) {
	
	if (candidate.getIdentityId().equals("")||
			candidate.getBirthYear()==0||
			candidate.getEmail().equals("")||
			candidate.getFirtsName().equals("")||
			candidate.getLastName().equals("")||
			candidate.getIdentityId().equals(""))
			
			{return new ErrorResult("Gerekli bilgileri lütfen eksiksiz giriniz.");
		
	}
	return new SuccessResult();
}

public Result isEmailTaken(String email) {
	var candidates=userDao.findAll();
	for(var candidate:candidates) {
		if (candidate.getEmail().equals(email)) {
			return new ErrorResult("Email zaten kayıtlı.");
		}
	}
		return new SuccessResult();
	
	
}



public Result isIdentityIdTaken(String identityId) {
	var candidates=candidateDao.findAll();
	for(var candidate:candidates) {
		if (candidate.getIdentityId().equals(identityId)) {
			return new ErrorResult("Bu kimlik numarasıyla daha önce kayıt yapılmıştır..");
		}
	}
	
	return new SuccessResult();
	
	
}

}
