package com.nhnacademy.jpa.auth;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final RedisTemplate<String, Object> redisTemplate;

    public LoginSuccessHandler(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication)
        throws IOException, ServletException {

        String sessionId = "session" + UUID.randomUUID().toString();

        // "SESSION" 이라는 이름의 쿠키에 sessionId 를 저장
        Cookie cookie = new Cookie("SESSION", sessionId);
        cookie.setMaxAge(259200);

        response.addCookie(cookie);

        String username = authentication.getName();
        String authority = new ArrayList<>(authentication.getAuthorities()).get(0).getAuthority();

        redisTemplate.opsForHash().put(sessionId, "username", username);
        redisTemplate.opsForHash().put(sessionId, "authority", authority);

        super.onAuthenticationSuccess(request, response, authentication);
    }

}
