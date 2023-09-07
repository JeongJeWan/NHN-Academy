package com.nhnacademy.student.repository;



import com.nhnacademy.student.domain.User;
import com.nhnacademy.student.request.LoginRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    private final Map<String, User> userMap = new HashMap<>();

    @Override
    public boolean exists(String id) {
        return userMap.containsKey(id);
    }

    @Override
    public boolean matches(User user, LoginRequest loginRequest) {
        if(user.getUserId().equals(loginRequest.getUserId()) && user.getUserPassword().equals(loginRequest.getUserPassword())){
            return true;
        }else {
            return false;
        }
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
