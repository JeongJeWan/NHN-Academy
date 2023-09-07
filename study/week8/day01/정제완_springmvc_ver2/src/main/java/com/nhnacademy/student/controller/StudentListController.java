package com.nhnacademy.student.controller;
import com.nhnacademy.student.domain.Student;
import com.nhnacademy.student.exception.StudentNotFoundException;
import com.nhnacademy.student.repository.StudentRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Objects;

@Slf4j
@Controller
@RequestMapping(value = "/student")
public class StudentListController{
    private final StudentRepository studentRepository;

    public StudentListController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/list.do")
    public String list(Model model){
        List<Student> studentList = studentRepository.getStudents();
        model.addAttribute("studentList",studentList);
        return "student/list";
    }
}
