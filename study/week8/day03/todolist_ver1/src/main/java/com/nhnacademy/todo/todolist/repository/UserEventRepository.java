package com.nhnacademy.todo.todolist.repository;

import com.nhnacademy.todo.todolist.domain.Event;
import com.nhnacademy.todo.todolist.domain.User;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public class UserEventRepository {

    private final ConcurrentMap<String, List<User>> eventMap = new ConcurrentHashMap<>();
    //id 채번을 위한 AtomicLong 사용
    private final AtomicLong autoIdx = new AtomicLong();

    public Long save(String eventDt, User user) {
        user.setId(autoIdx.incrementAndGet());

        List<User> userList = eventMap.computeIfAbsent(user.getUserId(), k -> new ArrayList<>());
        userList.add(user);
        return autoIdx.get();
    }

    public List<User> getUserById(String userId) {
        return eventMap.get(userId);
    }


    public List<User> getTodoUserList(String eventDt) {
        if(eventMap.containsKey(eventDt)) {
            return eventMap.get(eventDt);
        }
        return Collections.emptyList();
    }
}
