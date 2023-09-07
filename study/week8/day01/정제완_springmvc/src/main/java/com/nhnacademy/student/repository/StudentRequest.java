package com.nhnacademy.student.repository;

import com.nhnacademy.student.domain.Gender;
import com.nhnacademy.student.validator.EnumPattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class StudentRequest {
    @Length(min = 5,max=20)
    private String id;

    @NotBlank
    @NotEmpty
    private String name;

    @EnumPattern(regexp = "M|F")
    private Gender gender;
    @Min(20)
    @Max(30)
    private long age;
}
