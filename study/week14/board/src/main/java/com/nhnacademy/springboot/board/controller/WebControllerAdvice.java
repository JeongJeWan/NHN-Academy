package com.nhnacademy.springboot.board.controller;

import com.nhnacademy.springboot.board.exception.ValidationFailedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class WebControllerAdvice {

    @ExceptionHandler(ValidationFailedException.class)
    public String handlerValidationFailedException(Exception ex, Model model) {
        model.addAttribute("exception", ex.getMessage());

        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handlerException(Exception e, Model model) {
        model.addAttribute("exception", e.getMessage());

        e.printStackTrace();

        return "error";
    }

}
