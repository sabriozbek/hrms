package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.business.validationRules.JobTitleValidator;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobPosition;

public interface JobPositionService extends BaseService<JobPosition>{

	Result add(JobPosition jobTitle);
	
}
