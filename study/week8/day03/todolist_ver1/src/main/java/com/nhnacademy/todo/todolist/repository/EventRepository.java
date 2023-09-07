package com.nhnacademy.todo.todolist.repository;

import com.nhnacademy.todo.todolist.domain.Event;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public class EventRepository {
    private final ConcurrentMap<String, List<Event>> eventMap = new ConcurrentHashMap<>();
    //id 채번을 위한 AtomicLong 사용
    private final AtomicLong autoIdx = new AtomicLong();

    public Long save(String eventAt, Event event) {
        event.setId(autoIdx.incrementAndGet());

        List<Event> eventList = eventMap.computeIfAbsent(eventAt, k -> new ArrayList<>());
        eventList.add(event);
        return autoIdx.get();
    }

    public List<Event> getTodoItemList(String eventAt){
        if(eventMap.containsKey(eventAt)){
            return eventMap.get(eventAt);
        }
        return Collections.emptyList();
    }

    public void deleteById(long eventId) {
        for(List<Event> eventList : eventMap.values()) {
            for(Event event : eventList) {
                if (event.getId() == eventId) {
                    eventList.remove(event);
                    return;
                }
            }
        }
    }

    public void deleteByDaily(String eventAt) {
        eventMap.remove(eventAt);
    }

    public Event getById(long eventId) {
        for(List<Event> eventList : eventMap.values()) {
            for(Event event : eventList) {
                if(event.getId() == eventId) {
                    return  event;
                }
            }
        }
        return null;
    }

    public List<Event> findAllByMonth(Integer year, Integer month) {
        List<Event> resultEvent = new ArrayList<>();
        for(List<Event> eventList : eventMap.values()) {
            for(Event event : eventList) {
                LocalDate eventAt = LocalDate.parse(event.getEventAt());
                if (eventAt.getYear() == year && eventAt.getMonthValue() == month) {
                    resultEvent.add(event);
                }
            }
        }
        return resultEvent;
    }

    public List<Event> findAllByDay(Integer year, Integer month, Integer day) {
        List<Event> resultEvent = new ArrayList<>();
        for(List<Event> eventList : eventMap.values()) {
            for(Event event : eventList) {
                LocalDate eventAt = LocalDate.parse(event.getEventAt());
                if(eventAt.getYear() == year && eventAt.getMonthValue() == month && eventAt.getDayOfMonth() == day) {
                    resultEvent.add(event);
                }
            }
        }
        return resultEvent;
    }

}
