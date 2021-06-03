package kodlamaio.hrms.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.CoverLetterService;
import kodlamaio.hrms.business.abstracts.CurriculumVitaeService;
import kodlamaio.hrms.business.abstracts.EducationService;
import kodlamaio.hrms.business.abstracts.JobExperienceService;
import kodlamaio.hrms.business.abstracts.LanguageService;
import kodlamaio.hrms.business.abstracts.PhotoService;
import kodlamaio.hrms.business.abstracts.ProgrammingSkillService;
import kodlamaio.hrms.core.services.CloudinaryService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.CurriculumVitae;
import kodlamaio.hrms.entities.concretes.Language;

@RestController
@RequestMapping("api/curriculumVitae/")
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
	
	
	public Result addCv(@RequestParam int candidateId,@RequestParam String githubAdress,@RequestParam String linkedinAdress) {
		return this.curriculumVitaeService.add(candidateId, githubAdress, linkedinAdress);
	}
}
