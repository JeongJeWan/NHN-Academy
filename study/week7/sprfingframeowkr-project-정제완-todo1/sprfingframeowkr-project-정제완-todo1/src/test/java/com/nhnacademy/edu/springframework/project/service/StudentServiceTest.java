package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class StudentServiceTest {

    private StudentService studentService;
    private DataLoadService dataLoadService;
    private Students studentRepository;
    private Scores scoreRepository;

    @BeforeEach
    void setup() {
        studentService = new DefaultStudentService();
        dataLoadService = new CsvDataLoadService();
        studentRepository = CsvStudents.getInstance();
        scoreRepository = CsvScores.getInstance();
    }

    @Test
    void getPassedStudents() {
        dataLoadService.loadAndMerge();

        List<Integer> passedStudentSeqs = scoreRepository.findAll().stream()
                .filter(score -> !score.isFail())
                .map(Score::getStudentSeq)
                .collect(Collectors.toList());

        Collection<Student> expectedStudents = studentRepository.findAll().stream()
                .filter(student -> passedStudentSeqs.contains(student.getSeq()))
                .collect(Collectors.toList());

        List<Student> actualStudents = (List<Student>) studentService.getPassedStudents();

        assertEquals(expectedStudents, actualStudents);

        assertEquals(80, actualStudents.get(0).getScore().getScore());
        assertEquals(70, actualStudents.get(1).getScore().getScore());
    }

    @Test
    void getStudentsOrderByScore() {
        dataLoadService.loadAndMerge();

        Map<Integer, Score> scoreMap = scoreRepository.findAll().stream()
                .collect(Collectors.toMap(Score::getStudentSeq, Function.identity()));

        Collection<Student> expectedStudents = studentRepository.findAll().stream()
                .filter(student -> scoreMap.containsKey(student.getSeq()))
                .sorted(Comparator.comparing(student -> scoreMap.get(student.getSeq()).getScore(), Comparator.reverseOrder()))
                .collect(Collectors.toList());

        List<Student> actualStudents = (List<Student>) studentService.getStudentsOrderByScore();

        assertEquals(expectedStudents, actualStudents);

        assertEquals(80, actualStudents.get(0).getScore().getScore());
        assertEquals(70, actualStudents.get(1).getScore().getScore());
        assertEquals(30, actualStudents.get(2).getScore().getScore());
    }
}