package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.config.StudentScoreServiceConfig;
import com.nhnacademy.edu.springframework.project.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DataLoadServiceTest {

    private CsvDataLoadService dataLoadService;
    private Students students;

    private Students studentsMerge;
    private Scores scoresMerge;


    @BeforeEach
    void setUp() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(StudentScoreServiceConfig.class);
        dataLoadService = context.getBean("csvDataLoadService", CsvDataLoadService.class);
        students = context.getBean("csvStudents", CsvStudents.class);
        studentsMerge = context.getBean("csvStudents", CsvStudents.class);
        scoresMerge = context.getBean("csvScores", CsvScores.class);
    }

    @Test
    void loadAndMerge() {
        dataLoadService.loadAndMerge();

        studentsMerge.load();
        assertNotNull(studentsMerge.findAll());
        scoresMerge.load();
        assertNotNull(scoresMerge.findAll());
        studentsMerge.merge(scoresMerge.findAll());

        List<Student> studentScoreMerge = (List<Student>) studentsMerge.findAll();

        List<Student> studentListLoadAndMerge = (List<Student>) students.findAll();

        assertEquals(studentScoreMerge, studentListLoadAndMerge);

        assertEquals(30, studentListLoadAndMerge.get(0).getScore().getScore());
        assertEquals(80, studentListLoadAndMerge.get(1).getScore().getScore());
        assertEquals(70, studentListLoadAndMerge.get(2).getScore().getScore());
        assertNull(studentListLoadAndMerge.get(3).getScore());
    }
}