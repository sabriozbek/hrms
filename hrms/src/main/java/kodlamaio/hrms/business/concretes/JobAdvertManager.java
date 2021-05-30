package kodlamaio.hrms.business.concretes;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobAdvertService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.JobAdvertDao;
import kodlamaio.hrms.entities.concretes.JobAdvert;


@Service
public class JobAdvertManager implements JobAdvertService{
private JobAdvertDao jobAdvertDao;
@Autowired
	public JobAdvertManager(JobAdvertDao jobAdvertDao) {
	super();
	this.jobAdvertDao = jobAdvertDao;
}

	@Override
	public DataResult<List<JobAdvert>> getAll() {
		// TODO Auto-generated method stub
		return new SuccessDataResult<List<JobAdvert>>
		(this.jobAdvertDao.findAll(),"İş ilanları başarıyla listelendi");
	}

	@Override
	public Result add(JobAdvert jobAdvert) {
		jobAdvert.getCreateDate().equals(LocalDate.now());
		jobAdvert.setActive(true);
		jobAdvertDao.save(jobAdvert);
		return new SuccessResult("İş ilanı başarıyla eklenmiştir.");
	}

	@Override
	public Result changeJobAdvertStatus(int jobAdvertId) {
		var jobAdvert=getByJobAdvertId(jobAdvertId);
		if (!jobAdvert.isSuccess()) {
			return new ErrorResult(jobAdvert.getMessage());
			
		}
		jobAdvert.getData().setActive(!jobAdvert.getData().isActive());
		jobAdvertDao.save(jobAdvert.getData());
		return new SuccessResult("İlan durumu başarıyla güncellendi.");
		
	}

	
	@Override
	public DataResult<JobAdvert> getByJobAdvertId(int jobAdvertId) {
		var result=jobAdvertDao.findById(jobAdvertId).get();
		if (result==null) {
			return new ErrorDataResult<JobAdvert>("Aradığınız iş ilanı bulunamamaktadır.");
		}
		return new SuccessDataResult<JobAdvert>(result);
	}

	@Override
	public DataResult<List<JobAdvert>> getByActiveJobAdverts() {
		return new SuccessDataResult<List<JobAdvert>>(jobAdvertDao.getByIsActive(true));
	}

	@Override
	public DataResult<List<JobAdvert>> getByCreateDateJobAdvert(int value) {
		Sort sort;
		if (value==0) {
			sort=Sort.by(Sort.Direction.DESC,"createDate");
		}
		else {
			sort=Sort.by(Sort.Direction.ASC,"createDate");
		}
		return new SuccessDataResult<List<JobAdvert>>(jobAdvertDao.getByIsActive(true,sort));
	}

	@Override
	public DataResult<List<JobAdvert>> getByEmployerJobAdvert(int employerId) {
		// TODO Auto-generated method stub
		return new SuccessDataResult<List<JobAdvert>>(jobAdvertDao.getByIsActiveAndEmployerId(true, employerId));
	}

}
