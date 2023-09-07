package com.nhnacademy.springboot.board.repository;

import com.nhnacademy.springboot.board.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Like.Pk> {


    long countByUser_UserId(String userId);
}
