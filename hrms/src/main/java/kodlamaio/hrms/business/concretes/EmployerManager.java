package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.business.abstracts.VerificationEmployerService;
import kodlamaio.hrms.business.validationRules.EmployerValidator;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.entities.concretes.Employer;
@Service
public class EmployerManager implements EmployerService{
private EmployerDao employerDao;
private VerificationEmployerService verificationEmployerService;
@Autowired
	public EmployerManager(EmployerDao employerDao, VerificationEmployerService verificationEmployerService) {

	this.employerDao = employerDao;
	this.verificationEmployerService = verificationEmployerService;
}

	@Override
	public Result registeResult(Employer entity) {
		 EmployerValidator validator = new EmployerValidator(employerDao);
	        Result[] validators = new Result[]{
	                validator.areAllInformationFilledOnRegister(entity),
	                validator.isEmailTaken(entity.getEmail()),
	                validator.arePasswordSame(entity.getPassword(), entity.getPasswordRepat()),
	                validator.isEmailValid(entity.getEmail())
	        };

	        for (var item : validators) {
	            if (!item.isSuccess())
	                return new ErrorResult(item.getMessage());
	        }

	        employerDao.save(entity);

	        verificationEmployerService.sendVerificationCode(entity.getId(),entity.getEmail());
	        return new SuccessResult("Başarıyla kayıt oluşturulmuştur.");
	}

	@Override
	public DataResult<List<Employer>> getAll() {
		// TODO Auto-generated method stub
		return new SuccessDataResult<List<Employer>>(employerDao.findAll());
	}

	@Override
	public Result verifyAccountByVerificationCode(String email, String code) {
		var entities = getAll().getData();
        Employer employer = null;

        for (var item : entities) {
            if (item.getEmail().equals(email)){
                employer = item;
                var result = verificationEmployerService.verifyAccount(employer.getId(),code);
                if (!result.isSuccess())
                    return result;
            }
        }



        return new SuccessResult("Email başarıyla doğrulanmıştır.");
	}

}
