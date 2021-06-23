package kodlamaio.hrms.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","curriculumVitae"})
@Table(name = "programming_languages")
public class ProgrammingSkill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@NotBlank(message = "Boş geçilemez")
	@NotNull
	@Column(name = "programming_skill_name")
	private String programmingLanguageName;
	
	@ManyToOne
	@JoinColumn(name = "curriculum_vitae_id")
	private CurriculumVitae curriculumVitae;

	public ProgrammingSkill(String programmingLanguageName, CurriculumVitae curriculumVitae) {
		
		this.programmingLanguageName = programmingLanguageName;
		this.curriculumVitae = curriculumVitae;
	}
	
	
}
