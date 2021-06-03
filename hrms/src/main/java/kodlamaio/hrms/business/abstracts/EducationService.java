package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.Result;

public interface EducationService {
Result addEducationToCv(String schoolName, String department, int startTimeYear, int startTimeMonth, int startTimeDay, 
		int graduateTimeYear, int graduateTimeMonth, int graduateTimeDay, boolean isGraduated, int candidateId);
Result deleteEducationFromCv(int candidateId,int educationId);

}
