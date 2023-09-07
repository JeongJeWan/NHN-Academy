package com.nhnacademy.student.advice;

import com.nhnacademy.student.exception.DuplicateStudentIdException;
import com.nhnacademy.student.exception.IdExistException;
import com.nhnacademy.student.exception.StudentNotFoundException;
import com.nhnacademy.student.exception.ValidationFailedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class WebControllerAdvice {
    @InitBinder
    void initBinder(WebDataBinder binder){
        binder.initDirectFieldAccess();
    }

    @ExceptionHandler(StudentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String studentNotFound() {
        return "error/studentNotFound";
    }

    @ExceptionHandler(IdExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String idExist() {
        return "error/idExist";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String pageNotFound() {
        log.info("404 Not Found");
        return "error/404";
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String internalServlerError(Exception e, Model model, HttpServletRequest request){
        model.addAttribute("exception",e.getMessage());
        log.info("internal server error");
        return "error/500";
    }
}
