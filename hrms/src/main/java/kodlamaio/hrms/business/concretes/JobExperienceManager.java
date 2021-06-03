package kodlamaio.hrms.business.concretes;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobExperienceService;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CandidateDao;
import kodlamaio.hrms.dataAccess.abstracts.CurriculumVitaeDao;
import kodlamaio.hrms.dataAccess.abstracts.JobExperienceDao;
import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.concretes.CurriculumVitae;
import kodlamaio.hrms.entities.concretes.JobExpreince;

@Service
public class JobExperienceManager implements JobExperienceService{

	private JobExperienceDao jobExperienceDao;
	private CurriculumVitaeDao curriculumVitaeDao;
	private CandidateDao candidateDao;
	
	
	
	@Autowired
	public JobExperienceManager(JobExperienceDao jobExperienceDao, CurriculumVitaeDao curriculumVitaeDao,
			CandidateDao candidateDao) {
		super();
		this.jobExperienceDao = jobExperienceDao;
		this.curriculumVitaeDao = curriculumVitaeDao;
		this.candidateDao = candidateDao;
	}

	@Override
	public Result addJobExperienceToCv(String workplaceName, String positionName, int startTimeYear, int startTimeMonth,
			int startTimeDay, int endTimeYear, int endTimeMonth, int endTimeDay, boolean isWorkingNow,
			int candidateId) {
	
		Candidate candidate=this.candidateDao.getById(candidateId);
		CurriculumVitae curriculumVitae=this.curriculumVitaeDao.getByCandidate(candidate);
		
		LocalDate startDate =LocalDate.of(startTimeYear, startTimeMonth, startTimeDay);
		LocalDate endDate=null;
		
		if (isWorkingNow==false) {
			endDate=LocalDate.of(endTimeYear, endTimeMonth, endTimeDay);
			JobExpreince jobExpreince=new JobExpreince(workplaceName, positionName, startDate, endDate, isWorkingNow, curriculumVitae);
			this.jobExperienceDao.save(jobExpreince);
		}
		else {
			JobExpreince jobExpreince=new JobExpreince(workplaceName, positionName, startDate, isWorkingNow, curriculumVitae);
			this.jobExperienceDao.save(jobExpreince);
		}
		return new SuccessResult("İş deneyimleri başarıyla eklenmiştir.");
	}

	@Override
	public Result deleteJobExperienceToCv(int candidateId, int jobExperienceId) {
		
		Candidate candidate=this.candidateDao.getById(candidateId);
		for(JobExpreince j:candidate.getCurriculumVitae().getJobExpreinces()) {
			if(j.getId()==jobExperienceId) {
				this.jobExperienceDao.deleteById(jobExperienceId);
				return new SuccessResult("İş deneyimi başarıyla silinmiştir.");
			}
			
		}
		return new ErrorResult("İş deneyimi bilgisi daha önce eklenmemiştir.");
	}

	
	
}
