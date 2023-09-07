package com.nhnacademy.nhnmart.controller;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginPostController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        final String initParamId = "admin";
        final String initPatamPwd = "1234";

        String id = req.getParameter("id");
        String pwd = req.getParameter("pwd");

        if(initParamId.equals(id) && initPatamPwd.equals(pwd)) {
            HttpSession session = req.getSession();
            session.setAttribute("id", id);
            return "redirect:/login.do";
        } else {
            log.error("아이디/패스워드가 일치하지 않습니다.");
            return "redirect:/loginForm.do";
        }
    }
}
