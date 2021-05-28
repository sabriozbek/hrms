package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.Result;

public interface UserService<T> extends BaseService<T>{
Result registeResult (T entity);
}
