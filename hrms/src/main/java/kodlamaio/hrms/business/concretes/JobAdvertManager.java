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
import kodlamaio.hrms.dataAccess.abstracts.CityDao;
import kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.dataAccess.abstracts.JobAdvertDao;
import kodlamaio.hrms.dataAccess.abstracts.JobPositionDao;
import kodlamaio.hrms.dataAccess.abstracts.WorkPlaceDao;
import kodlamaio.hrms.dataAccess.abstracts.WorkTimeDao;
import kodlamaio.hrms.entities.concretes.JobAdvert;
import kodlamaio.hrms.entities.dtos.JobAdvertDto;


@Service
public class JobAdvertManager implements JobAdvertService{
private JobAdvertDao jobAdvertDao;
private EmployerDao employerDao;
private CityDao cityDao;
private JobPositionDao jobPositiondao;
private WorkPlaceDao workPlaceDao;
private WorkTimeDao workTimeDao;

	
@Autowired
	public JobAdvertManager(JobAdvertDao jobAdvertDao, EmployerDao employerDao, CityDao cityDao,
		JobPositionDao jobPositiondao, WorkPlaceDao workPlaceDao, WorkTimeDao workTimeDao) {
	super();
	this.jobAdvertDao = jobAdvertDao;
	this.employerDao = employerDao;
	this.cityDao = cityDao;
	this.jobPositiondao = jobPositiondao;
	this.workPlaceDao = workPlaceDao;
	this.workTimeDao = workTimeDao;
}



	@Override
	public DataResult<List<JobAdvert>> getAll() {
		// TODO Auto-generated method stub
		return new SuccessDataResult<List<JobAdvert>>
		(this.jobAdvertDao.findAll(),"İş ilanları başarıyla listelendi");
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

	@Override
	public DataResult<List<JobAdvert>> getByIsPassiveJobAdverts() {
		// TODO Auto-generated method stub
		return new SuccessDataResult<List<JobAdvert>>(jobAdvertDao.getByIsActive(false));
	}

	@Override
	public Result delete(int jobAdvertId) {
		// TODO Auto-generated method stub
		 jobAdvertDao.deleteById(jobAdvertId);;
		 return new SuccessResult("İlan başarıyla silinmiştir.");
		
	}

	@Override
	public Result add(JobAdvertDto jobAdvertDto) {
	JobAdvert jobAdvert=new JobAdvert();
	jobAdvert.setEmployer(this.employerDao.getOne(jobAdvertDto.getEmployerId()));
	jobAdvert.setCity(this.cityDao.getOne(jobAdvertDto.getCityId()));
	jobAdvert.setDescription(jobAdvertDto.getDescription());
	jobAdvert.setJobPositon(this.jobPositiondao.getOne(jobAdvertDto.getJobPositionId()));
	jobAdvert.setApplicaitonDeadlineDate(jobAdvertDto.getApplicaitonDeadlineDate());
	jobAdvert.setMinsalary(jobAdvertDto.getMinSalary());
	jobAdvert.setMaxSalary(jobAdvertDto.getMaxSalary());

	jobAdvert.setOpenPositionCount(jobAdvertDto.getOpenPositionCount());
	jobAdvert.setWorkPlace(this.workPlaceDao.getOne(jobAdvertDto.getWorkPlaceId()));
	jobAdvert.setWorkTime(this.workTimeDao.getOne(jobAdvertDto.getWorkTimeId()));
	jobAdvert.setCreateDate(LocalDate.now());
	jobAdvert.setActive(false);
	
	this.jobAdvertDao.save(jobAdvert);
	return new SuccessResult("Başarıyla ilan oluşturulmuştur.");
	}

	

}
