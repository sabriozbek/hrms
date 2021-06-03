package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.Result;

public interface ProgrammingSkillService {

	Result addPSkillToCv(String programmingSkillName,int candidateId);
	
	Result deletePSkillFromCv(int candidateId,int programmingSkillId);
}
