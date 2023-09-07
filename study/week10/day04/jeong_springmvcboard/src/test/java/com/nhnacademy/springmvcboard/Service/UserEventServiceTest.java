package com.nhnacademy.springmvcboard.Service;

import com.nhnacademy.springmvcboard.config.RootConfig;
import com.nhnacademy.springmvcboard.domain.User;
import org.junit.jupiter.api.DisplayName;
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
class UserEventServiceTest {

    @Autowired
    UserEventService userEventService;

    @Test
    @Rollback(value = false)
    @DisplayName("사용자 등록")
    void register() {
        User user = new User("user1", "1234", "marco", "프로필1");
        userEventService.register(user);

        assertEquals("user1", userEventService.getUserById("user1").getUserId());
    }

    @Test
    @Rollback(value = false)
    @DisplayName("사용자 수정")
    void modify() {
        User dummyUser = new User("user1", "1234", "jeong", "프로필2");
        userEventService.modify(dummyUser);

        User user = userEventService.getUserById("user1");

        assertEquals(user.getUserName(), dummyUser.getUserName());
        assertEquals(user.getProfileFileName(), dummyUser.getProfileFileName());
    }

    @Test
    @Rollback(value = false)
    @DisplayName("사용자 삭제")
    void removeById() {
        userEventService.removeById("user1");
    }

    @Test
    @Rollback(value = false)
    @DisplayName("사용자 조회")
    void getUserById() {
        User user = userEventService.getUserById("user1");

        assertEquals("user1", user.getUserId());
    }

    @Test
    @Rollback(value = false)
    @DisplayName("사용자 등록")
    void getUserList() {
        List<User> userList = userEventService.getUsers();

        assertEquals("user1", userList.get(0).getUserId());
    }
}