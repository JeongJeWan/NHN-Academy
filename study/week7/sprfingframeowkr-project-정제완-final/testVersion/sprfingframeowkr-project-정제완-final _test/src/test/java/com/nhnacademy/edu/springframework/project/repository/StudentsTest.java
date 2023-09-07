package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.service.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StudentsTest {

    private CsvStudents csvStudents;

    @BeforeEach
    void setup() {
        csvStudents = (CsvStudents) CsvStudents.getInstance();
    }

    @Test
    void load() {
        csvStudents.load();
        assertNotNull(csvStudents.students);

        List<Student> studentList = csvStudents.students;

        assertEquals("A", studentList.get(0).getName());
        assertEquals("B", studentList.get(1).getName());
        assertEquals("A", studentList.get(2).getName());
        assertEquals("D", studentList.get(3).getName());
    }

    @Test
    void findAll() {
        csvStudents.load();
        assertEquals(4, csvStudents.findAll().size());

        List<Student> studentList = csvStudents.students;

        assertEquals("A", studentList.get(0).getName());
        assertEquals("B", studentList.get(1).getName());
        assertEquals("A", studentList.get(2).getName());
        assertEquals("D", studentList.get(3).getName());
    }

    @Test
    void merge() {
        CsvScores csvScores = (CsvScores) CsvScores.getInstance();
        csvScores.load();

        csvStudents.load();
        csvStudents.merge(csvScores.findAll());

        List<Student> studentList = csvStudents.students;

        assertEquals(30, studentList.get(0).getScore().getScore());
        assertEquals(80, studentList.get(1).getScore().getScore());
        assertEquals(70, studentList.get(2).getScore().getScore());
        assertNull(studentList.get(3).getScore());
    }
}