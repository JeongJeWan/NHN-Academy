package com.nhnacademy.student.service;

import com.nhnacademy.student.domain.User;
import com.nhnacademy.student.request.LoginRequest;
import org.springframework.stereotype.Service;

@Service
public class LoginServlce {
    public boolean match(User user, LoginRequest loginRequest){
        if(user.getUserId().equals(loginRequest.getUserId()) && user.getUserPassword().equals(loginRequest.getUserPassword())){
            return true;
        }else {
            return false;
        }
    }
}
