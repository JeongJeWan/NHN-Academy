package com.nhnacademy.springmvcboard.exception;

public class DuplicateUserIdException extends RuntimeException{
    public DuplicateUserIdException(String id) {
        super("사용자아이디 중복: " + id);
    }
}
