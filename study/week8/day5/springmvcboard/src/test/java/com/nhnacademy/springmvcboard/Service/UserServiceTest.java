package com.nhnacademy.springmvcboard.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.springmvcboard.domain.User;
import com.nhnacademy.springmvcboard.repository.MapPostRepository;
import com.nhnacademy.springmvcboard.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserServiceTest {

    UserService userService;
    UserRepository userRepository;

    ObjectMapper objectMapper;

    @BeforeEach void beforeEach() {
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    @DisplayName("사용자 등록")
    void userRegister() {
        User user = new User("user1", "1234", "academy", "프로필1");
        userRepository.add(user);
    }

//    @Test
//    @DisplayName("사용자 수정")
//    void
}