package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.CsvScores;
import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultGradeQueryService implements GradeQueryService {

    private final CsvStudents studentRepository;
    private final CsvScores scoreRepository;

    @Autowired
    public DefaultGradeQueryService(CsvStudents studentRepository, CsvScores scoreRepository) {
        this.studentRepository = studentRepository;
        this.scoreRepository = scoreRepository;
    }

    @Override
    public List<Score> getScoreByStudentName(String name) {
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
        return scoreRepository.findAll().stream()
                .filter(score -> score.getStudentSeq() == seq)
                .findFirst()
                .orElse(null);
    }
}
