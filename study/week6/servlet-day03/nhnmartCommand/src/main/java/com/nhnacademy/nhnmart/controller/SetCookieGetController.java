package com.nhnacademy.nhnmart.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SetCookieGetController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        Cookie cookie = null;

        if(req.getParameter("lang").equals("ko")) {
            cookie = new Cookie("locale", "ko");
        } else if (req.getParameter("lang").equals("en")) {
            cookie = new Cookie("locale", "en");
        }

        cookie.setMaxAge(-1);
        cookie.setPath("/");
        resp.addCookie(cookie);

        return "redirect:/";
    }
}
