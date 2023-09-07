package com.nhnacademy.board.controller.userController;

import com.nhnacademy.board.controller.Command;
import com.nhnacademy.board.repository.UserRepository;
import com.nhnacademy.board.user.User;
import com.nhnacademy.board.user.UserImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class UserUpdatePostController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        UserRepository userRepository = (UserRepository) req.getServletContext().getAttribute("userRepository");

        String id = req.getParameter("id");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String profileFileName = req.getParameter("profileFileName");

        if(Objects.isNull(id) || Objects.isNull(password) || Objects.isNull(name) || Objects.isNull(profileFileName)) {
            throw new RuntimeException("id, password, name, profileFileName 존재하지 않습니다.");
        }

        User user = new UserImpl(id, password, name, profileFileName);
        userRepository.modify(user);

        return "redirect:/userView.do?id=" + user.getId();

    }
}
