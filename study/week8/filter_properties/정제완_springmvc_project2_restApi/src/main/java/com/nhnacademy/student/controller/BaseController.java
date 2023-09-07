package com.nhnacademy.student.controller;

import com.nhnacademy.student.domain.User;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

public interface BaseController {

    @ModelAttribute(name = "user")
    default User getUser(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if( Objects.isNull(session) ){
            return null;
        }
        return (User) session.getAttribute("user");
    }
}
