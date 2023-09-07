package com.nhnacademy.board.controller;

import com.nhnacademy.board.user.User;
import com.nhnacademy.board.repository.UserRepository;
import com.nhnacademy.board.user.UserImpl;
import com.nhnacademy.board.util.LoginCounterUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
public class LoginPostController implements Command{
    private final String initParamId = "admin";
    private final String initParamPwd = "12345";
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        UserRepository userRepository = (UserRepository) req.getServletContext().getAttribute("userRepository");
        List<UserImpl> users = userRepository.getUsers();


        String id = req.getParameter("id");
        String password = req.getParameter("password");

        if(initParamId.equals(id) && initParamPwd.equals(password)) {

            Cookie cookie = new Cookie("admin","admin");
            cookie.setMaxAge(-1);
            cookie.setPath("/");
            resp.addCookie(cookie);
            HttpSession session = req.getSession();
            session.setAttribute("id", id);
            LoginCounterUtils.increaseCounter(req.getServletContext());

            return "redirect:/userList.do";
        }else {
            for(UserImpl user : users) {
                if(user.getId().equals(id) && user.getPassword().equals(password)) {
                    HttpSession session = req.getSession();
                    session.setAttribute("id", id);
                    // 로그인 조회수
                    LoginCounterUtils.increaseCounter(req.getServletContext());
                    return "redirect:/postList.do";
                }
            }
        }
        return "redirect:/loginForm.do";
    }
}
