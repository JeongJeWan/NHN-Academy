package com.nhnacademy.edu.springframework.project;

import com.nhnacademy.edu.springframework.project.repository.Score;
import com.nhnacademy.edu.springframework.project.service.*;

import java.util.Collection;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        DataLoadService dataLoadService = new CsvDataLoadService();
        dataLoadService.loadAndMerge();

        DefaultStudentService studentService = new DefaultStudentService();
        Collection<Student> passedStudents = studentService.getPassedStudents();
        System.out.println(passedStudents);

        Collection<Student> orderedStudents = studentService.getStudentsOrderByScore();
        System.out.println(orderedStudents);

        DefaultGradeQueryService gradeQueryService = new DefaultGradeQueryService();
        List<Score> scoreList = gradeQueryService.getScoreByStudentName("A");
        System.out.println(scoreList);

        Score score = gradeQueryService.getScoreByStudentSeq(11);
        System.out.println(score);
    }
}
