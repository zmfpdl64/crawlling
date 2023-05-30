package com.example.demo.domain.entity;

import com.example.demo.domain.Catch;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "\"catch\"")
@Getter
@Setter
@Entity
public class CatchEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String companyName;
    private String level;
    private String education;
    private String recruitmentPart;
    private String employmentType;
    private String salary;
    private String region;

    public static CatchEntity of(String companyName,
                                 String level,
                                 String education,
                                 String recruitmentPart,
                                 String employmentType,
                                 String salary,
                                 String region)
    {
        CatchEntity entity = new CatchEntity();
        entity.setCompanyName(companyName);
        entity.setLevel(level);
        entity.setEducation(education);
        entity.setRecruitmentPart(recruitmentPart);
        entity.setEmploymentType(employmentType);
        entity.setSalary(salary);
        entity.setRegion(region);
        return entity;
    }
}
