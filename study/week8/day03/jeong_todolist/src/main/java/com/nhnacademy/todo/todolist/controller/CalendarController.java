package com.nhnacademy.todo.todolist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class CalendarController {

    @GetMapping("/index.html")
    public String view(){
        return "index";
    }
}
