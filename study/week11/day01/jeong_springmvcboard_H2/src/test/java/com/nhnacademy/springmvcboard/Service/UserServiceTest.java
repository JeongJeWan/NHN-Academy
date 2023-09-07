package com.nhnacademy.springmvcboard.Service;

import com.nhnacademy.springmvcboard.Service.impl.UserService;
import com.nhnacademy.springmvcboard.common.CommonPropertiesConfig;
import com.nhnacademy.springmvcboard.domain.User;
import com.nhnacademy.springmvcboard.exception.DuplicateUserIdException;
import com.nhnacademy.springmvcboard.exception.UserNotFoundException;
import com.nhnacademy.springmvcboard.repository.MapUserRepository;
import com.nhnacademy.springmvcboard.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    private static UserService userService;
    private static final UserRepository userRepository = new MapUserRepository();
    private static final CommonPropertiesConfig commonPropertiesConfig = null;
    @BeforeEach
    void setup() {
        for(int i=0; i< 5; i++) {
            userRepository.add(new User("user"+1, "pwd"+1, "NHN"+1, null));
        }

        userService = new UserService(userRepository, commonPropertiesConfig);
    }

    @Test
    @DisplayName("유저 등록")
    void userRegister() {
        User user = new User("admin", "1234", "관리자");
        userService.register(user);
        User userRegister = userService.getUserById("admin");
        assertEquals(user, userRegister);
    }

    @Test
    @DisplayName("유저ID 중복")
    void userDupId() {
        User user = new User("user1", "pwd1", "NHNasdas", "Ad");
        assertThrows(DuplicateUserIdException.class, () -> userService.register(user));
    }

    @Test
    @DisplayName("유저 수정")
    void userModify() {
        User user = new User("user1", "asdsa", "asdasd", "!!@!@");
        userService.modify(user);
        User user1 = userService.getUserById("user1");

        assertEquals("user1", user1.getUserId());
        assertEquals("asdsa", user1.getUserPassword());
        assertEquals("asdasd", user1.getUserName());
    }

    @Test
    @DisplayName("해당 유저 ID 검색")
    void userById() {
        User user = userService.getUserById("user1");

        assertEquals("user1", user.getUserId());
        assertEquals("pwd1", user.getUserPassword());
        assertEquals("NHN1", user.getUserName());
        assertNull(user.getProfileFileName());
    }

    @Test
    @DisplayName("해당 유저ID 존재하지 않을 때")
    void userNotFount() {
        assertThrows(UserNotFoundException.class,() -> userService.getUserById("Jwon"));
    }
}