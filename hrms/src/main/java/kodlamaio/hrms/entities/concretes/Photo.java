package kodlamaio.hrms.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "photos")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","curriculumVitae"})

public class Photo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "photo_name")
	private String photoName;
	
	@Column(name = "photo_url")
	private String photoUrl;
	
	@Column(name="photo_id")
	private String photoId;
	
	@ManyToOne()
	@JoinColumn(name="candidate_id")
	private Candidate candidate;
	
	@ManyToOne
	@JoinColumn(name = "curriculum_vitae_id")
	private CurriculumVitae curriculumVitae;

	public Photo(String photoName, String photoUrl, String photoId) {
		this.photoName = photoName;
		this.photoUrl = photoUrl;
		this.photoId = photoId;
		
	}

	public Photo(String photoName, String photoUrl, Candidate candidate, CurriculumVitae curriculumVitae) {
	super();
		this.photoName = photoName;
		this.photoUrl = photoUrl;
		this.candidate = candidate;
		this.curriculumVitae = curriculumVitae;
	}
	
	
	
	
	
	
	
	
	
	
}
