package com.nhnacademy.student.controller;
import com.nhnacademy.student.repository.StudentRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "/student/list.do")
public class StudentListController{
    private final StudentRepository studentRepository;

    public StudentListController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public String list(Model model){
        List<Object> studentList = studentRepository.getStudents();
        model.addAttribute("studentList",studentList);
        return "student/list";
    }
}
