package kodlamaio.hrms.business.validationRules;

import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;

public class JobTitleValidator implements BaseValidator{
public Result isTitleFilled(String title) {
	
	if (title.equals("")) {
		return new ErrorResult("Alanlar boş bırakılamaz.");
	}
	return new SuccessResult();
}
}
