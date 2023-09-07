package com.nhnacademy.todo.todolist.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.todo.todolist.config.RootConfig;
import com.nhnacademy.todo.todolist.config.WebConfig;
import com.nhnacademy.todo.todolist.domain.Event;
import com.nhnacademy.todo.todolist.domain.EventDto;
import com.nhnacademy.todo.todolist.repository.EventRepository;
import lombok.extern.slf4j.Slf4j;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextHierarchy(value = {
        @ContextConfiguration(classes = {RootConfig.class}),
        @ContextConfiguration(classes = {WebConfig.class})
})
@Slf4j
public class EventControllerTest {

    @Autowired
    WebApplicationContext context;

    ObjectMapper objectMapper = new ObjectMapper();

    EventRepository eventRepository = new EventRepository();

    MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilter(new CharacterEncodingFilter("UTF-8"))
                .build();
    }

    @Test
    @DisplayName("등록")
    void saveEvent() throws Exception {
        // given
        EventDto event = new EventDto();
        ReflectionTestUtils.setField(event, "subject", "Spring framework 학습");
        ReflectionTestUtils.setField(event, "eventAt", "2023-02-15");
        // when
        mockMvc.perform(post("/api/calendar/events")
                        .header("X-USER-ID", "marco")
                        .content(objectMapper.writeValueAsString(event))
                        .contentType(MediaType.APPLICATION_JSON))
                        // then
                        .andExpect(status().isCreated())
                        .andReturn();
    }

    @Test
    @DisplayName("일일 등록 카운트")
    void countEvent() throws Exception {
        // given
        mockMvc.perform(get("/api/calendar/daily-register-count")
                .param("date", "2023-04-20"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value(1))
                .andReturn();
    }

    @Test
    @DisplayName("월별 조회")
    void getEventDateMonthList() throws Exception {
        mockMvc.perform(get("/api/calendar/events")
                .param("year", "2023")
                .param("month", "04"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].eventAt").value("2023-04-20"))
                .andReturn();
    }

    @Test
    @DisplayName("일별 조회")
    void getEventDateDayList() throws Exception {
        mockMvc.perform(get("/api/calendar/events")
                        .param("year", "2023")
                        .param("month", "04")
                        .param("day", "20"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].eventAt").value("2023-04-20"))
                .andReturn();
    }

    @Test
    @DisplayName("정상적으로 이벤트 저장 후, 조회")
    public void saveAndGetEvent() throws Exception {
        // given
        EventDto eventDto = new EventDto();
        eventDto.setEventAt("2023-04-20");
        eventDto.setSubject("asd");

        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(eventDto);

        // when
        long eventID = eventRepository.save(content, new Event(eventDto.getSubject(),eventDto.getEventAt()));
        log.info("<<<<<<>>>>>>>>>:{}",eventID);
        // then
        mockMvc.perform(get("/api/calendar/events/" + eventID)
                        .header("X-USER-ID", "marco"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.subject").value(eventDto.getSubject()));
    }

}