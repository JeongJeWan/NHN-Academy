package com.nhnacademy.edu.springframework.config;

import com.nhnacademy.edu.springframework.DoorayHookSender;
import com.nhnacademy.edu.springframework.DoorayMessageSender;
import com.nhnacademy.edu.springframework.aop.LoggingAspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;


@Configuration
@PropertySource("classpath:url.properties")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class MessageSenderConfig {
    @Bean
    public RestTemplate resttemplate() {
        return new RestTemplate();
    }

    @Value("${url}")
    private String url;

    @Bean
    public DoorayHookSender doorayHookSender() {
        return new DoorayHookSender(resttemplate(), url);
    }

    @Bean
    public DoorayMessageSender doorayMessageSender() {
        return new DoorayMessageSender(doorayHookSender());
    }

    @Bean
    public LoggingAspect loggingAspect() {
        return new LoggingAspect();
    }

}
