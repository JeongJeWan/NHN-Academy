package com.nhnacademy.todo.todolist.repository;

import com.nhnacademy.todo.todolist.domain.Event;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository {
    Event save(Event event);

    void update(Event event);

    void deleteById(String userId, long eventId);

    Event getEvent(String userId, long eventId);

    List<Event> findAllByMonth(String userId, Integer year, Integer month);

    List<Event> findAllByDay(String userId, Integer year, Integer month, Integer day);

    long createByUserIdAndEventAt(String userId, LocalDate eventAt);

    void deleteByDaily(String userId, LocalDate eventAt);

}
