package com.nhnacademy.springmvcboard.exception;

public class DuplicateStudentIdException extends RuntimeException{
    public DuplicateStudentIdException(String id) {
        super("사용자아이디 중복: " + id);
    }
}
