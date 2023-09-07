package com.nhnacademy.student.domain;

import com.nhnacademy.student.validator.EnumPattern;
import lombok.Data;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

@Value
@Data
public class Student implements Serializable {
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
    private Date createdAt;

    public Student(String id, String name, Gender gender, long age){
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.createdAt = new Date();
    }
    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name=" + name +
                ", gender=" + gender +
                ", age=" + age +
                ", createdAt=" + createdAt +
                '}';
    }
}
