package com.nhnacademy.student.repository;



import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    private final Map<String, User> userMap = new HashMap<>();

    @Override
    public boolean exists(String id) {
        return userMap.containsKey(id);
    }

    @Override
    public boolean matches(String id, String password) {
        return Optional.ofNullable(getUser(id))
                       .map(user -> user.getUserPassword().equals(password))
                       .orElse(false);
    }
    @Override
    public User getUser(String id) {
        return exists(id) ? userMap.get(id) : null;
    }
    @Override
    public User addUser(String id, String name,String password) {
        User user = new User(id,name,password);
        userMap.put(id,user);
        return user;
    }
}
