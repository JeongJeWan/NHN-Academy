package com.nhnacademy.student.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Slf4j
public class LoginCheckFilter implements Filter {
    private final Set<String> excludeUrls = new HashSet<>();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        excludeUrls.add("/login");
        excludeUrls.add("/login/");
        excludeUrls.add("/login/logout");
        excludeUrls.add("/resources/");
        excludeUrls.add("/favicon.ico");
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
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String path = request.getRequestURI();
        log.info("login-check-filter-path:{}",path);

        if(urlCheck(path)){
            HttpSession session = request.getSession(false);
            if(Objects.isNull(session) || Objects.isNull(session.getAttribute("user")) ){
                response.sendRedirect("/login/");
                return;
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
