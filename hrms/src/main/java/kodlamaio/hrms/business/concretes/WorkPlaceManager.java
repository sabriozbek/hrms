package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.WorkPlaceService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.dataAccess.abstracts.WorkPlaceDao;
import kodlamaio.hrms.entities.concretes.WorkPlace;

@Service
public class WorkPlaceManager implements WorkPlaceService{
private WorkPlaceDao workPlaceDao;

	@Autowired
	public WorkPlaceManager(WorkPlaceDao workPlaceDao) {
	
	this.workPlaceDao = workPlaceDao;
}


	@Override
	public DataResult<List<WorkPlace>> getAll() {
		// TODO Auto-generated method stub
		return new SuccessDataResult<List<WorkPlace>>(this.workPlaceDao.findAll(),"Data başarıyla listelendi.");
	}

}
