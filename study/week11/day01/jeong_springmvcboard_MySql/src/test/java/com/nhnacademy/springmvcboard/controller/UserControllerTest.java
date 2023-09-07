package com.nhnacademy.springmvcboard.controller;

import com.nhnacademy.springmvcboard.config.RootConfig;
import com.nhnacademy.springmvcboard.config.WebConfig;
import com.nhnacademy.springmvcboard.domain.User;
import com.nhnacademy.springmvcboard.request.UserRegisterRequest;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@ContextHierarchy(
        value = {
                @ContextConfiguration(classes = {RootConfig.class}),
                @ContextConfiguration(classes = WebConfig.class),
        }
)
@WebAppConfiguration
@Slf4j
class UserControllerTest {
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
    @DisplayName("사용자 등록")
    void registerUser() throws Exception {
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setUserId("user1");
        userRegisterRequest.setUserPassword("1234");
        userRegisterRequest.setUserName("marco");
        userRegisterRequest.setProfileFileName("프로필1");

        mockMvc.perform(post("/user/register.do")
                .flashAttr("userRegisterRequest", userRegisterRequest))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/user/list.do"));
    }

    @Test
    @DisplayName("사용자 수정")
    void modifyUser() throws Exception {
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setUserId("user1");
        userRegisterRequest.setUserPassword("1234");
        userRegisterRequest.setUserName("marco");
        userRegisterRequest.setProfileFileName("프로필4");

        mockMvc.perform(post("/user/update.do?userId=user1")
                .flashAttr("userRegisterRequest",userRegisterRequest))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/user/list.do"));
    }

    @Test
    @DisplayName("사용자 목록")
    void listUser() throws Exception {
        User user = new User("user1", "1234", "marco", "프로필1");

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("user", user);

        mockMvc.perform(get("/user/list.do")
                        .session(session)
                .param("page", "1")
                .param("size", "10"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("user/userList"));
    }

    @Test
    @DisplayName("사용자 삭제")
    void deleteUser() throws Exception {
        User user = new User("user1", "1234", "marco", "프로필1");

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("userId", user.getUserId());

        mockMvc.perform(post("/user/delete.do?userId=user1")
                .session(session))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/user/list.do"));
    }

    @Test
    @DisplayName("사용자 조회")
    void viewUser() throws Exception {
        User user = new User("user1", "1234", "marco", "프로필1");

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("user", user);

        mockMvc.perform(get("/user/view.do?userId=user1")
                .session(session))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("user/userView"));
    }
}