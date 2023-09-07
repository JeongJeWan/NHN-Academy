package com.nhnacademy.springboot.board.exception;

public class InvalidUserException extends RuntimeException {
    public InvalidUserException(String userId) {
        super("Invalid user : " + userId);
    }
}
