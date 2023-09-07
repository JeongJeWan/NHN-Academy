package com.nhnacademy.student.request;

import com.nhnacademy.student.domain.Gender;
import com.nhnacademy.student.annotation.EnumPattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class StudentRegisterRequest {
    @NotBlank(message = "아이디를 입력해주세요")
    @Length(min = 5, max = 20 , message = "아이디는 최소 5자이상 20자 미만 입니다.")
    private  String id;
    @NotBlank(message = "이름을 입력해주세요")
    private  String name;

    @EnumPattern(regexp = "M|F")
    private Gender gender;

    @Min(value = 20, message = "나이는 20살 이상입니다.")
    @Max(value = 30, message = "나이는 30살 미만입니다.")
    private  int age;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "StudentRegisterRequest{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                '}';
    }
}
