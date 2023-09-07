package com.nhnacademy.todo.todolist.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorMessage405 {
    private LocalDateTime timestamp;
    private int statusCode;
    private String error;
    private String path;

    public ErrorMessage405(int statusCode, String error, String path) {
        this.timestamp = LocalDateTime.now();
        this.statusCode = statusCode;
        this.error = error;
        this.path = path;
    }

}
