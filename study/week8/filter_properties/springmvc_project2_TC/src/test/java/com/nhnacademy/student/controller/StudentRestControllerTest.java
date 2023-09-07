package com.nhnacademy.student.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.student.config.RootConfig;
import com.nhnacademy.student.config.WebConfig;
import com.nhnacademy.student.domain.Gender;
import com.nhnacademy.student.exception.DuplicateStudentIdException;
import com.nhnacademy.student.exception.IdExistException;
import com.nhnacademy.student.request.StudentRegisterRequest;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextHierarchy(value = {
        @ContextConfiguration(classes = {RootConfig.class}),
        @ContextConfiguration(classes = {WebConfig.class})
})
public class StudentRestControllerTest {
    @Autowired
    WebApplicationContext context;

    ObjectMapper objectMapper = new ObjectMapper();

    MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilter(new CharacterEncodingFilter("UTF-8"))
                .build();
    }

    @Test
    @DisplayName("학생등록")
    void registerStudent() throws Exception {
        StudentRegisterRequest studentRegisterRequest = new StudentRegisterRequest();
        ReflectionTestUtils.setField(studentRegisterRequest, "id", "marco");
        ReflectionTestUtils.setField(studentRegisterRequest, "name", "마르코");
        ReflectionTestUtils.setField(studentRegisterRequest, "age", 30);
        ReflectionTestUtils.setField(studentRegisterRequest, "gender", Gender.M);

        mockMvc.perform(post("/api/students").content(objectMapper.writeValueAsString(studentRegisterRequest)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    @DisplayName("학생등록-나이=50")
    void registerStudent_max_age_exception() throws Exception {
        StudentRegisterRequest studentRegisterRequest = new StudentRegisterRequest();
        ReflectionTestUtils.setField(studentRegisterRequest, "id", "marco");
        ReflectionTestUtils.setField(studentRegisterRequest, "name", "마르코");
        ReflectionTestUtils.setField(studentRegisterRequest, "age", 50);
        ReflectionTestUtils.setField(studentRegisterRequest, "gender", Gender.M);

        MvcResult mvcResult = mockMvc.perform(post("/api/students").content(objectMapper.writeValueAsString(studentRegisterRequest)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is5xxServerError())
                .andReturn();

        String messaege = mvcResult.getResolvedException().getMessage();
    }

    @Test
    @DisplayName("학생등록_아이디중복")
    void registerStudent_exite_id() throws Exception {
        StudentRegisterRequest studentRegisterRequest = new StudentRegisterRequest();
        ReflectionTestUtils.setField(studentRegisterRequest, "id", "student1");
        ReflectionTestUtils.setField(studentRegisterRequest, "name", "마르코");
        ReflectionTestUtils.setField(studentRegisterRequest, "age", 30);
        ReflectionTestUtils.setField(studentRegisterRequest, "gender", Gender.M);

        MvcResult mvcResult = mockMvc.perform(post("/api/students").content(objectMapper.writeValueAsString(studentRegisterRequest)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        assertEquals(DuplicateStudentIdException.class, mvcResult.getResolvedException().getClass());
    }

    @Test
    @DisplayName("학생조회")
    void getStudent() throws Exception {
        mockMvc.perform(get("/api/students/marco"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("marco"))
                .andExpect(jsonPath("$.name").value("마르코"))
                .andExpect(jsonPath("$.age").value(30))
                .andExpect(jsonPath("$.gender").value("M"))
                .andReturn();
    }

    @Test
    @DisplayName("학생수정")
    void updateStudent() throws Exception {
        StudentRegisterRequest studentRegisterRequest = new StudentRegisterRequest();
        ReflectionTestUtils.setField(studentRegisterRequest, "name", "마르코");
        ReflectionTestUtils.setField(studentRegisterRequest, "age", 22);
        ReflectionTestUtils.setField(studentRegisterRequest, "gender", Gender.M);

        mockMvc.perform(put("/api/students/marco")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(studentRegisterRequest)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
}