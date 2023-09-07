package com.nhnacademy.jpa.controller;

import com.nhnacademy.jpa.config.RootConfig;
import com.nhnacademy.jpa.config.WebConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.junit.jupiter.api.Assertions.*;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
class ResidentControllerTest {

    @Autowired
    WebApplicationContext context;
    MockMvc mockMvc;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilter(new CharacterEncodingFilter("UTF-8"))
                .build();
    }

    @Test
    void getResidents() throws Exception {

        mockMvc.perform(get("/resident?page=0&size=5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("residentList"));
    }

    @Test
    void getResident() throws Exception {

        mockMvc.perform(get("/resident/4"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("residentView"));
    }

    @Test
    void deleteResident() throws Exception {

        mockMvc.perform(get("/resident/8/delete"))
                .andDo(print())
                .andExpect(status().isFound());
    }

    @Test
    void household() throws Exception {

        mockMvc.perform(get("/resident/household/4"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("userFamily"));
    }
}