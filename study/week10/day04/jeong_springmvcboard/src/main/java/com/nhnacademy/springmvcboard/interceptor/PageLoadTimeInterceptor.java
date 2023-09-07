package com.nhnacademy.springmvcboard.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.time.Instant;

@Slf4j
public class PageLoadTimeInterceptor implements HandlerInterceptor {
    private static final String START_TIME_ATTRIBUTE_NAME = "startTime";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Instant startTime = Instant.now();
        request.setAttribute(START_TIME_ATTRIBUTE_NAME, startTime);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        Instant startTime = (Instant) request.getAttribute(START_TIME_ATTRIBUTE_NAME);
        Duration duration = Duration.between(startTime, Instant.now());
        long loadTimeMillis = duration.toMillis();
        log.info("Page load time: {}ms", loadTimeMillis);

        if (modelAndView != null) {
            modelAndView.addObject("loadTimeMillis", loadTimeMillis);
        }
    }
}
