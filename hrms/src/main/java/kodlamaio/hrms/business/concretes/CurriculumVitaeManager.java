package kodlamaio.hrms.business.concretes;

import java.time.LocalDate;

import org.apache.commons.lang3.ThreadUtils.ThreadIdPredicate;
import org.springframework.beans.factory.annotation.Autowired;


import kodlamaio.hrms.business.abstracts.CurriculumVitaeService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CandidateDao;
import kodlamaio.hrms.dataAccess.abstracts.CoverLetterDao;
import kodlamaio.hrms.dataAccess.abstracts.CurriculumVitaeDao;
import kodlamaio.hrms.dataAccess.abstracts.EducationDao;
import kodlamaio.hrms.dataAccess.abstracts.JobExperienceDao;
import kodlamaio.hrms.dataAccess.abstracts.LanguageDao;
import kodlamaio.hrms.dataAccess.abstracts.ProgrammingSkillDao;
import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.concretes.CoverLetter;
import kodlamaio.hrms.entities.concretes.CurriculumVitae;
import kodlamaio.hrms.entities.concretes.Education;
import kodlamaio.hrms.entities.concretes.JobExpreince;
import kodlamaio.hrms.entities.concretes.Language;
import kodlamaio.hrms.entities.concretes.ProgrammingSkill;


public class CurriculumVitaeManager implements CurriculumVitaeService{

	private CurriculumVitaeDao curriculumVitaeDao;
	private CandidateDao candidateDao;
	private EducationDao educationDao;
	private JobExperienceDao jobExperienceDao;
	private LanguageDao languageDao;
	private ProgrammingSkillDao programmingSkillDao;
	private CoverLetterDao coverLetterDao;
	
	@Autowired
	public CurriculumVitaeManager(CurriculumVitaeDao curriculumVitaeDao, CandidateDao candidateDao,
			EducationDao educationDao, JobExperienceDao jobExperienceDao, LanguageDao languageDao,
			ProgrammingSkillDao programmingSkillDao, CoverLetterDao coverLetterDao) {
	
		this.curriculumVitaeDao = curriculumVitaeDao;
		this.candidateDao = candidateDao;
		this.educationDao = educationDao;
		this.jobExperienceDao = jobExperienceDao;
		this.languageDao = languageDao;
		this.programmingSkillDao = programmingSkillDao;
		this.coverLetterDao = coverLetterDao;
	}

	@Override
	public Result add(int candidateId, String githubAdress, String linkedinAdress) {
		Candidate candidate=this.candidateDao.getById(candidateId);
		CurriculumVitae curriculumVitae=new CurriculumVitae(githubAdress, linkedinAdress, candidate);
		this.curriculumVitaeDao.save(curriculumVitae);
		return new SuccessResult("CV başarıyla oluşturuldu.");
	}

	@Override
	public DataResult<CurriculumVitae> getCv(int candidateId) {
		Candidate candidate=this.candidateDao.getById(candidateId);
		
		return new SuccessDataResult<CurriculumVitae>(this.curriculumVitaeDao.getByCandidate(candidate),"İşlem başarılı.");
	}

	@Override
	public Result addEducationToCv(String schoolName, String department, int startTimeYear, int startTimeMonth,
			int startTimeDay, int graduateTimeYear, int graduateTimeMonth, int graduateTimeDay, boolean isGraduated,
			int candidateId) {
		
		Candidate candidate=this.candidateDao.getById(candidateId);
		CurriculumVitae curriculumVitae=this.curriculumVitaeDao.getByCandidate(candidate);
		LocalDate startDate=LocalDate.of(startTimeDay, startTimeMonth, startTimeDay);
		LocalDate grDate=null;
		
		if (isGraduated==true) {
			grDate=LocalDate.of(graduateTimeYear, graduateTimeMonth, graduateTimeDay);
			Education education=new Education(schoolName, department, startDate, grDate, isGraduated, curriculumVitae);
		
			this.educationDao.save(education);
			
			}else {
				Education education=new Education(schoolName,department,startDate,isGraduated,curriculumVitae);
				this.educationDao.save(education);
				
			}
		return new SuccessResult("Eğitim bilgileri cv ye eklenmiştir.");
		
	}

	@Override
	public Result addJobExpreienceToCv(String workplaceName, String positionName, int startTimeYear, int startTimeMonth,
			int startTimeDay, int endTimeYear, int endTimeMonth, int endTimeDay, boolean isWorkingNow,
			int candidateId) {
		
		Candidate candidate=this.candidateDao.getById(candidateId);
		CurriculumVitae curriculumVitae=this.curriculumVitaeDao.getByCandidate(candidate);
		
		LocalDate startDate =LocalDate.of(startTimeYear, startTimeMonth, endTimeDay);
		LocalDate endDate=null;
		
		if (isWorkingNow==false) {
			endDate=LocalDate.of(endTimeYear, endTimeMonth, endTimeDay);
			JobExpreince jobExpreince=new JobExpreince(workplaceName,positionName,startDate,endDate,isWorkingNow,curriculumVitae);
			this.jobExperienceDao.save(jobExpreince);
			
		}
		else {
			JobExpreince jobExpreince=new JobExpreince(workplaceName,positionName,startDate,isWorkingNow,curriculumVitae);
			this.jobExperienceDao.save(jobExpreince);
			
		}
		return new SuccessResult("İş tecrübesi başarıyla kaydedilmiştir.");
		
		
		
	}

	@Override
	public Result addLanguageToCv(String languageName, int languageLevel, int candidateId) {
		Candidate candidate=this.candidateDao.getById(candidateId);
		CurriculumVitae curriculumVitae=this.curriculumVitaeDao.getByCandidate(candidate);
		 
		Language language=new Language(languageName,languageLevel,curriculumVitae);
		this.languageDao.save(language);
		return new SuccessResult("Dil bilgisi cv ye eklenmiştir.");
		
	}

	@Override
	public Result addProgrammingSkillToCv(String programmingSkillName,int candidateId) {
		Candidate candidate=this.candidateDao.getById(candidateId);
		CurriculumVitae curriculumVitae=this.curriculumVitaeDao.getByCandidate(candidate);
		
		ProgrammingSkill programmingSkill=new ProgrammingSkill(programmingSkillName,curriculumVitae);
		this.programmingSkillDao.save(programmingSkill);
		return new SuccessResult("Programlama  ve diğer teknolojik beceriler cv ye eklenmiştir.");
		
		
	}

	@Override
	public Result addCoverLetterToCv(String letter, int candidateId) {
		Candidate candidate=this.candidateDao.getById(candidateId);
		CurriculumVitae curriculumVitae=this.curriculumVitaeDao.getByCandidate(candidate);
		
		CoverLetter coverLetter=new CoverLetter(letter,curriculumVitae);
		this.coverLetterDao.save(coverLetter);
		return new SuccessResult("Önyazı cv ye başarıyla eklenmiştir.");
		
		
	}

}
