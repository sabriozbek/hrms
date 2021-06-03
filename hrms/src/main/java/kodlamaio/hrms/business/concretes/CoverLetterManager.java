package kodlamaio.hrms.business.concretes;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import kodlamaio.hrms.business.abstracts.CoverLetterService;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CandidateDao;
import kodlamaio.hrms.dataAccess.abstracts.CoverLetterDao;
import kodlamaio.hrms.dataAccess.abstracts.CurriculumVitaeDao;
import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.concretes.CoverLetter;
import kodlamaio.hrms.entities.concretes.CurriculumVitae;
@Service
public class CoverLetterManager implements CoverLetterService{

	
	private CoverLetterDao coverLetterDao;
	private CurriculumVitaeDao curriculumVitaeDao;
	private CandidateDao candidateDao;
	
	
	@Autowired
	public CoverLetterManager(CoverLetterDao coverLetterDao, CurriculumVitaeDao curriculumVitaeDao,
			CandidateDao candidateDao) {
		super();
		this.coverLetterDao = coverLetterDao;
		this.curriculumVitaeDao = curriculumVitaeDao;
		this.candidateDao = candidateDao;
	}

	@Override
	public Result addCoverLetterToCv(String letter, int candidateId) {
		
		Candidate candidate=this.candidateDao.getById(candidateId);
		CurriculumVitae curriculumVitae=this.curriculumVitaeDao.getByCandidate(candidate);
		
		CoverLetter coverLetter=new CoverLetter(letter,curriculumVitae);
		
		this.coverLetterDao.save(coverLetter);
		return new SuccessResult("Önyazı başarıyla eklenmiştir.");
		
		
		
		
	}

	@Override
	public Result deleteCoverLetterFromCv(int candidateId, int coverLetterId) {
		Candidate candidate=this.candidateDao.getById(candidateId);
		
		for(CoverLetter c:candidate.getCurriculumVitae().getCoverLetters()) {
			if (c.getId()==coverLetterId) {
				this.coverLetterDao.deleteById(coverLetterId);
				return new SuccessResult("Önyazı silinmiştir.");
			}
		}
		return new ErrorResult("Önyazı bulunamadı.");

		
		
	}

	
	
}
