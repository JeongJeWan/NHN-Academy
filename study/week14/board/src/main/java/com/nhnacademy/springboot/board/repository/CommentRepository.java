package com.nhnacademy.springboot.board.repository;

import com.nhnacademy.springboot.board.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    long countByPost_Id(Long postId);
    List<Comment> findByPost_Id(Long postId);
    List<Comment> findByPost_IdOrderByCreatedAt(Long postId);
}
