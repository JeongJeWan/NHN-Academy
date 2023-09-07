package com.nhnacademy.springboot.board.exception;

public class LogoutFailedException extends RuntimeException {
    public LogoutFailedException() {
        super("Session is not invalid. Login first.");
    }
}
