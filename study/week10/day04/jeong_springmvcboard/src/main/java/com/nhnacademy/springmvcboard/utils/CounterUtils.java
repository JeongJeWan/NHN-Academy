package com.nhnacademy.springmvcboard.utils;

import lombok.extern.slf4j.Slf4j;
import javax.servlet.ServletContext;
import java.util.Optional;
@Slf4j
public final class CounterUtils {
    private CounterUtils() {
        throw new IllegalStateException("Utility class");
    }
    public static Long increaseCounter(ServletContext servletContext){
        Long counter = Optional.ofNullable((Long)servletContext.getAttribute("check_counter")).orElse(0l);
        counter = counter+1;
        servletContext.setAttribute("check_counter",counter);
        return counter;
    }
    public static Long visitIncreaseCounter(ServletContext servletContext){
        Long counter = Optional.ofNullable((Long)servletContext.getAttribute("visitorCounter")).orElse(0l);
        counter = counter+1;
        log.info(""+counter);
        servletContext.setAttribute("visitorCounter",counter);
        return counter;
    }
}
