package com.nhnacademy.student.thymeleaf;

import com.nhnacademy.student.domain.Gender;

//  thymeleaf 에서 사용할 custom 함수, M or F 받아서 남성 or 여성을 리턴하는 함수
public class TagUtils {
    public String gender(Gender gender){
        if(gender.name().equals("M")){
            return "남성";
        } else if (gender.name().equals("F")) {
            return "여성";
        }else {
            return "";
        }
    }
}
