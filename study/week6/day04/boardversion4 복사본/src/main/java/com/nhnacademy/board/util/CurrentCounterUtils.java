package com.nhnacademy.board.util;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import java.util.Optional;

@Slf4j
public final class CurrentCounterUtils {
    private CurrentCounterUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static void increaseCounter(ServletContext servletContext){
        Long currentCounter = Optional.ofNullable((Long)servletContext.getAttribute("currentCounter")).orElse(0l);
        currentCounter = currentCounter+1;
        servletContext.setAttribute("currentCounter",currentCounter);
        log.error("currentCounter+: {}", servletContext.getAttribute("currentCounter"));
    }
}
