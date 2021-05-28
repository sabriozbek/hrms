package kodlamaio.hrms.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employers")
@EqualsAndHashCode(callSuper = true)
public class Employer extends User {


    @Column(name = "company_name")
    private String companyName;
    @Column(name = "web_adress")
    private String webSite;
    @Column(name = "phone_number")
    private String phoneNumber;
}
