package com.nhnacademy.todo.todolist.repository.impl;

import com.nhnacademy.todo.todolist.domain.Event;
import com.nhnacademy.todo.todolist.repository.EventRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

public class EventRepositoryImpl implements EventRepository {
    private final ConcurrentMap<String, List<Event>> eventMap = new ConcurrentHashMap<>();
    //id 채번을 위한 AtomicLong 사용
    private final AtomicLong autoIdx = new AtomicLong();

    @Override
    public Event save(Event event) {
        long id = autoIdx.incrementAndGet();
        event.setId(id);

        String userId = event.getUserId();
        List<Event> eventList = eventMap.computeIfAbsent(userId, k -> new ArrayList<>());
        eventList.add(event);

        return event;
    }

    @Override
    public void update(Event event) {
        // userId와 eventId로 이벤트 찾기
        String userId = event.getUserId();
        long eventId = event.getId();
        Event targetEvent = getEvent(userId, eventId);

        // 이벤트 수정
        targetEvent.setSubject(event.getSubject());
        targetEvent.setEventAt(event.getEventAt());
    }

    @Override
    public void deleteById(String userId, long eventId) {
        List<Event> eventList = eventMap.get(userId);
        if (eventList != null) {
            for (Event event : eventList) {
                if (event.getId() == eventId) {
                    eventList.remove(event);
                    return;
                }
            }
        }
    }

    @Override
    public Event getEvent(String userId, long eventId) {
        List<Event> eventList = eventMap.get(userId);
        if (eventList != null) {
            for (Event event : eventList) {
                if (event.getId() == eventId) {
                    return event;
                }
            }
        }
        return null;
    }

    @Override
    public List<Event> findAllByMonth(String userId, Integer year, Integer month) {
        List<Event> eventList = eventMap.get(userId);
        List<Event> resultList = new ArrayList<>();
        if (eventList != null) {
            for (Event event : eventList) {
                LocalDate eventAt = event.getEventAt();
                if (eventAt.getYear() == year && eventAt.getMonthValue() == month) {
                    resultList.add(event);
                }
            }
        }
        return resultList;
    }

    @Override
    public List<Event> findAllByDay(String userId, Integer year, Integer month, Integer day) {
        List<Event> eventList = eventMap.get(userId);
        List<Event> resultList = new ArrayList<>();
        if (eventList != null) {
            for (Event event : eventList) {
                LocalDate eventAt = event.getEventAt();
                if (eventAt.getYear() == year && eventAt.getMonthValue() == month && eventAt.getDayOfMonth() == day) {
                    resultList.add(event);
                }
            }
        }
        return resultList;
    }



    @Override
    public long createByUserIdAndEventAt(String userId, LocalDate eventAt) {
        // 이벤트 저장을 위한 id 생성
        long id = autoIdx.incrementAndGet();

        // 이벤트 생성 및 저장
        Event event = new Event(id, userId, "", eventAt);
        List<Event> eventList = eventMap.computeIfAbsent(userId, k -> new ArrayList<>());
        eventList.add(event);

        return id;
    }

    @Override
    public void deleteByDaily(String userId, LocalDate eventAt) {
        List<Event> eventList = eventMap.get(userId);
        if (eventList != null) {
            eventList.removeIf(event -> event.getEventAt().equals(eventAt));
        }
    }

}
