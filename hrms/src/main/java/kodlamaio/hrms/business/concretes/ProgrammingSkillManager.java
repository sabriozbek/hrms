package kodlamaio.hrms.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import kodlamaio.hrms.business.abstracts.ProgrammingSkillService;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CandidateDao;
import kodlamaio.hrms.dataAccess.abstracts.CurriculumVitaeDao;
import kodlamaio.hrms.dataAccess.abstracts.ProgrammingSkillDao;
import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.concretes.CurriculumVitae;
import kodlamaio.hrms.entities.concretes.ProgrammingSkill;
@Service
public class ProgrammingSkillManager implements ProgrammingSkillService{

	private ProgrammingSkillDao programmingSkillDao;
	private CandidateDao candidateDao;
	private CurriculumVitaeDao curriculumVitaeDao;
	
	@Autowired
	public ProgrammingSkillManager(ProgrammingSkillDao programmingSkillDao, CandidateDao candidateDao,
			CurriculumVitaeDao curriculumVitaeDao) {
		super();
		this.programmingSkillDao = programmingSkillDao;
		this.candidateDao = candidateDao;
		this.curriculumVitaeDao = curriculumVitaeDao;
	}

	@Override
	public Result addPSkillToCv(String programmingSkillName, int candidateId) {
		
		Candidate candidate=this.candidateDao.getById(candidateId);
		CurriculumVitae curriculumVitae=this.curriculumVitaeDao.getByCandidate(candidate);
		
		ProgrammingSkill programmingSkill=new ProgrammingSkill(programmingSkillName, curriculumVitae);
		this.programmingSkillDao.save(programmingSkill);
		return new SuccessResult("Program ve teknoloji bilgileri eklenmiştir.");
	}

	@Override
	public Result deletePSkillFromCv(int candidateId, int programmingSkillId) {
		
		
		Candidate candidate=this.candidateDao.getById(candidateId);
		
		for(ProgrammingSkill p:candidate.getCurriculumVitae().getProgrammingSkills()){
			if(p.getId()==programmingSkillId) {
				this.programmingSkillDao.deleteById(programmingSkillId);
			return new SuccessResult("Programlama ve teknoloji becerileri silindi.");
			}
		}
		return new ErrorResult("Programlama ve teknoloji bilgisi bulanamamaıştır.");
			
		
	}

}
