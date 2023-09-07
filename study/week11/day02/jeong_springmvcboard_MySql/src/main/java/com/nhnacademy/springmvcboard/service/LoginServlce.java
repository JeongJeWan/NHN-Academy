package com.nhnacademy.springmvcboard.service;

import com.nhnacademy.springmvcboard.domain.User;
import com.nhnacademy.springmvcboard.request.LoginRequest;
import org.springframework.stereotype.Service;

@Service
public class LoginServlce {
    public boolean match(User user, LoginRequest loginRequest){
        return user.getUserId().equals(loginRequest.getUserId()) && user.getUserPassword().equals(loginRequest.getUserPassword());
    }
}
