package com.nhnacademy.springmvcboard.Service;

import com.nhnacademy.springmvcboard.config.RootConfig;
import com.nhnacademy.springmvcboard.domain.Post;
import org.junit.jupiter.api.BeforeEach;
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
class PostEventServiceTest {

    @Autowired
    PostEventService postEventService;


    @Test
    @Rollback(value = false)
    @DisplayName("게시물 등록")
    void register() {
        Post post = new Post("title","content", "user1");
        postEventService.register(post);

        assertEquals("user1", postEventService.getPostById(1).getWriterUserId());
        assertEquals("title", postEventService.getPostById(1).getTitle());
    }

    @Test
    @Rollback(value = false)
    @DisplayName("게시물 수정")
    void modify() {
        Post dummyPost = new Post("James", "Hello", "user2");
        dummyPost.setPostId(1L);

        postEventService.modify(dummyPost);

        Post post = postEventService.getPostById(1);
        assertEquals(post.getTitle(), dummyPost.getTitle());
    }

    @Test
    @Rollback(value = false)
    @DisplayName("게시물 삭제")
    void delete() {
        postEventService.delete(1);
    }

    @Test
    @Rollback(value = false)
    @DisplayName("게시물 목록")
    void getPostList() {
        List<Post> postList = postEventService.getPostList(1, 10).getList();

        assertEquals("user2", postList.get(0).getWriterUserId());
    }

    @Test
    @Rollback(value = false)
    @DisplayName("게시물 조회")
    void getPostById() {
        Post post = postEventService.getPostById(1);

        assertEquals("user2", post.getWriterUserId());
    }
}