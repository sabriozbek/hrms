package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;

public interface BaseVerificationService<T> {
Result sendVerificationCode(int entityId,String email);
DataResult<List<T>> getAll();
Result verifyAccount(int entityId,String code);
}
