package com.nhnacademy.springmvcboard.mapper;

import com.nhnacademy.springmvcboard.config.RootConfig;
import com.nhnacademy.springmvcboard.domain.Post;
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
public class PostMapperTest {

    @Autowired
    PostMapper postMapper;

    @Order(1)
    @Test
    @Rollback(value = false)
    @DisplayName("게시물 등록")
    void register() {
        Post post= new Post("marco", "GOOD BOY", "user1");
        postMapper.register(post);

        assertEquals("user1", postMapper.getPostById(1).getWriterUserId());
    }

    @Order(2)
    @Test
    @Rollback(value = false)
    @DisplayName("게시물 조회")
    void getPosts() {
        List<Post> postList = postMapper.getPosts();

        assertEquals("user1", postList.get(0).getWriterUserId());
    }

    @Order(3)
    @Test
    @Rollback(value = false)
    @DisplayName("게시물 수정")
    void modify() {
        Post dummyPost = new Post("James", "Hello World", "user1");
        dummyPost.setPostId(1);

        postMapper.modify(dummyPost);

        Post post = postMapper.getPostById(1);

        assertEquals(post.getTitle(), dummyPost.getTitle());
    }

    @Order(5)
    @Test
    @Rollback(value = false)
    @DisplayName("게시물 삭제")
    void delete() {
        postMapper.delete(1);

        assertNull(postMapper.getPostById(1));
    }

    @Order(4)
    @Test
    void getPostById() {
        Post post = postMapper.getPostById(1);

        assertEquals("user1", post.getWriterUserId());
    }
}