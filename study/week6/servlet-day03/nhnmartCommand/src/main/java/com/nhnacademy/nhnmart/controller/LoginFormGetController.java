package com.nhnacademy.nhnmart.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFormGetController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String locale = "ko";
        Cookie cookie = new Cookie("locale",locale);
        resp.addCookie(cookie);

        return "/views/loginForm.jsp";
    }
}
