package com.nhnacademy.springmvcboard.controller;

import com.nhnacademy.springmvcboard.service.LoginServlce;
import com.nhnacademy.springmvcboard.domain.User;
import com.nhnacademy.springmvcboard.repository.UserRepository;
import com.nhnacademy.springmvcboard.request.LoginRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController implements BaseController{

    private final UserRepository userRepository;
    private final LoginServlce loginServlce;
    private final User adminUser;

    public LoginController(UserRepository userRepository, LoginServlce loginServlce,User adminUser) {
        this.userRepository = userRepository;
        this.loginServlce = loginServlce;
        this.adminUser = adminUser;
    }

    @GetMapping(value = {"/",""})
    public String loginForm(Model model, User user, HttpServletResponse response){
        if(Objects.nonNull(user)){
            return "redirect:/";
        }
        log.info("message:{}", model.getAttribute("message"));
        model.addAttribute("loginRequest",new LoginRequest());
        return "/login/loginForm";
    }

    @PostMapping(value = {"/",""})
    public String doLogin(Model model, @Valid LoginRequest loginRequest, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectAttributes){
        log.info("userId:{}", loginRequest.getUserId());
        log.info("userPassword:{}", loginRequest.getUserPassword());
        log.info("UserRepositort: {}", userRepository.getUsers());
        List<User> userList = userRepository.getUsers();

        if(bindingResult.hasFieldErrors()){
            model.addAttribute("loginRequest", loginRequest);
            return "login/loginForm";
        }

        if(loginServlce.match(adminUser, loginRequest)) {
            HttpSession session = request.getSession();
            session.setAttribute("admin", adminUser.getUserId());
            session.setAttribute("userId", adminUser.getUserId());
            session.setAttribute("user", adminUser);
            return "redirect:/user/list.do";
        } else {
            for(User user : userList) {
                if(loginServlce.match(user, loginRequest)) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("userId", user.getUserId());
                    session.setAttribute("user", user);
                    return "redirect:/post/list.do";
                }
            }
        }

        redirectAttributes.addFlashAttribute("message", "로그인 실패");
        return "redirect:/login/";
    }

    @PostMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession(false);
        if(Objects.nonNull(session)){
            session.invalidate();
            Cookie cookie = new Cookie("JSESSIONID","");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
        return "redirect:/login/";
    }
}
