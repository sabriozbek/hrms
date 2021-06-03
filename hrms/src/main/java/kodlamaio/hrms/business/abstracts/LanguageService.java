package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.Result;

public interface LanguageService {

	Result addLanguageToCv(String languageName,int languageLevel,int candidateId);
	
	Result deleteLanguageFromCv(int candidateId,int languageId);
	
	
}
