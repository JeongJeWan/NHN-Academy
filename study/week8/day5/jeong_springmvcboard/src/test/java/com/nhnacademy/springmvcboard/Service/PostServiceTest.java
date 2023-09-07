package com.nhnacademy.springmvcboard.Service;

import com.nhnacademy.springmvcboard.domain.Post;
import com.nhnacademy.springmvcboard.exception.DuplicatePostIdException;
import com.nhnacademy.springmvcboard.exception.PostNotFoundException;
import com.nhnacademy.springmvcboard.repository.MapPostRepository;
import com.nhnacademy.springmvcboard.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class PostServiceTest {

    private static final PostRepository postRepository = new MapPostRepository();
    private static PostService postService;
    @BeforeEach
    public void beforeEach() {
        for(int i=0; i < 5; i++) {
            Post post= new Post("title" +i, "content" +i, "id" +i);
            postRepository.register(post);
        }
        postService = new PostService(postRepository);
    }

    @Test
    void getPostText() {
        Post post = postService.getPostById(1);

        assertEquals(1, post.getPostId());
        assertEquals("content0", post.getContent());
        assertEquals("title0", post.getTitle());
        assertEquals(0, post.getViewCount());
    }

    @Test
    @DisplayName("게시물 목")
    void postList() throws Exception {
        List<Post> posts = postService.getPosts();

        assertEquals(posts, postRepository.getPosts());
    }

    @Test
    @DisplayName("게시물 등록")
    void postRegister() throws Exception {
        Post post = new Post("marco", "nhnacademy", "jeong");
        postService.register(post);
        Post postRegister = postService.getPostById(post.getPostId());

        assertEquals("marco", postRegister.getTitle());
        assertEquals("nhnacademy", postRegister.getContent());
        assertEquals("jeong", postRegister.getWriterUserId());
    }

    @Test
    @DisplayName("게시물 삭제")
    void postDelete() throws Exception {
        Post post = postService.getPostById(1);
        log.info("ASDSADASDSAD:{}", post.getWriterUserId());
        Post post1 = postService.delete(1);
        assertEquals(post.getTitle(), post1.getTitle());
        assertEquals(post.getContent(), post1.getContent());
        assertEquals(post.getWriterUserId(), post1.getWriterUserId());
    }

    @Test
    @DisplayName("게시물 등록")
    void postModify() {
        postService.modify(new Post(1,"test", "test2", "jeong",0));

        Post post = postService.getPostById(1);

        assertEquals("test2", post.getContent());
        assertEquals("test", post.getTitle());
        assertEquals("jeong", post.getWriterUserId());
    }

    @Test
    @DisplayName("중복 아이디 오류")
    void postDupId() {
        Post post = new Post(1, "asda", "asd", "asd", 2);

        assertThrows(DuplicatePostIdException.class, () -> postService.register(post));
    }

    @Test
    @DisplayName("해당 id 게시물 존재하지 않음")
    void postNotFound() {

        assertThrows(PostNotFoundException.class, () -> postService.getPostById(324));
    }
}