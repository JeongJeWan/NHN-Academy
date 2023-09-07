package com.nhnacademy.nhnmart.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@WebServlet(name = "setCookieServlet", urlPatterns = "/change-lang")
public class SetCookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String locale = req.getParameter("locale");

        Cookie cookie = null;
        if(req.getParameter("lang").equals("ko")){
            cookie = new Cookie("locale","ko");
        } else if(req.getParameter("lang").equals("en")){

        }

        cookie.setMaxAge(-1);
        cookie.setPath("/");
        resp.addCookie(cookie);

        resp.sendRedirect("index.do");
    }
}
