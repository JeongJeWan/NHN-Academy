package com.nhnacademy.board.controller.userController;

import com.nhnacademy.board.controller.Command;
import com.nhnacademy.board.repository.UserRepository;
import com.nhnacademy.board.user.UserImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class UserRegisterPostController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        UserRepository userRepository = (UserRepository) req.getServletContext().getAttribute("userRepository");

        String id = req.getParameter("id");
        if(Objects.isNull(id)) {
            throw new RuntimeException("아이디가 존재하지 않습니다,");
        }
        String password = req.getParameter("password");
        if(Objects.isNull(password)) {
            throw new RuntimeException("비밀번호가 존재하지 않습니다.");
        }
        String name = req.getParameter("name");
        if(Objects.isNull(name)) {
            throw new RuntimeException("이름이 존재하지 않습니다.");
        }

        String profileFileName = req.getParameter("profileFileName");
        if(Objects.isNull(profileFileName)) {
            throw new RuntimeException("프로필파일이름이 존재하지 않습니다,");
        }


        UserImpl user = new UserImpl(id, password, name, profileFileName);
        userRepository.add(user);

        return "redirect:/userView.do?id=" + user.getId();
    }
}
