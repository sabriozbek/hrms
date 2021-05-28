package kodlamaio.hrms.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
public class Candidate extends User{
	@Column(name="first_name")
	private String firtsName;
	@Column(name="last_name")
	private String lastName;
	@Column(name = "identity_number")
     private String identityId;
	 @Column(name = "birth_year")
	 private int birthYear;
}
