package com.nhnacademy.springboot.board.exception;

public class InvalidPostAccessException extends RuntimeException {
    public InvalidPostAccessException(long postId) {
        super("Invalid Post num : " + postId);
    }
}
