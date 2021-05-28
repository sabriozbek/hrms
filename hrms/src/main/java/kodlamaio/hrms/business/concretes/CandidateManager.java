package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.AuthenticationService;
import kodlamaio.hrms.business.abstracts.CandidateService;
import kodlamaio.hrms.business.abstracts.VerificationCandidateService;
import kodlamaio.hrms.business.validationRules.CandidateValidator;
import kodlamaio.hrms.core.adapters.MernisServiceAdapter;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CandidateDao;
import kodlamaio.hrms.dataAccess.abstracts.UserDao;
import kodlamaio.hrms.entities.concretes.Candidate;

@Service
public class CandidateManager implements CandidateService{
	private CandidateDao candidateDao;
	private UserDao userDao;
	private VerificationCandidateService verificationCandidateService;

	@Autowired
	public CandidateManager(CandidateDao candidateDao, UserDao userDao,
			VerificationCandidateService verificationCandidateService) {
		
		this.candidateDao = candidateDao;
		this.userDao = userDao;
		this.verificationCandidateService = verificationCandidateService;
	}

	@Override
	public Result registeResult(Candidate entity) {

		CandidateValidator validator=new CandidateValidator(candidateDao, userDao);
		Result[] validators =new Result[] {
				validator.areAllInformationFilledOnRegister(entity),
				validator.isEmailTaken(entity.getEmail()),
				validator.arePasswordSame(entity.getPassword(), entity.getPasswordRepat()),
				validator.isIdentityIdTaken(entity.getIdentityId()),
				validator.isEmailValid(entity.getEmail())
		};
		for(var item:validators) {
			if(!item.isSuccess()) {
				return new ErrorResult(item.getMessage());
			}
		}
		
		AuthenticationService authenticationService=new AuthenticationManager(new MernisServiceAdapter()); 
		if(!authenticationService.checkIfRealPerson
				(entity.getIdentityId(), entity.getFirtsName(), entity.getLastName(), entity.getBirthYear()).isSuccess())	{
			return new ErrorResult("Kullanıcı geçersiz.");
		}
			candidateDao.save(entity);
			verificationCandidateService.sendVerificationCode(entity.getId(),entity.getEmail());
		return new SuccessResult("Kayıt başarıyla oluşturulmuştur: "+entity.getEmail()+" adresine gelen onay kodu ile hesabınızı doğrulayınız.");
	}

	@Override
	public DataResult<List<Candidate>> getAll() {
		// TODO Auto-generated method stub
		return new SuccessDataResult<List<Candidate>>(candidateDao.findAll());
	}

	@Override
	public Result verifyAccountByVerificationCode(String email, String code) {
		 var entities = getAll().getData();
	        Candidate candidate = null;

	        for (var item : entities) {
	            if (item.getEmail().equals(email))
	                candidate = item;
	        }

	        var result = verificationCandidateService.verifyAccount(candidate.getId(),code);
	        if (!result.isSuccess())
	            return result;

	        return new SuccessResult("Email doğrulama başarılı.");

	    }
	

}
