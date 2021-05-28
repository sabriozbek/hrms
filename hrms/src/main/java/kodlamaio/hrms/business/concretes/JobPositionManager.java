package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobPositionService;
import kodlamaio.hrms.business.validationRules.JobTitleValidator;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.JobPositionDao;
import kodlamaio.hrms.entities.concretes.JobPosition;

@Service
public class JobPositionManager implements JobPositionService{
private JobPositionDao jobPositionDao;
@Autowired
	public JobPositionManager(JobPositionDao jobPositionDao) {
	
	this.jobPositionDao = jobPositionDao;
}

	@Override
	public DataResult<List<JobPosition>> getAll() {
		// TODO Auto-generated method stub
		return new SuccessDataResult<List<JobPosition>>(jobPositionDao.findAll());
	}

	@Override
	public Result add(JobPosition entity) {
		 var titles = jobPositionDao.findAll();
	        for (var tittle : titles)
	        {
	            if (entity.getPosition().equals(tittle.getPosition()))
	                return new ErrorResult("Bu pozisyon daha önce kayıt edilmiştir.");
	        }

	        JobTitleValidator validator = new JobTitleValidator();
	        Result[] validators = new Result[]{
	                validator.isTitleFilled(entity.getPosition()),
	        };

	        for (var item : validators) {
	            if (!item.isSuccess())
	                return new ErrorResult(item.getMessage());
	        }

	        jobPositionDao.save(entity);
	        return new SuccessResult("Pozisyon başarıyla eklenmiştir.");
	    }

}
