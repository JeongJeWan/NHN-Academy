package com.nhnacademy.springboot.board.domain;

import com.nhnacademy.springboot.board.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PostAll {
    private final Post post;
    private final long like;
    private final List<File> files;
    private final List<Comment> comments;
    private final List<Reply> replies;
}
