package kodlamaio.hrms.business.concretes;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.MailVerificationService;
import kodlamaio.hrms.business.abstracts.VerificationCodeCandidateService;
import kodlamaio.hrms.core.adapters.GmailVerificationAdapters;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.VerificationCodeCandidateDao;
import kodlamaio.hrms.entities.concretes.VerificationCodeCandidate;
@Service
public class VerificationCodeCandidateManager implements VerificationCodeCandidateService{
private VerificationCodeCandidateDao entityDao;
@Autowired
	public VerificationCodeCandidateManager(VerificationCodeCandidateDao entityDao) {
	this.entityDao = entityDao;
}

	@Override
	public Result sendVerificationCode(int entityId, String email) {
		//Mail doğrulama
		MailVerificationService service = new GmailVerificationAdapters();
        var code = service.sendVerificationCode(email);

        VerificationCodeCandidate verificationCode = new VerificationCodeCandidate();
        verificationCode.setCode(code);
        verificationCode.setCandidateId(entityId);
        verificationCode.setVerifiedDate(LocalDateTime.now());

        entityDao.save(verificationCode);
        return new SuccessResult();
	}

	@Override
	public DataResult<List<VerificationCodeCandidate>> getAll() {
		// TODO Auto-generated method stub
		return new SuccessDataResult<>(entityDao.findAll());
	}

	@Override
	public Result verifyAccount(int verificationCodeId, int entityId, String code) {
		var verificationCodes = getAll().getData();
        VerificationCodeCandidate verificationCode = null;
        for (var item : verificationCodes){
            if (item.getId()==verificationCodeId)
                verificationCode = item;
        }

        verificationCode.setVerified(true);
        entityDao.save(verificationCode);

        return new ErrorResult();
	}

}
