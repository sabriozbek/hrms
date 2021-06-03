package kodlamaio.hrms.business.abstracts;


import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.CurriculumVitae;

public interface CurriculumVitaeService {
	Result add(int candidateId,String githubAdress,String linkedinAdress);
	DataResult<CurriculumVitae> getCv(int candidateId);
	
	Result updateCv(int candidateId,String githubAddress,String linkedinAdress);
	
}

