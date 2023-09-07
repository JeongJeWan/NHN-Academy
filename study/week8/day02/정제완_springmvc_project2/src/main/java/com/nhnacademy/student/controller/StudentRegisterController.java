package com.nhnacademy.student.controller;

import com.nhnacademy.student.exception.IdExistException;
import com.nhnacademy.student.exception.ValidationFailedException;
import com.nhnacademy.student.domain.Student;
import com.nhnacademy.student.repository.StudentRepository;
import com.nhnacademy.student.request.StudentRegisterRequest;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
@RequestMapping("/student")
public class StudentRegisterController implements BaseController{
    private final StudentRepository studentRepository;

    public StudentRegisterController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    @PostMapping("/register.do")
    public String registerPost(@Valid StudentRegisterRequest studentRegisterRequest, BindingResult bindingResult, HttpServletRequest req){

        if(!Objects.isNull(studentRepository.getStudentById(studentRegisterRequest.getId()))){
            throw new IdExistException();
        }
        if (bindingResult.hasErrors() ) {
            throw new ValidationFailedException(bindingResult);
        }

        Student student = new Student(studentRegisterRequest.getId(), studentRegisterRequest.getName(), studentRegisterRequest.getGender(), studentRegisterRequest.getAge());
        studentRepository.save(student);

        return "redirect:/student/list.do";
    }

    @GetMapping("/register.do")
    public String registerGet(Model model){
        model.addAttribute(new Student());
        return "student/register";
    }
}
