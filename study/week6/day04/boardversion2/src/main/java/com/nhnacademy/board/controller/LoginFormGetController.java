package com.nhnacademy.board.controller;

import com.nhnacademy.board.util.CookieUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class LoginFormGetController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String locale = "ko";
        Cookie cookie = new Cookie("locale",locale);
        resp.addCookie(cookie);

        Cookie cookie2 = CookieUtils.getCookie(req, "admin");
        if(!Objects.isNull(cookie2)) {
            cookie2.setValue("");
            cookie2.setMaxAge(0);
            resp.addCookie(cookie2);
        }

        return "/user/loginForm.jsp";
    }
}
