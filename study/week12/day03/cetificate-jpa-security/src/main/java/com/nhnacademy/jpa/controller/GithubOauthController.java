package com.nhnacademy.jpa.controller;

import com.nhnacademy.jpa.service.custom.CustomOauthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@PropertySource("classpath:github.properties")
public class GithubOauthController {

    private final CustomOauthService customOauthService;

    public GithubOauthController(CustomOauthService customOauthService) {
        this.customOauthService = customOauthService;
    }

    @GetMapping("/login/oauth2/code/github")
    public String getUserInfo(@RequestParam String code, HttpServletRequest request) {
        customOauthService.processOauthLogin(code, request);

        return "redirect:/";
    }
}
