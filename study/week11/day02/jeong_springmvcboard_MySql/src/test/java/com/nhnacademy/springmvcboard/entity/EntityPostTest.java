package com.nhnacademy.springmvcboard.entity;

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
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})

class EntityPostTest {

    @PersistenceContext
    EntityManager entityManager;

    @Test
    @Rollback(value = false)
    void test() {
        EntityUser user = new EntityUser();
        user.setUserId("user1");
        user.setUserPassword("1234");
        user.setUserName("marco");
        user.setProfileFileName("프로필1");
        user.setCreatedAt(LocalDateTime.now());

        EntityPost post1 = new EntityPost();
        post1.setTitle("title1");
        post1.setContent("content1");
        post1.setWriteTime(LocalDateTime.now());
        post1.setViewCount(1);
        post1.setUser(user);

        EntityPost post2 = new EntityPost();
        post2.setTitle("title2");
        post2.setContent("content2");
        post2.setWriteTime(LocalDateTime.now());
        post2.setViewCount(1);
        post2.setUser(user);

        List<EntityPost> posts = new ArrayList<>();
        posts.add(post1);
        posts.add(post2);

        user.setPost(posts);

        entityManager.persist(user);

        entityManager.flush();
    }
}