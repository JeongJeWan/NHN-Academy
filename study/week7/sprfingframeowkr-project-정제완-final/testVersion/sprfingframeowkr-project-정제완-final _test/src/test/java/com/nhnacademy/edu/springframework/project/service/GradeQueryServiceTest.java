package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.CsvScores;
import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GradeQueryServiceTest {

    private GradeQueryService gradeQueryService;
    private CsvStudents csvStudents;
    private CsvScores csvScores;

    @BeforeEach
    void setup() {
        gradeQueryService = new DefaultGradeQueryService();
        csvStudents = (CsvStudents) CsvStudents.getInstance();
        csvScores = (CsvScores) CsvScores.getInstance();
    }

    @Test
    public void getScoreByStudentName() {
        csvStudents.load();
        csvScores.load();

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
        csvScores.load();

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