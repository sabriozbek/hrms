package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.JobExpreince;

public interface JobExperienceDao extends JpaRepository<JobExpreince, Integer> {

}
