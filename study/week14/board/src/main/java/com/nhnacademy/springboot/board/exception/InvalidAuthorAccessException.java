package com.nhnacademy.springboot.board.exception;

public class InvalidAuthorAccessException extends RuntimeException {
    public InvalidAuthorAccessException(int authority) {
        super("(" + authority + ")접근 권한이 없습니다.");
    }
}
