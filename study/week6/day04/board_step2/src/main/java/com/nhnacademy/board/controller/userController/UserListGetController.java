package com.nhnacademy.board.controller.userController;

import com.nhnacademy.board.controller.Command;
import com.nhnacademy.board.repository.UserRepository;
import com.nhnacademy.board.user.UserImpl;
import com.nhnacademy.board.util.CookieUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

public class UserListGetController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        UserRepository userRepository = (UserRepository) req.getServletContext().getAttribute("userRepository");
        List<UserImpl> users = userRepository.getUsers();
        Cookie cookie = CookieUtils.getCookie(req, "admin");
        if(Objects.nonNull(cookie)) {
            req.setAttribute("admin", "admin");
        }
        req.setAttribute("users", users);
        return "/user/userList.jsp";
    }
}
