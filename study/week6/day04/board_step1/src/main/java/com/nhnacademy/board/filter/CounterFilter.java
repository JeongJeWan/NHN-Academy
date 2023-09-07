package com.nhnacademy.board.filter;

import com.nhnacademy.board.util.LoginCounterUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "counterFilter", urlPatterns = "/*")
@Slf4j
public class CounterFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        LoginCounterUtils.increaseCounter(servletRequest.getServletContext());
        filterChain.doFilter(servletRequest,servletResponse);
        log.error("loginCounter:{}",servletRequest.getServletContext().getAttribute("loginCounter"));
        log.error("currentCounter:{}",servletRequest.getServletContext().getAttribute("currentCounter"));
    }
}