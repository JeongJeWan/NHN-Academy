package com.nhnacademy.student.controller;

import com.nhnacademy.student.exception.ValidationFailedException;
import com.nhnacademy.student.domain.Student;
import com.nhnacademy.student.repository.StudentRepository;
import com.nhnacademy.student.request.StudentRegisterRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RequestMapping("/student")
@Controller
public class StudentUpdateController implements BaseController{
    private final StudentRepository studentRepository;

    public StudentUpdateController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    @PostMapping("/update.do")
    public String updatePost(@Valid StudentRegisterRequest request, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        Student student = new Student(request.getId(), request.getName(), request.getGender(), request.getAge());
        studentRepository.update(student);

        return "redirect:/student/view.do?id="+student.getId();
    }
    @GetMapping("/update.do")
    public String updateGet(Model model, HttpServletRequest req){
        String id = req.getParameter("id");
        Student student = studentRepository.getStudentById(id);
        model.addAttribute("student", student);

        return "student/register";
    }
}