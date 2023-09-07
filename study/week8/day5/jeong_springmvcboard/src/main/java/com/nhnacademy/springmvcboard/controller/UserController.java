package com.nhnacademy.springmvcboard.controller;

import com.nhnacademy.springmvcboard.Service.UserService;
import com.nhnacademy.springmvcboard.common.pagination.Page;
import com.nhnacademy.springmvcboard.domain.User;
import com.nhnacademy.springmvcboard.exception.ValidationFailedException;
import com.nhnacademy.springmvcboard.request.UserRegisterRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController implements BaseController{

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list.do")
    public String list(Model model, @RequestParam(name = "page", defaultValue = "1") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
        Page<User> userPage= userService.getUserList(page, size);
//        List<User> userList = userService.getUsers();
        for(int i=0; i < userPage.getList().size(); i++) {
            log.info("user: {}", userPage.getList().get(i));
        }
//        model.addAttribute("userList", userList);
        model.addAttribute("userPage", userPage);
        return "user/userList";
    }

    @PostMapping("/register.do")
    public String register(@Valid UserRegisterRequest userRegisterRequest, BindingResult bindingResult) {
        if(bindingResult.hasFieldErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        User user = new User(userRegisterRequest.getUserId(), userRegisterRequest.getUserPassword(), userRegisterRequest.getUserName(), userRegisterRequest.getProfileFileName());
        userService.register(user);
        log.info("LocalDateTime>>>>>>>>>{}", user.getCreatedAt());
        log.info("studentRegisterRequest:{}",userRegisterRequest);
        log.info("save-student:{}", user);

        return "redirect:/user/list.do";
    }

    @GetMapping("/register.do")
    public String registerForm(Model model) {
        model.addAttribute(new User());
        return "user/userRegister";
    }

    @PostMapping("/delete.do")
    public String delete(HttpServletRequest req) {
        String userId = req.getParameter("userId");
        log.error("DELETE UserId:{}", userId);

        HttpSession session = req.getSession();
        User user = userService.getUserById(userId);

        if(user.getUserId().equals(session.getAttribute("userId")) || session.getAttribute("userId").equals("admin")) {
            userService.removeById(userId);
            return "redirect:/user/list.do";
        }

        return "redirect:/user/view.do?userId=" +userId;
    }

    @PostMapping("/update.do")
    public String update(@Valid UserRegisterRequest request, BindingResult bindingResult) {
        if(bindingResult.hasFieldErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        User user = new User(request.getUserId(), request.getUserPassword(), request.getUserName(), request.getProfileFileName());
        userService.modify(user);
        return "redirect:/user/view.do?userId=" + user.getUserId();
    }

    @GetMapping("/update.do")
    public String updateForm(Model model, @RequestParam(name = "page", defaultValue = "1") int page, @RequestParam(name = "size", defaultValue = "10")int size, HttpServletRequest req) {
        String userId = req.getParameter("userId");
        HttpSession session = req.getSession();

        User user = userService.getUserById(userId);
        model.addAttribute("user", user);
        model.addAttribute("page", page);
        model.addAttribute("size", size);

        if(user.getUserId().equals(session.getAttribute("userId")) || session.getAttribute("userId").equals("admin")) {
            return "user/userRegister";
        }

        return "redirect:/user/view.do?userId=" + userId;

    }

    @GetMapping("/view.do")
    public String view(Model model, @RequestParam(name = "page", defaultValue = "1") int page, @RequestParam(name = "size", defaultValue = "10")int size, String userId) {
        User user = userService.getUserById(userId);
        model.addAttribute("user", user);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        return "user/userView";
    }
}
