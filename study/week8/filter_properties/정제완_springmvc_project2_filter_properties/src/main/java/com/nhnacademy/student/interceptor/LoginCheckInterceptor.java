package com.nhnacademy.student.interceptor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
public class LoginCheckInterceptor implements HandlerInterceptor {

    private final Set<String> excludeUrls = new HashSet<>();

    private void init(){
        if(excludeUrls.isEmpty()) {
            excludeUrls.add("/login");
            excludeUrls.add("/login/");
            excludeUrls.add("/login/logout");
            excludeUrls.add("/resources/");
            excludeUrls.add("/favicon.ico");
        }
    }

    private boolean urlCheck(String path){
        for (String excludeUrl : excludeUrls) {
            if(path.contains(excludeUrl)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        init();
        String path = request.getRequestURI();
        log.info("login-check-filter-path:{}",path);

        if(urlCheck(path)){
            HttpSession session = request.getSession(false);
            if(Objects.isNull(session) || Objects.isNull(session.getAttribute("user")) ){
                response.sendRedirect("/login/");
                return false;
            }
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
