package com.nhnacademy.springmvcboard.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:/db.properties")
public class DatabaseProperties {
    @Getter
    @Setter
    @Value("${db.username}")
    private String username;
    @Getter
    @Setter
    @Value("${db.password}")
    private String password;
    @Getter
    @Setter
    @Value("${db.driverClassName}")
    private String driverClassName;
    @Getter
    @Setter
    @Value("${db.url}")
    private String url;
    @Getter
    @Setter
    @Value("${db.initialSize}")
    private Integer initialSize;
    @Getter
    @Setter
    @Value("${db.maxTotal}")
    private Integer maxTotal;
    @Getter
    @Setter
    @Value("${db.maxIdle}")
    private Integer maxIdle;
    @Getter
    @Setter
    @Value("${db.minIdle}")
    private Integer minIdle;
    @Getter
    @Setter
    @Value("${db.maxWaitMillis}")
    private Integer maxWaitMillis;
    @Getter
    @Setter
    @Value("${db.validationQuery}")
    private String validationQuery;
    @Getter
    @Setter
    @Value("${db.testOnBorrow}")
    private boolean testOnBorrow;
}
