package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DataLoadServiceTest {

    private DataLoadService dataLoadService;
    private Students students;

    @BeforeEach
    void setup() {
        dataLoadService = new CsvDataLoadService();
        students = CsvStudents.getInstance();
    }

    @Test
    void loadAndMerge() {
        dataLoadService.loadAndMerge();

        List<Student> studentList = (List<Student>) students.findAll();

        assertEquals(30, studentList.get(0).getScore().getScore());
        assertEquals(80, studentList.get(1).getScore().getScore());
        assertEquals(70, studentList.get(2).getScore().getScore());
        assertNull(studentList.get(3).getScore());

    }
}