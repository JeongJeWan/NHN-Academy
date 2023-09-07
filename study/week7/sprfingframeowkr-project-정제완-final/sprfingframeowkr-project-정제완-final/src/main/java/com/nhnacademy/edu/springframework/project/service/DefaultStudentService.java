package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.Students;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class DefaultStudentService implements StudentService {
    private final Students studentRepository;

    @Autowired
    public DefaultStudentService(Students studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Collection<Student> getPassedStudents() {
        return studentRepository.findAll().stream()
                .filter(student -> student.getScore() != null && !student.getScore().isFail())
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Student> getStudentsOrderByScore() {
        return studentRepository.findAll().stream()
                .filter(student -> student.getScore() != null)
                .sorted((s1, s2) -> Integer.compare(s2.getScore().getScore(), s1.getScore().getScore()))
                .collect(Collectors.toList());
    }

}
