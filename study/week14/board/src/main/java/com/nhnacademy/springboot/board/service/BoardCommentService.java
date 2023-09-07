package com.nhnacademy.springboot.board.service;

import com.nhnacademy.springboot.board.entity.Comment;
import com.nhnacademy.springboot.board.entity.Post;
import com.nhnacademy.springboot.board.entity.User;
import com.nhnacademy.springboot.board.exception.InvalidCommentException;
import com.nhnacademy.springboot.board.exception.InvalidPostAccessException;
import com.nhnacademy.springboot.board.exception.InvalidUserException;
import com.nhnacademy.springboot.board.repository.CommentRepository;
import com.nhnacademy.springboot.board.repository.PostRepository;
import com.nhnacademy.springboot.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardCommentService implements CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getComments(long postId) {
        return commentRepository.findByPost_Id(postId);
    }

    @Override
    @Transactional(readOnly = true)
    public Comment getComment(long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new InvalidCommentException(commentId));
    }

    @Override
    public void createComment(long postId, String writer, String content) {
        User user = userRepository.findById(writer)
                .orElseThrow(() -> new InvalidUserException(writer));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new InvalidPostAccessException(postId));

        Comment comment = new Comment();
        comment.setPost(post);
        comment.setUser(user);
        comment.setCreatedAt(new Date());

        commentRepository.save(comment);
    }

    @Override
    public long updateComment(long commentId, String content) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new InvalidCommentException(commentId));

        comment.setContent(content);
        comment.setUpdatedAt(new Date());

        Comment savedComment = commentRepository.save(comment);
        return savedComment.getPost().getId();
    }

    @Override
    public void deleteComment(long commentId) {
        commentRepository.deleteById(commentId);
    }


}
