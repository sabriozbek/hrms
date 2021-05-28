package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;

public interface BaseService<T> {
DataResult<List<T>> getAll();
}
