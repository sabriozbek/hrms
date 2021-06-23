package kodlamaio.hrms.api.controllers;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.business.abstracts.CoverLetterService;
import kodlamaio.hrms.business.abstracts.CurriculumVitaeService;
import kodlamaio.hrms.business.abstracts.EducationService;
import kodlamaio.hrms.business.abstracts.JobExperienceService;
import kodlamaio.hrms.business.abstracts.LanguageService;
import kodlamaio.hrms.business.abstracts.PhotoService;
import kodlamaio.hrms.business.abstracts.ProgrammingSkillService;
import kodlamaio.hrms.core.services.CloudinaryService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.CurriculumVitae;
import kodlamaio.hrms.entities.concretes.JobAdvert;
import kodlamaio.hrms.entities.concretes.Language;
import kodlamaio.hrms.entities.concretes.Photo;

@RestController
@RequestMapping("api/curriculumVitae/")
@CrossOrigin
public class CurriculumVitaeController {

	
	private CurriculumVitaeService curriculumVitaeService;
	private CloudinaryService cloudinaryService;
	private PhotoService photoService;
	private EducationService educationService;
	private JobExperienceService jobExperienceService;
	private LanguageService languageService;
	private ProgrammingSkillService programmingSkillService;
	private CoverLetterService coverLetterService;
	
	@Autowired
	public CurriculumVitaeController(CurriculumVitaeService curriculumVitaeService, CloudinaryService cloudinaryService,
			PhotoService photoService, EducationService educationService, JobExperienceService jobExperienceService,
			LanguageService languageService, ProgrammingSkillService programmingSkillService,
			CoverLetterService coverLetterService) {
		super();
		this.curriculumVitaeService = curriculumVitaeService;
		this.cloudinaryService = cloudinaryService;
		this.photoService = photoService;
		this.educationService = educationService;
		this.jobExperienceService = jobExperienceService;
		this.languageService = languageService;
		this.programmingSkillService = programmingSkillService;
		this.coverLetterService = coverLetterService;
	}
	
	@GetMapping("getCvById")
	public DataResult<CurriculumVitae>getCvById(int candidateId){
		return this.curriculumVitaeService.getCv(candidateId);
	}
	
	@PostMapping("addCv")
	public Result addCv(@RequestParam int candidateId,@RequestParam String githubAdress,@RequestParam String linkedinAdress) {
		return this.curriculumVitaeService.add(candidateId, githubAdress, linkedinAdress);
	}
	
	@PostMapping("addEducationToCv")
	public Result addEducationToCv(@RequestParam String schoolName,@RequestParam  String department, @RequestParam int startTimeYear, 
			@RequestParam int startTimeMonth, @RequestParam int startTimeDay, @RequestParam Integer graduateTimeYear, 
			@RequestParam Integer graduateTimeMonth, @RequestParam Integer graduateTimeDay, @RequestParam boolean isGraduated, @RequestParam int candidateId) {
		
		if (graduateTimeYear==null||graduateTimeMonth==null||graduateTimeDay==null) {
			graduateTimeDay=0;
			graduateTimeMonth=0;
			graduateTimeYear=0;
		}
		return this.educationService.addEducationToCv(schoolName, department, startTimeYear, startTimeMonth, startTimeDay, graduateTimeYear, graduateTimeMonth, graduateTimeDay, isGraduated, candidateId);
		
	}
	@PostMapping("addJobExperienceToCv")
	public Result addJobExperienceToCv(String workplaceName, String positionName, int startTimeYear, int startTimeMonth, int startTimeDay, 
			Integer endTimeYear, Integer endTimeMonth, Integer endTimeDay, boolean isWorkingNow, int candidateId) {
		
		if(endTimeDay==null||endTimeMonth==null||endTimeDay==null) {
			endTimeDay=0;
			endTimeDay=0;
			endTimeDay=0;
		}
		return this.jobExperienceService.addJobExperienceToCv(workplaceName, positionName, startTimeYear, startTimeMonth, startTimeDay, endTimeYear, endTimeMonth, endTimeDay, isWorkingNow, candidateId);
		
	}
	@PostMapping("addPhotoToCv")
	public Result addPhotoToCv(@RequestParam MultipartFile multipartFile,@RequestParam int candidateId) throws IOException{
		BufferedImage bufferedImage=ImageIO.read(multipartFile.getInputStream());
		if (bufferedImage==null) {
			
			return new ErrorResult("Resim yüklemesi başarısız oldu.");
		}
		Map result=cloudinaryService.upload(multipartFile);
		
		Photo photo=new Photo((String)result.get("original_filename"), (String)result.get("url"), (String)result.get("public_id"));
		return this.photoService.add(photo, candidateId);
	}
	@PostMapping("addLanguageToCv")
	public Result addLanguageToCv(String languageName,int languageLevel,int candidateId) {
		if (languageLevel==0||languageLevel>=6) {
			return new ErrorResult("Dil seviyesini 1 ve 5 arasında giriniz.");
		}
		return this.languageService.addLanguageToCv(languageName, languageLevel, candidateId);
	}
	@PostMapping("addProgrammingSkillToCv")
	public ResponseEntity<?> addProgrammingSkillToCv(@Valid @RequestParam String programmingSkillName,@RequestParam int candidateId) {
		
		return ResponseEntity.ok(this.programmingSkillService.addPSkillToCv(programmingSkillName, candidateId));
	}
	@PostMapping("addCoverLetterToCv")
	public Result addCoverLetterToCv(String letter,int candidateId) {
		return this.coverLetterService.addCoverLetterToCv(letter, candidateId);
	}
	
	@PostMapping("updateCv")
	public Result updateCv(int candidateId,String githubAdress,String linkedinAdress) {
		return this.curriculumVitaeService.updateCv(candidateId, githubAdress, linkedinAdress);
	}
	@PostMapping("deleteEducationFromCv")
	public Result deleteEducationFromCv(int candidateId,int educationId) {
		return this.educationService.deleteEducationFromCv(candidateId, educationId);
	}
	@PostMapping("deleteLanguageFromCv")
	public Result deleteLanguageFromCv(int candidateId,int languageId) {
		return this.languageService.deleteLanguageFromCv(candidateId, languageId);
	}
	@PostMapping("deleteJobExperienceFromCv")
	public Result deleteJobExperienceFromCv(int candidateId,int jobExperienceId) {
		return this.jobExperienceService.deleteJobExperienceToCv(candidateId, jobExperienceId);
		
	}
	@PostMapping("deletePhotoFromCv")
	public Result deletePhotoFromCv(int candidateId,int photoId) {
		return this.photoService.deletePhotoFromCv(candidateId, photoId);
	}
	@PostMapping("deleteProgrammingSkillFromCv")
	public Result deleteProgrammingSkillFromCv(int candidateId,int programmingSkillId) {
		return this.programmingSkillService.deletePSkillFromCv(candidateId, programmingSkillId);
	}
	@PostMapping("deleteCoverLetterFromCv")
	public Result deleteCoverLetterFromCv(int candidateId,int coverLetterId ) {
		return this.coverLetterService.deleteCoverLetterFromCv(candidateId, coverLetterId);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions){
		
		Map<String, String> validationErrors =new HashMap<String, String>();
	for(FieldError fieldError: exceptions.getBindingResult().getFieldErrors()) {
		validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
	}

	ErrorDataResult<Object> errors=
	new ErrorDataResult<Object>(validationErrors,"Doğrulama hataları");
	return errors;

	}

		
	
}
