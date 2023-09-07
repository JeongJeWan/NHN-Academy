package com.nhnacademy.edu.springframework.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ScoresTest {

    private CsvScores csvScores;

    @BeforeEach
    void setUp() {
        csvScores = (CsvScores) CsvScores.getInstance();
    }
    @Test
    void load() {
        csvScores.load();
        assertNotNull(csvScores.scores);

        List<Score> scoreList = csvScores.scores;

        assertEquals(30, scoreList.get(0).getScore());
        assertEquals(80, scoreList.get(1).getScore());
        assertEquals(70, scoreList.get(2).getScore());
    }

    @Test
    void findAll() {
        csvScores.load();
        assertEquals(3, csvScores.findAll().size());

        List<Score> scoreList = csvScores.scores;

        assertEquals(30, scoreList.get(0).getScore());
        assertEquals(80, scoreList.get(1).getScore());
        assertEquals(70, scoreList.get(2).getScore());
    }
}