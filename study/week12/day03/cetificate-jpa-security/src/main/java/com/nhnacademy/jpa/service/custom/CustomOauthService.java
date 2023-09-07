package com.nhnacademy.jpa.service.custom;

import com.nhnacademy.jpa.entity.Resident;
import com.nhnacademy.jpa.repository.resident.ResidentRepository;
import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.*;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@PropertySource("classpath:github.properties")
public class CustomOauthService {

    private RestTemplate restTemplate;
    @Value("${client_id}")
    private String clientId;
    @Value("${client_secret}")
    private String clientSecret;
    @Value("${redirect_uri}")
    private String redirectUri;
    private static final String ACCESS_URL = "https://github.com/login/oauth/access_token";
    private static final String API_URL = "https://api.github.com/user";
    private final ResidentRepository residentRepository;
    private final RedisTemplate<String, String> redisTemplate;

    public CustomOauthService(ResidentRepository residentRepository, RedisTemplate<String, String> redisTemplate) {
        this.residentRepository = residentRepository;
        this.redisTemplate = redisTemplate;
    }

    public void processOauthLogin(String code, HttpServletRequest request) {
        restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("code", code);
        params.add("redirect_uri", redirectUri);

        HttpEntity<MultiValueMap<String, String>> oauthRequest = new HttpEntity<>(params, headers);
        ResponseEntity<AccessToken> response = restTemplate.exchange(ACCESS_URL, HttpMethod.POST, oauthRequest, AccessToken.class);

        String accessToken = response.getBody().getAccess_token();

        headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<String> userInfoEntity = new HttpEntity<>(headers);
        ResponseEntity<UserInfo> userInfo = restTemplate.exchange(API_URL, HttpMethod.GET, userInfoEntity, UserInfo.class);

        String email = userInfo.getBody().getEmail();
        Resident resident = residentRepository.getResidentByEmail(email);

        if (Objects.nonNull(resident)) {
            SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
            Authentication authentication =
                    new TestingAuthenticationToken(resident.getResidentSerialNumber()+"-"+resident.getName(),
                            resident.getPassword(), "ROLE_USER");
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

            Authentication authentication1 =
//                    new OAuth2AuthenticationToken(resident.getName(), resident.getPassword(), "ROLE_USER");
                    new UsernamePasswordAuthenticationToken(resident.getResidentSerialNumber()+"-"+resident.getName(),
                            resident.getPassword(), authorities);

            securityContext.setAuthentication(authentication1);
            SecurityContextHolder.setContext(securityContext);

            HttpSession session = request.getSession(true);

            redisTemplate.opsForHash().put(session.getId(), "oauth-username", resident.getId());
            redisTemplate.opsForHash().put(session.getId(), "oauth-authority", "ROLE_USER");

            session.setAttribute("username", resident.getName());
            session.setAttribute("authority", "ROLE_USER");
        }

    }

    @Getter
    @ToString
    static class AccessToken {
        private String access_token;
        private String scope;
        private String token_type;
    }

    @Getter
    @ToString
    static class UserInfo {
        private String login;
        private String id;
        private String name;
        private String email;
    }

}
