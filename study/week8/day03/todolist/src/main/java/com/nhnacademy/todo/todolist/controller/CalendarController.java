package com.nhnacademy.todo.todolist.controller;

import com.nhnacademy.todo.todolist.dto.DailyRegisterCountResponseDto;
import com.nhnacademy.todo.todolist.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("api/calendar")
@RequiredArgsConstructor
public class CalendarController {

    private final EventService eventService;

    @GetMapping("/daily-register-count")
    public DailyRegisterCountResponseDto dailyRegisterCountResponseDto(
            @RequestHeader(name = "X-USER-ID", required = true) String userId,
            @RequestParam(name = "date") String date) {

        LocalDate targetDate = LocalDate.parse(date);
        return eventService.getDailyRegisterCount(userId, targetDate);
    }
}
