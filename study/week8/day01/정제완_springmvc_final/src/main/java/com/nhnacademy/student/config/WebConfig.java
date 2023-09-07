package com.nhnacademy.student.config;

import com.nhnacademy.student.controller.ControllerBase;
import com.nhnacademy.student.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@EnableWebMvc
@Configuration
@ComponentScan(basePackageClasses = ControllerBase.class)
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/view/", ".jsp");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/","/student/list.do");
        //todo#20 - /favicon.ico 호출시 -> /resources/favicon.ico 로 연결.. 404 error 발생 처리
//        registry.addRedirectViewController("/favicon.ico","/resources/favicon.ico");
    }

    @Bean
    public User user() {
        return new User("admin", "관리자", "1234");
    }
}
