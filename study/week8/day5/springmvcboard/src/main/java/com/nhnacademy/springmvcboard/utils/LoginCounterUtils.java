package com.nhnacademy.springmvcboard.utils;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import java.util.Optional;

@Slf4j
public final class LoginCounterUtils {
    private LoginCounterUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static long increaseCounter(ServletContext servletContext){
        Long loginCounter = Optional.ofNullable((Long)servletContext.getAttribute("loginCounter")).orElse(0l);
        loginCounter = loginCounter+1;
        servletContext.setAttribute("loginCounter",loginCounter);
        log.error("loginCounter+: {}", servletContext.getAttribute("loginCounter"));
        return loginCounter;
    }
}
