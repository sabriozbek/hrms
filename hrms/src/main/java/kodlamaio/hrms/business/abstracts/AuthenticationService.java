package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.Result;

public interface AuthenticationService {
Result checkIfRealPerson(String identityId,String firstName,String lastName,int birthYear);
}
