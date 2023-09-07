package com.nhnacademy.springmvcboard.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.springmvcboard.config.RootConfig;
import com.nhnacademy.springmvcboard.config.WebConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
public class UserEntityTest {

    @PersistenceContext
    EntityManager entityManager;

    @Test
    @Rollback(value = false)
    void test1() {
        User user1 = new User();
        user1.setUserId("user1");
        user1.setUserPassword("1234");
        user1.setUserName("marco");
        user1.setProfileFileName("프로필1");
        user1.setCreatedAt(LocalDateTime.now());

        entityManager.persist(user1);

        User user2 = entityManager.find(User.class, "user1");
        assertThat(user2).isEqualTo(user1);

        user2.setUserPassword("5678");
        entityManager.flush();
    }
}