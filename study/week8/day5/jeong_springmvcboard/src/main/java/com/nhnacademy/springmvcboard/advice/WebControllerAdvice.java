package com.nhnacademy.springmvcboard.advice;

import com.nhnacademy.springmvcboard.exception.DuplicateUserIdException;
import com.nhnacademy.springmvcboard.exception.PostNotFoundException;
import com.nhnacademy.springmvcboard.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@Slf4j
@ControllerAdvice
public class WebControllerAdvice {
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleMissingServletRequestParameterException(MissingServletRequestParameterException ex, Model model){
        String message = "현재 이'"+ex.getParameterName()+"'이 입력되지 않았습니다.";
        model.addAttribute("message",message);
        return "/error";
    }
    @ExceptionHandler(PostNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundPostId(PostNotFoundException ex,Model model){
        String message = "해당 "+ex.getMessage()+"의 게시글이 존재하지 않습니다.";
        model.addAttribute("message",message);
        return "/error";
    }
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundUserId(UserNotFoundException ex,Model model){
        String message = "해당 "+ex.getMessage()+"의 유저가 존재하지 않습니다.";
        model.addAttribute("message",message);
        return "/error";
    }
    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleNumberFormatException(NumberFormatException ex,Model model){
        String message = "Failed to convert value of type 'java.lang.String' to required type 'long'; nested exception is java.lang.NumberFormatException: For input string:"+ex.getMessage();
        model.addAttribute("message",message);
        return "/error";
    }
    @ExceptionHandler(DuplicateUserIdException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleUserAlreadyExists(DuplicateUserIdException ex,Model model){
        String message = "해당"+ex.getMessage()+"은 이미 존재합니다.";
        model.addAttribute("message",message);
        return "/error";
    }


}
