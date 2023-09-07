package com.nhnacademy.board.controller;

import com.nhnacademy.board.util.CookieUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

public class LogoutGetController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(true);

        if(session.getAttribute("id") == null) {
            return "redirect:/loginForm.do";
        } else {
            session.invalidate();
        }


        Cookie cookie2 = CookieUtils.getCookie(req, "admin");
        if(!Objects.isNull(cookie2)) {
            cookie2.setValue("");
            cookie2.setMaxAge(0);
            resp.addCookie(cookie2);
        }

        String locale = "ko";
        Cookie cookie = new Cookie("locale",locale);
        resp.addCookie(cookie);

        return "redirect:/loginForm.do";
    }
}
