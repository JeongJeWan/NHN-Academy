package com.nhnacademy.student.exception;

public class DuplicateStudentIdException extends RuntimeException {
    public DuplicateStudentIdException(String id){
        super("학생아이디 중복 : " + id);
    }
}
