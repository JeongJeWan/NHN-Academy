package com.nhnacademy.student.controller;

import com.nhnacademy.student.exception.ValidationFailedException;
import com.nhnacademy.student.domain.Student;
import com.nhnacademy.student.repository.StudentRepository;
import com.nhnacademy.student.repository.StudentRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(value = "/student/update.do")
@Controller
public class StudentUpdateController {
    private final StudentRepository studentRepository;

    public StudentUpdateController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    @PostMapping
    public String updatePost(@Valid @ModelAttribute StudentRequest studentRequest, BindingResult bindingResult){

//
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        Student student = new Student(studentRequest.getId(),studentRequest.getName(),studentRequest.getGender(),studentRequest.getAge());
        studentRepository.update(student);

        return "redirect:/student/view.do?id="+student.getId();
    }
    @GetMapping
    public String updateGet(@RequestParam("id") String id, Model model){
        String userId = id;
        Student student = studentRepository.getStudentById(userId);
        model.addAttribute("student",student);

        return "student/register";
    }
}