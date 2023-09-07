package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultGradeQueryService implements GradeQueryService {

    @Override
    public List<Score> getScoreByStudentName(String name) {
        Students studentRepository = CsvStudents.getInstance();
        Scores scoreRepository = CsvScores.getInstance();
        List<Student> studentName = studentRepository.findAll().stream()
                .filter(student -> student.getName().equals(name))
                .collect(Collectors.toList());
        List<Score> scoreName = new ArrayList<>();
        for(Student student : studentName) {
            List<Score> scoreSeq = scoreRepository.findAll().stream()
                    .filter(score -> score.getStudentSeq() == student.getSeq())
                    .collect(Collectors.toList());
            scoreName.addAll(scoreSeq);
        }
        return scoreName;
    }

    @Override
    public Score getScoreByStudentSeq(int seq) {
        Scores scoreRepository = CsvScores.getInstance();

        return scoreRepository.findAll().stream()
                .filter(score -> score.getStudentSeq() == seq)
                .findFirst()
                .orElse(null);
    }
}
