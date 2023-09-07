package com.nhnacademy.springboot.board.exception;

import org.springframework.validation.BindingResult;

import java.util.stream.Collectors;

public class ValidationFailedException extends RuntimeException {
    public ValidationFailedException(BindingResult bindingResult) {
        super(bindingResult.getAllErrors()
                .stream()
                .map(error -> new StringBuilder()
                        .append(error.getObjectName())
                        .append(" [입력오류] ").append(error.getDefaultMessage()))
                .collect(Collectors.joining(" | ")));
    }
}
