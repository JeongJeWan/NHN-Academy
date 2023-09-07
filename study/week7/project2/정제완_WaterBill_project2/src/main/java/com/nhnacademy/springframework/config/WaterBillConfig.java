package com.nhnacademy.springframework.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "com.nhnacademy.springframework")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class WaterBillConfig {

}
