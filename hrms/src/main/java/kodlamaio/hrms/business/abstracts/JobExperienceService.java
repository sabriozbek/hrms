package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.Result;

public interface JobExperienceService {

	
	Result addJobExperienceToCv(String workplaceName, String positionName, int startTimeYear, int startTimeMonth, int startTimeDay, 
			int endTimeYear, int endTimeMonth, int endTimeDay, boolean isWorkingNow, int candidateId);


	Result deleteJobExperienceToCv(int candidateId,int jobExperienceId);
}
