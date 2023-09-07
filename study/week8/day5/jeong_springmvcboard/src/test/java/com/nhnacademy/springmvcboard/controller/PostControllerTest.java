package com.nhnacademy.springmvcboard.controller;

import com.nhnacademy.springmvcboard.Service.PostService;
import com.nhnacademy.springmvcboard.config.RootConfig;
import com.nhnacademy.springmvcboard.config.WebConfig;
import com.nhnacademy.springmvcboard.domain.Post;
import com.nhnacademy.springmvcboard.exception.DuplicatePostIdException;
import com.nhnacademy.springmvcboard.exception.PostNotFoundException;
import com.nhnacademy.springmvcboard.repository.MapPostRepository;
import com.nhnacademy.springmvcboard.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextHierarchy(
        value = {
                @ContextConfiguration(classes = {RootConfig.class}),
                @ContextConfiguration(classes = WebConfig.class),
        }
)
@WebAppConfiguration
@Slf4j
public class PostControllerTest {

    @Autowired
    WebApplicationContext context;

    MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilter(new CharacterEncodingFilter("UTF-8"))
                .build();
    }




}