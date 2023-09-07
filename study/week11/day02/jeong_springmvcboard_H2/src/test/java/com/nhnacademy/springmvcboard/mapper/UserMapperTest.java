package com.nhnacademy.springmvcboard.mapper;

import com.nhnacademy.springmvcboard.config.RootConfig;
import com.nhnacademy.springmvcboard.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = RootConfig.class)
@Transactional
@Slf4j
public class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Order(1)
    @Test
    @Rollback(value = false)
    @DisplayName("사용자 등록")
    void register() {
        User user = new User("marco", "1234", "마르코", "프로필1");
        userMapper.register(user);

        Assertions.assertEquals("marco", userMapper.getUserById("marco").getUserId());
    }

    @Order(2)
    @Test
    @Rollback(value = false)
    @DisplayName("사용자 수정")
    void modify() {
        User dummyUser = new User("marco", "1234", "마르코 수정", "프로필2");

        userMapper.modify(dummyUser);

        User user = userMapper.getUserById("marco");

        Assertions.assertEquals(user.getUserName(), dummyUser.getUserName());
        Assertions.assertEquals(user.getProfileFileName(), dummyUser.getProfileFileName());
    }

    @Order(6)
    @Test
    @Rollback(value = false)
    @DisplayName("사용자 삭제")
    void delete() {
        userMapper.delete("marco");

        assertNull(userMapper.getUserById("marco"));
    }

    @Order(3)
    @Test
    @Rollback(value = false)
    @DisplayName("해당 아이디 조회")
    void getUserById() {
        User user = userMapper.getUserById("marco");

        assertEquals("marco", user.getUserId());
    }

    @Order(4)
    @Test
    @Rollback(value = false)
    @DisplayName("사용자 조회")
    void getUsers() {
        List<User> userList = userMapper.getUsers();

        assertEquals("marco", userList.get(0).getUserId());
    }

    @Order(5)
    @Test
    @Rollback(value = false)
    @DisplayName("user is null")
    void getUsers_notFound() {
        User user = userMapper.getUserById("jeong");

        assertNull(user);
    }
}