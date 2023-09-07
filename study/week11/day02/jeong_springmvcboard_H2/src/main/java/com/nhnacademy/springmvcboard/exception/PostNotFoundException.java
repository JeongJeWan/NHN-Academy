package com.nhnacademy.springmvcboard.exception;

public class PostNotFoundException extends RuntimeException{
    public PostNotFoundException(long message) {
        super(String.valueOf(message));
    }
}
