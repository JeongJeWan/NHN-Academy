package com.nhnacademy.jpa.controller.loginController;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Controller
public class LogoutController {

    private final RedisTemplate<String, Object> redisTemplate;

    public LogoutController(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("/logout")
    public RedirectView logout(@CookieValue("SESSION") String sessionId, HttpServletResponse response) {

        if(Objects.nonNull(sessionId)) {
            // "SESSION" 쿠키를 삭제
            Cookie cookie = new Cookie("SESSION", null);
            cookie.setMaxAge(0);

            response.addCookie(cookie);

            redisTemplate.opsForHash().delete(sessionId, "username", "authority");
        }

        return new RedirectView("/");
    }

}
