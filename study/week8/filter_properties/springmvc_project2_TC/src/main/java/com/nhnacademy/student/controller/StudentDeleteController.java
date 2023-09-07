package com.nhnacademy.student.controller;

import com.nhnacademy.student.repository.StudentRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@Controller
@RequestMapping("/student")
public class StudentDeleteController implements BaseController{
    private final StudentRepository studentRepository;

    public StudentDeleteController(StudentRepository studentRepository) {
        this.studentRepository= studentRepository;
    }
    @PostMapping("/delete.do")
    public String delete(HttpServletRequest req){
        String id = req.getParameter("id");
        log.error("id:{}",id);
        studentRepository.deleteById(id);
        //  view를 리턴
        return "redirect:/student/list.do";
    }

}