package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.JobAdvertService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobAdvert;

@RestController
@RequestMapping("/api/jobAdverts/")
public class JobAdvertsController {
	private JobAdvertService jobAdvertService;
@Autowired
	public JobAdvertsController(JobAdvertService jobAdvertService) {
		
		this.jobAdvertService = jobAdvertService;
	}

	@GetMapping("getAll")
	public DataResult<List<JobAdvert>> getAll(){
		return this.jobAdvertService.getAll();
	}
	@PostMapping("add")
	public Result add(@RequestBody JobAdvert jobAdvert) {
		return jobAdvertService.add(jobAdvert);
	}
	@PostMapping("changeStatus")
	public Result changeStatus(@RequestParam int jobAdvertId) {
		return jobAdvertService.changeJobAdvertStatus(jobAdvertId);
	}
	
	@GetMapping("getByActive")
	public Result getByActive() {
		return jobAdvertService.getByActiveJobAdverts();
	}
	@GetMapping("getByCreateDate")
	public DataResult<List<JobAdvert>> getByCreateDate(@RequestParam int value){
		return jobAdvertService.getByCreateDateJobAdvert(value);
	}
@GetMapping("getByEmployer")
public DataResult<List<JobAdvert>> getByEmployer(@RequestParam int employerId){
	return jobAdvertService.getByEmployerJobAdvert(employerId);
}
}
