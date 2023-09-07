package com.nhnacademy.springboot.board.service;

import com.nhnacademy.springboot.board.domain.UserLoginRequest;
import com.nhnacademy.springboot.board.entity.User;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public interface UserService {
    Optional<User> getUser(String userId);

    User login(UserLoginRequest request);
}
