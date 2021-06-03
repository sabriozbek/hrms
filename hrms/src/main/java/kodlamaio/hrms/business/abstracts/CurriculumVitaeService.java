package kodlamaio.hrms.business.abstracts;


import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.CurriculumVitae;

public interface CurriculumVitaeService {
	Result add(int candidateId,String githubAdress,String linkedinAdress);
	DataResult<CurriculumVitae> getCv(int candidateId);
	Result addEducationToCv(String schoolName, String department, int startTimeYear, int startTimeMonth, int startTimeDay, 
			int graduateTimeYear, int graduateTimeMonth, int graduateTimeDay, boolean isGraduated, int candidateId);
	

	Result addJobExpreienceToCv(String workplaceName, String positionName, int startTimeYear, int startTimeMonth, int startTimeDay, 
			int endTimeYear, int endTimeMonth, int endTimeDay, boolean isWorkingNow, int candidateId);

 
	Result addLanguageToCv(String languageName,int languageLevel,int candidateId);
	
	Result addProgrammingSkillToCv(String programmingSkillName,int candidateId);
	
	Result addCoverLetterToCv(String letter,int candidateId);

}

