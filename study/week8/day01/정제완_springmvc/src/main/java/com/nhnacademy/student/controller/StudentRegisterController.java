package com.nhnacademy.student.controller;

import com.nhnacademy.student.exception.IdExistException;
import com.nhnacademy.student.exception.ValidationFailedException;
import com.nhnacademy.student.domain.Student;
import com.nhnacademy.student.repository.StudentRepository;
import com.nhnacademy.student.repository.StudentRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Objects;
@Slf4j
@Controller
@RequestMapping(value = "/student/register.do")
public class StudentRegisterController {
    private final StudentRepository studentRepository;

    public StudentRegisterController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    @PostMapping
    public String registerPost(@Valid @ModelAttribute StudentRequest studentRequest, BindingResult bindingResult, HttpServletRequest req){

        if(!Objects.isNull(studentRepository.getStudentById(studentRequest.getId()))){
            throw new IdExistException();
        }
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        Student student = new Student(studentRequest.getId(),studentRequest.getName(),studentRequest.getGender(),studentRequest.getAge());
        studentRepository.update(student);

        return "redirect:/student/view.do?id="+student.getId();
    }

    @GetMapping
    public String registerGet(){
        return "student/register";
    }
}
