package com.nhnacademy.edu.springframework;

import com.nhn.dooray.client.DoorayHook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DoorayHookSender {

    private final RestTemplate resttemplate;
    @Value("${url}")
    private String url;


    public DoorayHookSender() {
        this.resttemplate = new RestTemplate();
    }

    public boolean send(DoorayHook doorayHook) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<DoorayHook> entity = new HttpEntity<>(doorayHook, headers);
        ResponseEntity<String> exchange = this.resttemplate.exchange(this.url, HttpMethod.POST, entity, String.class, new Object[0]);
        return true;
    }
}
