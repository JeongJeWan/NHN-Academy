package com.nhnacademy.springboot.board.domain;

import lombok.Value;

import java.util.Date;

@Value
public class PostComment {
    private final long id;
    private final String userId;
    private final String title;
    private final Date createdAt;
    private final String modifier;
    private final Date updatedAt;
    private final int commentCount;
}
