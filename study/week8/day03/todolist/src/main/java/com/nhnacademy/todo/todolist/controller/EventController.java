package com.nhnacademy.todo.todolist.controller;

import com.nhnacademy.todo.todolist.dto.EventCreateResponseDto;
import com.nhnacademy.todo.todolist.dto.EventDto;
import com.nhnacademy.todo.todolist.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/calendar/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(value = {"/", ""})
    public EventCreateResponseDto createEvent(@RequestHeader(name = "X-USER-ID", required = true) String userId,
                                                              @RequestBody EventDto eventDto) {
        return eventService.insert(userId, eventDto);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PutMapping(value = {"/{event-id}"})
    public long updateEvent(@RequestHeader(name = "X-USER-ID", required = true) String userId, @PathVariable("event-id") long eventId, @RequestBody EventDto eventDto) {
        return eventService.update(userId, eventId, eventDto);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{event-id}")
    public void deleteEvent(@RequestHeader(name = "X-USER-ID", required = true) String userId, @PathVariable("event-id") long eventId) {
        eventService.deleteDto(userId, eventId);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/daily/{eventAt}")
    public void deleteEventByDaily(@RequestHeader(name = "X-USER-ID", required = true) String userId,@PathVariable("eventAt") String eventAt) {
        LocalDate localDate = LocalDate.parse(eventAt);

        eventService.deleteEventByDaily(userId, localDate);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(value = "/{event-id}")
    public EventDto getEvent(@RequestHeader(value = "X-USER-ID", required = true) String userId,
                             @PathVariable("event-id") long eventId) {
        return eventService.getEvent(userId, eventId);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/")
    public List<EventDto> getEventList(@RequestHeader(name = "X-USER-ID", required = true) String userId,
                                       @RequestParam(value = "year") Integer year,
                                       @RequestParam(value = "month") Integer month,
                                       @RequestParam(value = "day", required = false) Integer day) {
        if (day != null) {
            // 일별 조회
            return eventService.getEventListByDay(userId,year, month, day);
        } else {
            // 월별 조회
            return eventService.getEventListByMonth(userId, year, month);
        }
    }


}
