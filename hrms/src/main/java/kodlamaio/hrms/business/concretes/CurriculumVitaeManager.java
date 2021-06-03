package kodlamaio.hrms.business.concretes;

import java.time.LocalDate;

import org.apache.commons.lang3.ThreadUtils.ThreadIdPredicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CurriculumVitaeService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
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

@Service
public class CurriculumVitaeManager implements CurriculumVitaeService{

	private CurriculumVitaeDao curriculumVitaeDao;
	private CandidateDao candidateDao;
	
	@Autowired
	public CurriculumVitaeManager(CurriculumVitaeDao curriculumVitaeDao, CandidateDao candidateDao) {
	
		this.curriculumVitaeDao = curriculumVitaeDao;
		this.candidateDao = candidateDao;
	}
		

	@Override
	public Result add(int candidateId, String githubAdress, String linkedinAdress) {
		Candidate candidate=this.candidateDao.getById(candidateId);
		
		if(candidate.getCurriculumVitae()!=null) {
			return new ErrorResult("Cv daha önce kayıt edilmiş.Önceki cv nizi siliniz.");
		}
		CurriculumVitae curriculumVitae=new CurriculumVitae(candidate,githubAdress,linkedinAdress);
		this.curriculumVitaeDao.save(curriculumVitae);
		return new SuccessResult("CV başarıyla oluşturuldu.");
	}

	@Override
	public DataResult<CurriculumVitae> getCv(int candidateId) {
		Candidate candidate=this.candidateDao.getById(candidateId);
		
		return new SuccessDataResult<CurriculumVitae>(this.curriculumVitaeDao.getByCandidate(candidate),"İşlem başarılı.");
	}


	@Override
	public Result updateCv(int candidateId, String githubAddress, String linkedinAdress) {
	
		
		Candidate candidate=this.candidateDao.getById(candidateId);
		candidate.setCurriculumVitae(new CurriculumVitae(candidate,githubAddress,linkedinAdress));
		this.candidateDao.save(candidate);
		return new SuccessResult("Cv niz başarıyla güncellenmiştir."); 
		
		
		
	}
	
	



}
