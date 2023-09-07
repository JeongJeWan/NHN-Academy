package com.nhnacademy.springboot.board.controller;

import com.nhnacademy.springboot.board.domain.UserLoginRequest;
import com.nhnacademy.springboot.board.entity.User;
import com.nhnacademy.springboot.board.exception.ValidationFailedException;
import com.nhnacademy.springboot.board.service.UserService;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String loginForm() {
        return "loginForm";
    }

    @PostMapping
    public String login(@Valid @ModelAttribute UserLoginRequest request,
                        BindingResult bindingResult,
                        HttpServletRequest req) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        User user = userService.login(request);
        HttpSession session = req.getSession();
        session.setAttribute("sessionId", user.getUserId());
        session.setAttribute("name", user.getName());
        session.setAttribute("authority", user.getAuthority());

        return "index";
    }
}
