package com.nhnacademy.springmvcboard.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nhnacademy.springmvcboard.domain.User;
import com.nhnacademy.springmvcboard.repository.MapPostRepository;
import com.nhnacademy.springmvcboard.repository.MapUserRepository;
import com.nhnacademy.springmvcboard.repository.PostRepository;
import com.nhnacademy.springmvcboard.repository.UserRepository;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackageClasses = {com.nhnacademy.springmvcboard.Base.class},excludeFilters = {@ComponentScan.Filter(Controller.class)})
public class RootConfig {

    @Bean
    public UserRepository mapUserRepository() {
        UserRepository userRepository = new MapUserRepository();

        for(int i=1; i<=100; i++) {
            String id = "user" + i;
            String password = "pwd" + i;
            String name = "아카데미" + i;
            String profileFileName = null;
            User user = new User(id, password, name, profileFileName);
            userRepository.add(user);
        }

//        User adminUser = new User("admin", "1234", "관리자");
//        userRepository.add(adminUser);
        return userRepository;
    }

    @Bean
    public User adminUser() {
        return new User("admin", "1234", "관리자");
    }

    @Bean
    public PostRepository mapPostRepository() {

        return new MapPostRepository();
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        //  pretty pring json
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        //  value -> null 무시, 데이터가 널이면 빼버림
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //  LocalDate, LocaDateTime suport jsr310
        objectMapper.registerModule(new JavaTimeModule());
        //  timestemp 출력을 disable -> 리스트 형태에서 문자열형태로 출력
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return objectMapper;
    }

    //  MessageSource bean 등록, 번들!!
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("message");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
