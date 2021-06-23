package kodlamaio.hrms.api.controllers;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.business.abstracts.CandidateService;
import kodlamaio.hrms.business.abstracts.PhotoService;
import kodlamaio.hrms.core.services.CloudinaryService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.concretes.Photo;
import kodlamaio.hrms.entities.dtos.UserForVerifyVerificationCode;

@RestController
@RequestMapping("/api/candidates")
@CrossOrigin
public class CandidatesController {
private CandidateService candidateService;
private PhotoService photoService;

private CloudinaryService cloudinaryService;

@Autowired
public CandidatesController(CandidateService candidateService, PhotoService photoService,
		CloudinaryService cloudinaryService) {
	super();
	this.candidateService = candidateService;
	this.photoService = photoService;
	this.cloudinaryService = cloudinaryService;
}


@GetMapping("/getAll")
public DataResult <List<Candidate>> getAll(){
	return candidateService.getAll();
	
}
@PostMapping("/register")
public Result register(@RequestBody  Candidate candidate) {
	return candidateService.registeResult(candidate);
}


@PostMapping("/verifyAccountByVerificationCode")
public Result verifyAccount(@RequestBody UserForVerifyVerificationCode user) {
	return candidateService.
			verifyAccountByVerificationCode(user.getEmail(), user.getCode());
	
}
@PostMapping("/add")
public Result add(String email, String password, String passwordRepat, String firtsName, String lastName,
		String identityId, int birthYear) {
	
	return candidateService.add(email, password, passwordRepat, firtsName, lastName, identityId, birthYear);
	
}
@PostMapping("addPhotoToCandidate")
public Result addPhotoToCandidate(@RequestParam MultipartFile multipartFile,@RequestParam int candidateId) throws IOException{
	BufferedImage bufferedImage=ImageIO.read(multipartFile.getInputStream());
	if (bufferedImage==null) {
		
		return new ErrorResult("Resim yüklemesi başarısız oldu.");
	}
	Map result=cloudinaryService.upload(multipartFile);
	
	Photo photo=new Photo((String)result.get("original_filename"), (String)result.get("url"), (String)result.get("public_id"));
	return this.photoService.addCandidatePhoto(photo, candidateId);
}

}
