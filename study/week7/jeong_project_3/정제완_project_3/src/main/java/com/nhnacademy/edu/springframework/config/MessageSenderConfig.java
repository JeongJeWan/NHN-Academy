package com.nhnacademy.edu.springframework.config;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = "com.nhnacademy.edu.springframework")
@PropertySource("classpath:url.properties")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class MessageSenderConfig {
}
