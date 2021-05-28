package kodlamaio.hrms.business.validationRules;

import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.entities.concretes.Employer;

public class EmployerValidator extends UserValidator{
public EmployerDao employerDao;


public EmployerValidator(EmployerDao employerDao) {
	super();
	this.employerDao = employerDao;
}

public Result areAllInformationFilledOnRegister(Employer employer) {
    if
    (
                    employer.getPhoneNumber().equals("") ||
                    employer.getEmail().equals("") ||
                    employer.getWebSite().equals("") ||
                    employer.getCompanyName().equals("") ||
                    employer.getPassword().equals("") ||
                    employer.getPasswordRepat().equals("")
    ) {
        return new ErrorResult("Gereksinimler boş bırakılmaz.");
    }
    return new SuccessResult();


}
public Result isEmailTaken(String email) {
    var employers = employerDao.findAll();
    for (var employer : employers) {
        if (employer.getEmail().equals(email))
            return new ErrorResult("Bu email ile daha önce kayıt oluşturulmuştur.");
    }
    return new SuccessResult();
}
}
