package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.CandidateService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.dtos.UserForVerifyVerificationCode;

@RestController
@RequestMapping("/api/candidates")
public class CandidatesController {
private CandidateService candidateService;

@Autowired
public CandidatesController(CandidateService candidateService) {
	
	this.candidateService = candidateService;
}


@GetMapping("/getAll")
public DataResult <List<Candidate>> getAll(){
	return candidateService.getAll();
	
}
@PostMapping("/register")
public Result register(@RequestBody Candidate candidate) {
	return candidateService.registeResult(candidate);
}


@PostMapping("/verifyAccountByVerificationCode")
public Result verifyAccount(@RequestBody UserForVerifyVerificationCode user) {
	return candidateService.
			verifyAccountByVerificationCode(user.getEmail(), user.getCode());
	
}
}