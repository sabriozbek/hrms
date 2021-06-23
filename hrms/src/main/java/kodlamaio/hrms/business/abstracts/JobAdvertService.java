package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobAdvert;
import kodlamaio.hrms.entities.dtos.JobAdvertDto;

public interface JobAdvertService {
DataResult<List<JobAdvert>> getAll();
Result add(JobAdvertDto jobAdvertDto);
Result delete(int jobAdvert);
Result changeJobAdvertStatus(int jobAdvertId);

//DataResult<List<JobAdvert>> getAll(int pageNo,int pageSize);
DataResult<JobAdvert> getByJobAdvertId(int jobAdvertId);
DataResult<List<JobAdvert>> getByActiveJobAdverts();
DataResult<List<JobAdvert>>  getByIsPassiveJobAdverts();

DataResult<List<JobAdvert>> getByCreateDateJobAdvert(int value);
DataResult<List<JobAdvert>> getByEmployerJobAdvert(int employerId);
}
