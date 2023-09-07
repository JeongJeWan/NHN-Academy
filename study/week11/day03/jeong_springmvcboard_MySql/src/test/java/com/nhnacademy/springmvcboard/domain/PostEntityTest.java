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
public class PostEntityTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @Rollback(value = false)
    void test() {
        Post post1 = new Post();
        post1.setTitle("marco23");
        post1.setContent("이런 맛은 없었다");
        post1.setWriterUserId("마르코 등장");
        post1.setViewCount(1);
        post1.setWriteTime(LocalDateTime.now());

        entityManager.persist(post1);

        Post post2 = entityManager.find(Post.class, post1.getPostId());
        assertThat(post2).isEqualTo(post1);

        post1.setTitle("marco story");
        entityManager.flush();
    }
}