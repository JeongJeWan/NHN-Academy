package com.nhnacademy.springbootopenapi.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter @Setter
@ConfigurationProperties(prefix = "account")
public class AccountProperties {
    private String url;
}
