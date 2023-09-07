package com.nhnacademy.student.config;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nhnacademy.student.Base;
import com.nhnacademy.student.domain.Gender;
import com.nhnacademy.student.domain.Student;
import com.nhnacademy.student.repository.*;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackageClasses = {com.nhnacademy.student.Base.class},excludeFilters = {@ComponentScan.Filter(Controller.class)})
public class RootConfig {
    @Bean
    public StudentRepository mapStudentRepository(){
        StudentRepository studentRepository = new MapStudentRepository();
        RandomDataGenerator generator = new RandomDataGenerator();
        for(int i=1; i<=10; i++){
            String id="student" +i;
            String name = "학생"+ i;
            Gender gender = i%2 ==0 ? Gender.M : Gender.F;
            int age = generator.nextInt(20,30);
            Student student = new Student(id,name,gender,age);
            studentRepository.save(student);
        }
        return studentRepository;
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        //  pretty pring json
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        // value -> null 무시, 데이터가 널이면 빼버림
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // LocatDate, LocalDateTime suport jsr310
        objectMapper.registerModule(new JavaTimeModule());
        // timestamp 출력을 disable -> 리스트 형태에서 문자열형태로 출력
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return objectMapper;
    }

    // MessageSource Bean 등록, Spring framework 애서 다국어 처리 시 이 bean을 활용
    @Bean
    public MessageSource messageSource(){
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("message");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }


}
