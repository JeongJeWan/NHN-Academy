package com.nhnacademy.board.util;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import java.util.Optional;

@Slf4j
public final class LoginCounterUtils {
    private LoginCounterUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static void increaseCounter(ServletContext servletContext){
        Long loginCounter = Optional.ofNullable((Long)servletContext.getAttribute("loginCounter")).orElse(0l);
        loginCounter = loginCounter+1;
        servletContext.setAttribute("loginCounter",loginCounter);
        log.error("loginCounter+: {}", servletContext.getAttribute("loginCounter"));
    }
    public static void decreaseCounter(ServletContext servletContext){
        Long loginCounter = Optional.ofNullable((Long)servletContext.getAttribute("loginCounter")).orElse(0l);
        loginCounter = loginCounter-1;
        servletContext.setAttribute("loginCounter",loginCounter);
        log.error("loginCounter-: {}", servletContext.getAttribute("loginCounter"));
    }
}
