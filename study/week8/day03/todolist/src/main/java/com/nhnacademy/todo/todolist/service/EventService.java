package com.nhnacademy.todo.todolist.service;

import com.nhnacademy.todo.todolist.dto.DailyRegisterCountResponseDto;
import com.nhnacademy.todo.todolist.dto.EventCreateResponseDto;
import com.nhnacademy.todo.todolist.dto.EventDto;

import java.time.LocalDate;
import java.util.List;

public interface EventService {

    EventCreateResponseDto insert(String userId, EventDto eventDto);

    long update(String userId, long eventId, EventDto eventDto);

    void deleteDto(String userId, long eventId);

    EventDto getEvent(String userId, long eventId);

    List<EventDto> getEventListByMonth(String userId, Integer year, Integer month);

    List<EventDto> getEventListByDay(String userId, Integer year, Integer month, Integer day);

    DailyRegisterCountResponseDto getDailyRegisterCount(String userId, LocalDate targetDate);
    void deleteEventByDaily(String userId, LocalDate eventAt);
}
