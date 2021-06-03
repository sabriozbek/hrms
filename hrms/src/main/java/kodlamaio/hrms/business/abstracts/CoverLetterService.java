package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.Result;

public interface CoverLetterService {

	Result addCoverLetterToCv(String letter,int candidateId);
	
	Result deleteCoverLetterFromCv(int candidateId,int coverLetterId);
	
	
}
