package com.nhnacademy.board.repository;

import com.nhnacademy.board.user.UserImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserRepositoryImpl implements UserRepository{

    private final Map<String, UserImpl> userMap;

    public UserRepositoryImpl() {
        userMap = new ConcurrentHashMap<>();
    }

    @Override
    public void add(UserImpl user) {
        userMap.put(user.getId(), user);
    }

    @Override
    public void modify(UserImpl user) {
        userMap.put(user.getId(), user);
    }

    @Override
    public UserImpl remove(String id) {
        return userMap.remove(id);
    }

    @Override
    public UserImpl getUser(String id) {
        return userMap.get(id);
    }

    @Override
    public List<UserImpl> getUsers() {
        return new ArrayList<>(userMap.values());
    }
}
