package com.nhnacademy.board.controller.userController;

import com.nhnacademy.board.controller.Command;
import com.nhnacademy.board.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Slf4j
public class UserDeletePostController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        UserRepository userRepository = (UserRepository) req.getServletContext().getAttribute("userRepository");

        String id = req.getParameter("id");
        if(Objects.isNull(id)) {
            log.error("사용자가 존재하지 않습니다.");
        }
        HttpSession session = req.getSession();
        log.error("id:{}",session.getAttribute("id"));

        userRepository.remove(id);
        return "redirect:/userList.do";
    }
}
