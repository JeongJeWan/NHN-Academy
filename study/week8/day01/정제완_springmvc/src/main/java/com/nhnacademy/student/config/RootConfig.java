package com.nhnacademy.student.config;


import com.nhnacademy.student.Base;
import com.nhnacademy.student.domain.Gender;
import com.nhnacademy.student.domain.Student;
import com.nhnacademy.student.repository.*;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackageClasses = Base.class,
    excludeFilters = { @ComponentScan.Filter(Controller.class)})
public class RootConfig {
    @Bean
    public UserRepository userRepository() {
        UserRepository userRepository = new UserRepositoryImpl();
        userRepository.addUser("admin","정제완", "1234");

        return userRepository;
    }
    @Bean
    public StudentRepository contextInitialized() {
        StudentRepository studentRepository = new MapStudentRepository();
        for (int i = 1; i <= 10; i++) {
            String id = "student" + i;
            String name = "아카데미" + i;
            int age = new RandomDataGenerator().nextInt(20, 30);
            Gender gender = age % 2 == 0 ? Gender.M : Gender.F;
            Student student = new Student(id, name, gender, age);
            studentRepository.save(student);
        }
        return studentRepository;
    }
}
