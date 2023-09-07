package com.nhnacademy.springmvcboard.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.springmvcboard.config.RootConfig;
import com.nhnacademy.springmvcboard.config.WebConfig;
import com.nhnacademy.springmvcboard.entity.EntityPost;
import com.nhnacademy.springmvcboard.entity.EntityUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

//@Disabled("temporary")
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
public class EntityPostRepositoryTest {

    @Autowired
    private EntityPostRepository entityPostRepository;

    @Test
    public void test1() {

        EntityPost post1 = entityPostRepository.findByPostId(1L);
        assertThat("user1").isEqualTo(post1.getUser().getUserId());

        EntityPost post2 = entityPostRepository.findByTitle("King Of Man");
        assertThat("user1").isEqualTo(post2.getUser().getUserId());

        List<EntityPost> posts1 = entityPostRepository.findByUser_UserId("user1");
        assertThat(posts1).hasSize(2);
    }

    @Test
    void test2() {
        List<EntityPost> posts = entityPostRepository.getEntityPostsByWriteTime(LocalDateTime.now());
        assertThat(posts).hasSize(5);
    }

    @Test
    void test3() {
        entityPostRepository.getEntityPostsWithAssociation()
                .stream()
                .map(EntityPost::getUser)
                .map(EntityUser::getPost)
                .collect(Collectors.toList());
    }

    @Test
    void test4() {
        entityPostRepository.readAllBy();
    }

    @Test
    void test5() {
        entityPostRepository.getAllEntityPostWithAssosiation();
    }

}