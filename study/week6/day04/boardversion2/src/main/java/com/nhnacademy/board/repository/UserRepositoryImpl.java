package com.nhnacademy.board.repository;

import com.nhnacademy.board.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserRepositoryImpl implements UserRepository{

    private final Map<String, User> userMap;

    public UserRepositoryImpl() {
        userMap = new ConcurrentHashMap<>();
    }

    @Override
    public void add(User user) {
        userMap.put(user.getId(), user);
    }

    @Override
    public void modify(User user) {
        userMap.put(user.getId(), user);
    }

    @Override
    public User remove(String id) {
        return userMap.remove(id);
    }

    @Override
    public User getUser(String id) {
        return userMap.get(id);
    }

    @Override
    public List<User> getUsers() {
        return new ArrayList<>(userMap.values());
    }
}
