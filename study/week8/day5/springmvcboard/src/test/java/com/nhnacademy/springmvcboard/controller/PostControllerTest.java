package com.nhnacademy.springmvcboard.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.springmvcboard.config.RootConfig;
import com.nhnacademy.springmvcboard.config.WebConfig;
import com.nhnacademy.springmvcboard.domain.Post;
import com.nhnacademy.springmvcboard.repository.MapPostRepository;
import com.nhnacademy.springmvcboard.repository.PostRepository;
import com.nhnacademy.springmvcboard.request.PostRegisterRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextHierarchy(value = {
        @ContextConfiguration(classes = {RootConfig.class}),
        @ContextConfiguration(classes = {WebConfig.class})
})
class PostControllerTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PostRepository postRepository;
    private long nextId;


    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(postRepository, "postMap", new ConcurrentHashMap<>());
        ReflectionTestUtils.setField(nextId, "nextId", 1L);
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .build();
        for (int i = 0; i < 5; i++) {
            Post post = new Post();
            post.setPostId(nextId++);
            post.setTitle("");
        }
    }

    @Test
    @DisplayName("게시글 등록")
    void register() throws Exception {
        // given
        PostRegisterRequest request = new PostRegisterRequest();
        request.setTitle("테스트 제목");
        request.setContent("테스트 내용");

        // when, then
        mockMvc.perform(post("/post/register.do")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(request.getTitle()))
                .andExpect(jsonPath("$.content").value(request.getContent()));
    }
}