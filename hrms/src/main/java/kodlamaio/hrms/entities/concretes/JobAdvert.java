package kodlamaio.hrms.entities.concretes;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="job_adverts")
//@JsonIgnoreProperties({"hibernateLazyInitializer","handler","jobAdvertisements"}) 
public class JobAdvert {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;

@Column(name="description")
private String description;

@Column(name="min_salary")
private int minsalary;

@Column(name="max_salary")
private int maxSalary;

@Column(name="number_of_open_positions")
private int openPositionCount;

@Column(name="application_deadline")
private LocalDate applicaitonDeadlineDate;

@Column(name="created_at")
private LocalDate createDate;

@Column(name="is_active")
private boolean isActive;

@ManyToOne()
@JoinColumn(name="employer_id")
private Employer employer;

@ManyToOne()
@JoinColumn(name="job_position_id")
private JobPosition jobPositon;

@ManyToOne()
@JoinColumn(name="city_id")
private City city;

@ManyToOne()
@JoinColumn(name="workplace_id")
private WorkPlace workPlace;

@ManyToOne()
@JoinColumn(name = "work_time_id")
private WorkTime workTime;


}