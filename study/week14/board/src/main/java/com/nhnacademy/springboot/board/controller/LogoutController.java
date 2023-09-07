package com.nhnacademy.springboot.board.controller;

import com.nhnacademy.springboot.board.exception.LogoutFailedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.Objects;

@Controller
@RequestMapping("/logout")
public class LogoutController {

    @GetMapping
    public String logout(HttpSession session) {
        if (Objects.isNull(session.getAttribute("sessionId"))) {
            throw new LogoutFailedException();
        }
        for (Iterator<String> it = session.getAttributeNames().asIterator(); it.hasNext(); ) {
            String name = it.next();

            session.removeAttribute(name);
        }

        return "index";
    }
}
