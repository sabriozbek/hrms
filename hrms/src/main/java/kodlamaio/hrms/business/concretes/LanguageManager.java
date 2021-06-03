package kodlamaio.hrms.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.LanguageService;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CandidateDao;
import kodlamaio.hrms.dataAccess.abstracts.CurriculumVitaeDao;
import kodlamaio.hrms.dataAccess.abstracts.LanguageDao;
import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.concretes.CurriculumVitae;
import kodlamaio.hrms.entities.concretes.Language;
@Service
public class LanguageManager implements LanguageService{

	
	
	private CandidateDao candidateDao;
	private LanguageDao languageDao;
	private CurriculumVitaeDao curriculumVitaeDao;
	
	@Autowired
	public LanguageManager(CandidateDao candidateDao, LanguageDao languageDao, CurriculumVitaeDao curriculumVitaeDao) {
		super();
		this.candidateDao = candidateDao;
		this.languageDao = languageDao;
		this.curriculumVitaeDao = curriculumVitaeDao;
	}

	@Override
	public Result addLanguageToCv(String languageName, int languageLevel, int candidateId) {

		Candidate candidate=this.candidateDao.getById(candidateId);
		CurriculumVitae curriculumVitae=this.curriculumVitaeDao.getByCandidate(candidate);
		
		Language language=new Language(languageName, languageLevel, curriculumVitae);
		this.languageDao.save(language);
		return new SuccessResult("Yabancı dil bilgisi başarıyla eklenmiştir.");
		

		
	}

	@Override
	public Result deleteLanguageFromCv(int candidateId, int languageId) {
		
		Candidate candidate=this.candidateDao.getById(candidateId);
		
		for(Language l:candidate.getCurriculumVitae().getLanguages()) {
			if(l.getId()==languageId) {
				this.languageDao.deleteById(languageId);
				return new SuccessResult("Yabancı dil bilgisi silindi.");
			}
		}
		return new ErrorResult("Yabancı dil bilgisi daha önce eklenmemiştir.") ;
		
	}

}
