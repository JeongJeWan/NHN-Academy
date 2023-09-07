package com.nhnacademy.springboot.board.service;


import com.nhnacademy.springboot.board.entity.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getComments(long postId);

    Comment getComment(long commentId);

    void createComment(long postId, String writer, String content);

    long updateComment(long commentId, String content);

    void deleteComment(long commentId);
}
