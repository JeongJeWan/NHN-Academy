package com.nhnacademy.edu.springframework.project;

import com.nhnacademy.edu.springframework.project.config.StudentScoreServiceConfig;
import com.nhnacademy.edu.springframework.project.repository.Score;
import com.nhnacademy.edu.springframework.project.service.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Collection;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        try(AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(StudentScoreServiceConfig.class)) {

            CsvDataLoadService dataLoadService = context.getBean("csvDataLoadService", CsvDataLoadService.class);
            dataLoadService.loadAndMerge();

            DefaultStudentService studentService = context.getBean("defaultStudentService", DefaultStudentService.class);
            Collection<Student> passedStudents = studentService.getPassedStudents();
            System.out.println(passedStudents);

            Collection<Student> orderedStudents = studentService.getStudentsOrderByScore();
            System.out.println(orderedStudents);

            DefaultGradeQueryService defaultGradeQueryService = context.getBean("defaultGradeQueryService", DefaultGradeQueryService.class);
            List<Score> scoreList = defaultGradeQueryService.getScoreByStudentName("A");
            System.out.println(scoreList);

            Score score = defaultGradeQueryService.getScoreByStudentSeq(11);
            System.out.println(score);
        }
    }
}
