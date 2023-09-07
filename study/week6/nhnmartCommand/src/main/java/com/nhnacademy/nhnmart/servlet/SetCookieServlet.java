package com.nhnacademy.nhnmart.servlet;


import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet(name = "setCookieServlet", urlPatterns = "/change-lang")
public class SetCookieServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = null;


//        log.error("req.getParametet: {}", req.getParameter("lang"));
        if(req.getParameter("lang").equals("ko")) {
            log.error("req.getParametet: {}", req.getParameter("lang"));
            cookie = new Cookie("locale", "ko");
        } else if (req.getParameter("lang").equals("en")) {
            log.error("req.getParametet: {}", req.getParameter("lang"));
            cookie = new Cookie("locale", "en");
        }

        cookie.setMaxAge(-1);
        cookie.setPath("/");
        resp.addCookie(cookie);

        resp.sendRedirect("index.do");
    }
}
