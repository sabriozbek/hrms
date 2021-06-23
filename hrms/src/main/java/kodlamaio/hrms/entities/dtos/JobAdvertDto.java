package kodlamaio.hrms.entities.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class JobAdvertDto {
private int cityId;
	
	private int employerId;
	
	private String description;

	private LocalDate applicaitonDeadlineDate;
	
	private int jobPositionId;
	
	private int maxSalary;
	
	private int minSalary;
	
	private int openPositionCount;
	
	private int workPlaceId;
	
	private int workTimeId;
}
