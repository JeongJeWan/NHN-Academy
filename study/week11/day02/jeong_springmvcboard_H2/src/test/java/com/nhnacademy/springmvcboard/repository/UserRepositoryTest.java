package com.nhnacademy.springmvcboard.repository;


import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.springmvcboard.config.RootConfig;
import com.nhnacademy.springmvcboard.config.WebConfig;
import com.nhnacademy.springmvcboard.entity.EntityUser;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@Disabled("temporary")
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void test() {
        EntityUser user1 = userRepository.findByUserId("user1");
        assertThat("1234").isEqualTo(user1.getUserPassword());

        EntityUser user2 = userRepository.findByUserName("jeong");
        assertThat("user2").isEqualTo(user2.getUserId());
    }

}