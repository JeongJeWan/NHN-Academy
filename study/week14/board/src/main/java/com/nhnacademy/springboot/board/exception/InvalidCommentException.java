package com.nhnacademy.springboot.board.exception;

public class InvalidCommentException extends RuntimeException {
    public InvalidCommentException(long commentId) {
        super("Invalid Comment : " + commentId);
    }
}
