package kodlamaio.hrms.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name = "candidates")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","photos", "curriculumVitae"})
public class Candidate extends User{
	@Column(name="first_name")
	private String firtsName;
	@Column(name="last_name")
	private String lastName;
	@Column(name = "identity_number")
     private String identityId;
	 @Column(name = "birth_year")
	 private int birthYear;
	 
	 @OneToMany(mappedBy = "candidate")
	 private List<Photo> photos;
	 
	 @OneToOne(mappedBy = "candidate")
	 private CurriculumVitae curriculumVitae;
}
