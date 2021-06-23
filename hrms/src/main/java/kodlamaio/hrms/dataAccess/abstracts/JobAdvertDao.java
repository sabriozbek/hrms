package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;


import kodlamaio.hrms.entities.concretes.JobAdvert;

public interface JobAdvertDao extends JpaRepository<JobAdvert, Integer>{

	List<JobAdvert> getByIsActive(boolean isActive);

 List<JobAdvert> getByIsActive(boolean isActive,Sort sort);
 List<JobAdvert> getByIsActiveAndEmployerId(boolean isActive,int id);
 
}
