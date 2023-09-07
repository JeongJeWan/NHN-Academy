package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.config.StudentScoreServiceConfig;
import com.nhnacademy.edu.springframework.project.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GradeQueryServiceTest {

    private DefaultGradeQueryService gradeQueryService;
    private Students studentRepository;
    private Scores scoreRepository;

    @BeforeEach
    void setup() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(StudentScoreServiceConfig.class);
        gradeQueryService = context.getBean("defaultGradeQueryService", DefaultGradeQueryService.class);
        studentRepository = context.getBean("csvStudents", CsvStudents.class);
        scoreRepository = context.getBean("csvScores", CsvScores.class);
    }

    @Test
    public void getScoreByStudentName() {
        studentRepository.load();
        assertNotNull(studentRepository.findAll());
        scoreRepository.load();
        assertNotNull(scoreRepository.findAll());

        List<Score> scoreList = gradeQueryService.getScoreByStudentName("A");
        assertNotNull(scoreList);
        assertEquals(2, scoreList.size());

        scoreList = gradeQueryService.getScoreByStudentName("B");
        assertNotNull(scoreList);
        assertEquals(1, scoreList.size());

        scoreList = gradeQueryService.getScoreByStudentName("D");
        assertNotNull(scoreList);
        assertEquals(0, scoreList.size());
    }

    @Test
    void getScoreByStudentSeq() {
        scoreRepository.load();
        assertNotNull(scoreRepository.findAll());

        Score score = gradeQueryService.getScoreByStudentSeq(1);
        assertNotNull(score);
        assertEquals(30, score.getScore());

        score = gradeQueryService.getScoreByStudentSeq(2);
        assertNotNull(score);
        assertEquals(80, score.getScore());

        score = gradeQueryService.getScoreByStudentSeq(3);
        assertNotNull(score);
        assertEquals(70, score.getScore());
    }
}