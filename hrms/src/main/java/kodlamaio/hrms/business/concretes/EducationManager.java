package kodlamaio.hrms.business.concretes;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import kodlamaio.hrms.business.abstracts.EducationService;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CandidateDao;
import kodlamaio.hrms.dataAccess.abstracts.CurriculumVitaeDao;
import kodlamaio.hrms.dataAccess.abstracts.EducationDao;
import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.concretes.CurriculumVitae;
import kodlamaio.hrms.entities.concretes.Education;

@Service
public class EducationManager implements EducationService{

	private EducationDao educationDao;
	private CurriculumVitaeDao curriculumVitaeDao;
	private CandidateDao candidateDao;
	
	@Autowired
	public EducationManager(EducationDao educationDao, CurriculumVitaeDao curriculumVitaeDao, CandidateDao candidateDao) {
		super();
		this.educationDao = educationDao;
		this.curriculumVitaeDao = curriculumVitaeDao;
		this.candidateDao = candidateDao;
	}

	@Override
	public Result addEducationToCv(String schoolName, String department, int startTimeYear, int startTimeMonth,
			int startTimeDay, int graduateTimeYear, int graduateTimeMonth, int graduateTimeDay, boolean isGraduated,
			int candidateId) {
		
		Candidate candidate=this.candidateDao.getById(candidateId);
		CurriculumVitae curriculumVitae=this.curriculumVitaeDao.getByCandidate(candidate);
		
		LocalDate startDate=LocalDate.of(startTimeYear, startTimeMonth, startTimeDay);
		LocalDate gruduationDate=null;
		
		if(isGraduated=true) {
			gruduationDate=LocalDate.of(graduateTimeYear, graduateTimeMonth, graduateTimeDay);
			Education education=new Education(schoolName,department,startDate,gruduationDate,isGraduated,curriculumVitae);
			this.educationDao.save(education);
			
		}
		else {
			Education education=new Education(schoolName, department, startDate, isGraduated, curriculumVitae);
		
					this.educationDao.save(education);
		}
		return new SuccessResult("Eğitim bilgileri başarıyal cv'ye eklenmiştir.");
		
	}

	@Override
	public Result deleteEducationFromCv(int candidateId, int educationId) {
		Candidate candidate=this.candidateDao.getById(candidateId);
		
		for(Education e:candidate.getCurriculumVitae().getEducations()) {
			
			if(e.getId()==educationId){
				this.educationDao.deleteById(educationId);
				return new SuccessResult("Eğitim bilgileri başarıyla silinmiştir.");
			}
			
			
		}
		return new ErrorResult("Eğitim bilgisi daha önce eklenmemiştir.");
		
		
		
	}
	
	
	
}
