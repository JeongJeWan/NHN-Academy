package com.nhnacademy.todo.todolist.service.impl;

import com.nhnacademy.todo.todolist.domain.Event;
import com.nhnacademy.todo.todolist.dto.DailyRegisterCountResponseDto;
import com.nhnacademy.todo.todolist.dto.EventCreateResponseDto;
import com.nhnacademy.todo.todolist.dto.EventDto;
import com.nhnacademy.todo.todolist.repository.EventRepository;
import com.nhnacademy.todo.todolist.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Override
    public EventCreateResponseDto insert(String userId, EventDto eventDto) {
        Event target = new Event(userId, eventDto.getSubject(), eventDto.getEventAt());
        Event event = eventRepository.save(target);
        return new EventCreateResponseDto(event.getId());
    }

    @Override
    public long update(String userId, long eventId, EventDto eventDto) {
        Event target = new Event(userId, eventDto.getSubject(), eventDto.getEventAt());
        target.setId(eventId);
        eventRepository.update(target);
        return eventId;
    }

    @Override
    public void deleteDto(String userId, long eventId) {
        eventRepository.deleteById(userId, eventId);
    }

    @Override
    public EventDto getEvent(String userId, long eventId) {
        Event event = eventRepository.getEvent(userId, eventId);
        return new EventDto(event.getId(), event.getSubject(), event.getEventAt());
    }

    @Override
    public List<EventDto> getEventListByMonth(String userId, Integer year, Integer month) {
        List<Event> eventList = eventRepository.findAllByMonth(userId, year, month);
        List<EventDto> eventDtos = new ArrayList<>();
        for(Event event : eventList) {
            eventDtos.add(new EventDto(event.getId(), event.getSubject(), event.getEventAt()));
        }
        return eventDtos;
    }

    @Override
    public List<EventDto> getEventListByDay(String userId, Integer year, Integer month, Integer day) {
        List<Event> eventList = eventRepository.findAllByDay(userId, year, month, day);
        List<EventDto> eventDtos = new ArrayList<>();
        for(Event event : eventList) {
            eventDtos.add(new EventDto(event.getId(), event.getSubject(), event.getEventAt()));
        }
        return eventDtos;
    }


    @Override
    public DailyRegisterCountResponseDto getDailyRegisterCount(String userId, LocalDate targetDate) {
        long count = eventRepository.createByUserIdAndEventAt(userId, targetDate);
        return new DailyRegisterCountResponseDto(count);
    }

    @Override
    public void deleteEventByDaily(String userId, LocalDate eventAt) {
        eventRepository.deleteByDaily(userId, eventAt);
    }


}
