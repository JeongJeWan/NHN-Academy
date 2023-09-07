package com.nhnacademy.board.controller.userController;

import com.nhnacademy.board.controller.Command;
import com.nhnacademy.board.repository.UserRepository;
import com.nhnacademy.board.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class UserUpdateGetController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        UserRepository userRepository = (UserRepository) req.getServletContext().getAttribute("userRepository");
        String id = req.getParameter("id");
        User user = userRepository.getUser(id);
        if(Objects.isNull(user)) {
            throw new RuntimeException("User not fount: " + id);
        }
        req.setAttribute("user", user);
        return "/user/userRegister.jsp";
    }
}
