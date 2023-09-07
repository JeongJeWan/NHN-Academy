package com.nhnacademy.student.controller;


import com.nhnacademy.student.repository.User;
import com.nhnacademy.student.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class LoginController {
    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String login(@CookieValue(name = "SESSION", required = false)String sessionId ) {
        if(StringUtils.hasText(sessionId)){
            return "redirect:/student/list.do";
        }
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String doLogin(String userId, String userPassword , Model model, HttpServletRequest request, HttpServletResponse response, RedirectAttributes red) {

        if( userRepository.matches(userId,userPassword) ){
            //로그인 성공
            HttpSession session = request.getSession(true);
            Cookie cookie = new Cookie("SESSION", session.getId());
            response.addCookie(cookie);

            User user = userRepository.getUser(userId);
            session.setAttribute("user",user);
            return "redirect:/student/list.do";
        }else{
            //로그인 실폐
            log.info("id :{}",userId);
            log.info("password :{}",userPassword);
            if(userId.equals("")){
                model.addAttribute("id",true);
            }
            if(userPassword.equals("")){
                model.addAttribute("password",true);
            }
            if(!userPassword.equals("") && !userId.equals("")){
                model.addAttribute("message",true);
            }
            return "login/loginForm";
        }
    }

}
