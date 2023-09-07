package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.config.StudentScoreServiceConfig;
import com.nhnacademy.edu.springframework.project.service.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StudentsTest {

    private Students studentRepository;
    private Scores scoreRepository;

    @BeforeEach
    void setup() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(StudentScoreServiceConfig.class);
        studentRepository = context.getBean("csvStudents", CsvStudents.class);
        scoreRepository = context.getBean("csvScores", CsvScores.class);
    }

    @Test
    void load() {
        studentRepository.load();
        assertNotNull(studentRepository.findAll());

        List<Student> studentList = (List<Student>) studentRepository.findAll();

        assertEquals("A", studentList.get(0).getName());
        assertEquals("B", studentList.get(1).getName());
        assertEquals("A", studentList.get(2).getName());
        assertEquals("D", studentList.get(3).getName());
    }

    @Test
    void findAll() {
        studentRepository.load();
        assertEquals(4, studentRepository.findAll().size());

        List<Student> studentList = (List<Student>) studentRepository.findAll();

        assertEquals("A", studentList.get(0).getName());
        assertEquals("B", studentList.get(1).getName());
        assertEquals("A", studentList.get(2).getName());
        assertEquals("D", studentList.get(3).getName());
    }

    @Test
    void merge() {
        scoreRepository.load();
        assertNotNull(scoreRepository.findAll());

        studentRepository.load();
        assertNotNull(studentRepository.findAll());

        studentRepository.merge(scoreRepository.findAll());

        List<Student> studentList = (List<Student>) studentRepository.findAll();

        assertEquals(30, studentList.get(0).getScore().getScore());
        assertEquals(80, studentList.get(1).getScore().getScore());
        assertEquals(70, studentList.get(2).getScore().getScore());
        assertNull(studentList.get(3).getScore());
    }
}