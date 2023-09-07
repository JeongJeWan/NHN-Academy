package com.nhnacademy.springmvcboard.controller;

import com.nhnacademy.springmvcboard.config.RootConfig;
import com.nhnacademy.springmvcboard.config.WebConfig;
import com.nhnacademy.springmvcboard.domain.User;
import com.nhnacademy.springmvcboard.request.PostRegisterRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilter(new CharacterEncodingFilter("UTF-8"))
                .build();
    }


    @Test
    @DisplayName("게시물 생성")
    void createPost() throws Exception {
        User user = new User("user1", "1234", "marco", "프로필1");
        PostRegisterRequest postRegisterRequest = new PostRegisterRequest();
        postRegisterRequest.setTitle("Test Post");
        postRegisterRequest.setContent("This is a test post.");
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("userId", user.getUserId());

        // Mock the behavior of the postEventService
        long postId = 1L;

        // Perform the registration request
        mockMvc.perform(post("/post/register.do")
                        .session(session)
                        .flashAttr("postRegisterRequest", postRegisterRequest))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/post/list.do"));

    }

    @Test
    @DisplayName("게시물 수정")
    void modifyPost() throws Exception {
        PostRegisterRequest postRegisterRequest = new PostRegisterRequest();
        postRegisterRequest.setTitle("Test Post");
        postRegisterRequest.setContent("This is a test post.");

        mockMvc.perform(post("/post/update.do?postId=6")
                        .flashAttr("postRegisterRequest", postRegisterRequest))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/post/list.do"));
    }

    @Test
    @DisplayName("게시물 목록")
    void listPost() throws Exception {
        User user = new User("user1", "1234", "marco", "프로필1");

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("user", user);

        mockMvc.perform(get("/post/list.do")
                        .session(session)
                        .param("page", "1")
                        .param("size", "10"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post/postList"));
    }

    @Test
    @DisplayName("게시물 삭제")
    void deletePost() throws Exception {
        User user = new User("user1", "1234", "marco", "프로필1");

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("userId", user.getUserId());

        mockMvc.perform(post("/post/delete.do?postId=6")
                .session(session))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/post/list.do"));
    }

    @Test
    @DisplayName("게시물 조회")
    void viewPost() throws Exception {
        User user = new User("user1", "1234", "marco", "프로필1");

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("user", user);

        mockMvc.perform(get("/post/view.do?postId=2")
                .session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post/postView"));
    }
}