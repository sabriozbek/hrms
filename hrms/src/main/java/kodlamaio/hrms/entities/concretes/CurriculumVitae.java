package kodlamaio.hrms.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name="curriculum_vitaes")
public class CurriculumVitae {
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="id")
private int id;

@Column(name="github_adress")
private String githubAdress;

@Column(name="linkedin_adress")
private String linkedinAdress;

@OneToOne
@JoinColumn(name="candidate_id")
private Candidate candidate;

@OneToMany(mappedBy = "curriculumVitae")
private List<Education> educations;

@OneToMany(mappedBy = "curriculumVitae")
private List<JobExpreince> jobExpreinces;

@OneToMany(mappedBy = "curriculumVitae")
private List<Language> languages;

@OneToMany(mappedBy = "curriculumVitae")
private List<Photo> photos;

@OneToMany(mappedBy = "curriculumVitae")
private List<CoverLetter> coverLetters;

@OneToMany(mappedBy = "curriculumVitae")
private List<ProgrammingSkill> programmingSkills;

public CurriculumVitae(Candidate candidate,String githubAdress,String linkedinAdress) {
	
	this.githubAdress = githubAdress;
	this.linkedinAdress = linkedinAdress;
	this.candidate = candidate;
}










}
