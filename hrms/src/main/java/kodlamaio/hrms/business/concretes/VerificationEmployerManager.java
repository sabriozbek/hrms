package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.VerificationCodeEmployerService;
import kodlamaio.hrms.business.abstracts.VerificationEmployerService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.entities.concretes.VerificationCodeEmployer;
@Service
public class VerificationEmployerManager implements VerificationEmployerService{
private VerificationCodeEmployerService verificationCodeEmployerService;
@Autowired
	public VerificationEmployerManager(VerificationCodeEmployerService verificationCodeEmployerService) {

	this.verificationCodeEmployerService = verificationCodeEmployerService;
}

	@Override
	public Result sendVerificationCode(int entityId, String email) {
		 verificationCodeEmployerService.sendVerificationCode(entityId,email);
	        return new SuccessResult();
	}

	@Override
	public DataResult<List<VerificationCodeEmployer>> getAll() {
		return new SuccessDataResult<>(verificationCodeEmployerService.getAll().getData());
	}

	@Override
	public Result verifyAccount(int entityId, String code) {
		 int verificationCodeId = 0;
	        var results = getAll().getData();
	        for (var result : results)
	        {
	            if (entityId==result.getEmployerId() && code.equals(result.getCode())) {
	                verificationCodeId = result.getId();
	                verificationCodeEmployerService.verifyAccount(verificationCodeId, entityId, code);
	                return new SuccessResult();
	            }
	        }

	        return new ErrorResult("Yanlış doğrulama kodu.xsaa"+ entityId+ ""+code);
	       
	    }
	}


