package com.nhnacademy.student.repository;

import com.nhnacademy.student.domain.User;
import com.nhnacademy.student.request.LoginRequest;

public interface UserRepository {
    boolean exists(String id);
    boolean matches(User user, LoginRequest loginRequest);

    User getUser(String id);

    User addUser(String id, String name, String password);

}
