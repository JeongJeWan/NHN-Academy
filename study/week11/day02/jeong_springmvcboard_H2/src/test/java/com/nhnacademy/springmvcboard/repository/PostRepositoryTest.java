package com.nhnacademy.springmvcboard.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.springmvcboard.config.RootConfig;
import com.nhnacademy.springmvcboard.config.WebConfig;
import com.nhnacademy.springmvcboard.entity.EntityPost;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    public void test() {

        EntityPost post1 = postRepository.findByPostId(1L);
        assertThat("user1").isEqualTo(post1.getUser().getUserId());

        EntityPost post2 = postRepository.findByTitle("King Of Man");
        assertThat("user1").isEqualTo(post2.getUser().getUserId());

        List<EntityPost> posts1 = postRepository.findByUser_UserId("user1");
        assertThat(posts1).hasSize(2);
    }

}