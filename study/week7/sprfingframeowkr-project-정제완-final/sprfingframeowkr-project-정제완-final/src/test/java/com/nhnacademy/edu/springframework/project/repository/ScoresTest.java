package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.config.StudentScoreServiceConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ScoresTest {

    private Scores scoreRepository;

    @BeforeEach
    void setup() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(StudentScoreServiceConfig.class);
        scoreRepository = context.getBean("csvScores", CsvScores.class);
    }


    @Test
    void load() {
        scoreRepository.load();
        assertNotNull(scoreRepository.findAll());

        List<Score> scoreList = scoreRepository.findAll();

        assertEquals(30, scoreList.get(0).getScore());
        assertEquals(80, scoreList.get(1).getScore());
        assertEquals(70, scoreList.get(2).getScore());
    }

    @Test
    void findAll() {
        scoreRepository.load();
        assertEquals(3, scoreRepository.findAll().size());

        List<Score> scoreList = scoreRepository.findAll();

        assertEquals(30, scoreList.get(0).getScore());
        assertEquals(80, scoreList.get(1).getScore());
        assertEquals(70, scoreList.get(2).getScore());
    }
}