package kodlamaio.hrms.business.concretes;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.MailVerificationService;
import kodlamaio.hrms.business.abstracts.VerificationCodeEmployerService;
import kodlamaio.hrms.core.adapters.GmailVerificationAdapters;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.VerificationCodeEmployerDao;
import kodlamaio.hrms.entities.concretes.VerificationCodeEmployer;
import lombok.experimental.var;
@Service
public class VerificationCodeEmployerManager implements VerificationCodeEmployerService{
private VerificationCodeEmployerDao entityDao;
@Autowired
	public VerificationCodeEmployerManager(VerificationCodeEmployerDao entityDao) {
	
	this.entityDao = entityDao;
}

	@Override
	public Result sendVerificationCode(int entityId, String email) {
		MailVerificationService service= new GmailVerificationAdapters();
		var code=service.sendVerificationCode(email);
		
		VerificationCodeEmployer verificationCode = new VerificationCodeEmployer();
        verificationCode.setCode(code);
        verificationCode.setEmployerId(entityId);
        verificationCode.setVerifiedDate(LocalDateTime.now());

        entityDao.save(verificationCode);
        return new SuccessResult();
		
	}

	@Override
	public DataResult<List<VerificationCodeEmployer>> getAll() {
		// TODO Auto-generated method stub
		return new SuccessDataResult<>(entityDao.findAll());
	}

	@Override
	public Result verifyAccount(int verificationCodeId, int entityId, String code) {
		 var verificationCodes = getAll().getData();
	        VerificationCodeEmployer verificationCode = null;
	        for (var item : verificationCodes){
	            if (item.getId()==verificationCodeId)
	                verificationCode = item;
	        }

	        verificationCode.setVerified(true);
	        entityDao.save(verificationCode);

	        return new ErrorResult();
	}

}
