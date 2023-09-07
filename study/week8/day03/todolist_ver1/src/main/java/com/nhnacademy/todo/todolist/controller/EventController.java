package com.nhnacademy.todo.todolist.controller;


import com.nhnacademy.todo.todolist.domain.*;
import com.nhnacademy.todo.todolist.exception.ErrorUserId;
import com.nhnacademy.todo.todolist.exception.EventIdNotFoundException;
import com.nhnacademy.todo.todolist.repository.EventRepository;
import com.nhnacademy.todo.todolist.repository.UserEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.MethodNotAllowedException;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/calendar")
public class EventController {
    private final EventRepository eventRepository;
    private final UserEventRepository userRepository;

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/events")
    public List<Event> getEventDateList(@RequestParam(name = "year", required = true) Integer year,
                                        @RequestParam(value = "month", required = true) Integer month,
                                        @RequestParam(value = "day", required = false) Integer day) {

        if(day != null) {
            //일별 조회
            return eventRepository.findAllByDay(year, month, day);
        } else
            //월별 조회
            return eventRepository.findAllByMonth(year, month);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/events")
    public long saveEvent(@RequestHeader(name = "X-USER-ID", required = true) String userId, @RequestBody EventDto eventDto) {
        long userID = userRepository.save(eventDto.getEventAt(), new User(userId, eventDto.getSubject(), eventDto.getEventAt()));
        log.info("User SAVE ID: {}", userID);
        long eventID = eventRepository.save(eventDto.getEventAt(), new Event(eventDto.getSubject(), eventDto.getEventAt()));
        log.info("Event SAVE ID: {}", eventID);
        return eventID;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/daily-register-count")
    public DailyRegisterCountResponseDto countEvent(@RequestParam(name = "date") String date) {
        long count = eventRepository.getTodoItemList(date).size();
        return new DailyRegisterCountResponseDto(count);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/events/{event-id}")
    public void deleteEventByID(@PathVariable("event-id") long enventId) {
        log.info("Event:{}", enventId);
        eventRepository.deleteById(enventId);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/events/daily/{eventAt}")
    public void deleteEventByDaily(@PathVariable("eventAt") String eventAt) {
        log.info("Event:{}", eventAt);
        eventRepository.deleteByDaily(eventAt);
        log.info("Event:{}", eventAt);
    }

    @GetMapping("/events/{event-id}")
    public ResponseEntity<User> getEventById(@RequestHeader(name = "X-USER-ID") String userId, @PathVariable("event-id") long eventId) throws EventIdNotFoundException {
        Long id;
        try{
            id = eventId;
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
        Event event = eventRepository.getById(eventId);
        if(Objects.isNull(event)) {
            throw new EventIdNotFoundException(eventId);
        }

        List<User> userList = userRepository.getUserById(userId);
        for (User user : userList) {
            if(eventId == user.getId()) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
        }
        throw new ErrorUserId();
    }


    @ExceptionHandler(MethodNotAllowedException.class)
    @PatchMapping("/events/{event-id}")
    public ResponseEntity<ErrorMessage405> handlerMethodNotAllowedException(@RequestHeader(name = "X-USER-ID") String userId, @PathVariable("event-id") long eventId){
        return new ResponseEntity<>(new ErrorMessage405(405, "Method Not Allowed", "/api/calendar/events/1"), HttpStatus.METHOD_NOT_ALLOWED);
    }

}
