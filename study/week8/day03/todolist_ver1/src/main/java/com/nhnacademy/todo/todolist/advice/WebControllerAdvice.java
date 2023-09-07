package com.nhnacademy.todo.todolist.advice;

import com.nhnacademy.todo.todolist.domain.ErrorMessage;
import com.nhnacademy.todo.todolist.domain.ErrorMessage405;
import com.nhnacademy.todo.todolist.exception.ErrorUserId;
import com.nhnacademy.todo.todolist.exception.EventIdNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.servlet.NoHandlerFoundException;


@Slf4j
@ControllerAdvice
public class WebControllerAdvice {
    @InitBinder
    void initBinder(WebDataBinder binder){
        binder.initDirectFieldAccess();
    }

    @ExceptionHandler({ErrorUserId.class, NullPointerException.class})
    public ResponseEntity<ErrorMessage> errorUserId() {
        return new ResponseEntity<>(new ErrorMessage(403, "잘못된 이벤트 소유자"), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(EventIdNotFoundException.class)
    public ResponseEntity<ErrorMessage> eventIdNotFound() {
        return new ResponseEntity<>(new ErrorMessage(404, "message : 이벤트가 존재하지 않습니다."), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({MissingServletRequestParameterException.class})
    public ResponseEntity<ErrorMessage> handlerMissingServletRequestParameter(Exception ex) {

        ErrorMessage errorMessage = new ErrorMessage(400, ex.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<ErrorMessage> handlerMissingRequestHeaderException() {
        return new ResponseEntity<>(new ErrorMessage(401, "Unauthorized"), HttpStatus.UNAUTHORIZED);
    }



    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ErrorMessage> internalServlerError(Exception e) {
        return new ResponseEntity<>(new ErrorMessage(500, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}