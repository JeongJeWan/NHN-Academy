package com.nhnacademy.springmvcboard.exception;

public class DuplicatePostIdException extends RuntimeException{
    public DuplicatePostIdException(long id) {
        super("게시판아이디 중복: " + id);
    }
}
