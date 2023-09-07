package com.nhnacademy.board.controller.userController;

import com.nhnacademy.board.controller.Command;
import com.nhnacademy.board.repository.UserRepository;
import com.nhnacademy.board.user.User;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Slf4j
public class UserViewGetController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        UserRepository userRepository = (UserRepository) req.getServletContext().getAttribute("userRepository");

        String id = req.getParameter("id");
        if(Objects.isNull(id)) {
            throw new RuntimeException("아이디가 존재하지 않습니다.");
        }

        User user = userRepository.getUser(id);
        if(Objects.isNull(user)) {
            throw new RuntimeException("학생을 찾을 수 없습니다." +id);
        }

        log.error("user:{}", user);
        req.setAttribute("user", user);

        return "/user/userView.jsp";
    }
}
