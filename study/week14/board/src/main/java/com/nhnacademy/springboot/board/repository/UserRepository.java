package com.nhnacademy.springboot.board.repository;

import com.nhnacademy.springboot.board.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUserId(String userId);
    List<User> findAll();
}
