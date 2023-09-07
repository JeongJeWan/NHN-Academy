package com.nhnacademy.springmvcboard.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nhnacademy.springmvcboard.domain.User;
import com.nhnacademy.springmvcboard.mvcrepository.MapPostRepository;
import com.nhnacademy.springmvcboard.mvcrepository.MapUserRepository;
import com.nhnacademy.springmvcboard.mvcrepository.PostRepository;
import com.nhnacademy.springmvcboard.mvcrepository.UserRepository;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackageClasses = {com.nhnacademy.springmvcboard.Base.class},excludeFilters = {@ComponentScan.Filter(Controller.class)})
public class RootConfig {

    @Bean
    public UserRepository mapUserRepository() {
//        UserRepository userRepository = new MapUserRepository();

//        for(int i=1; i<=100; i++) {
//            String id = "user" + i;
//            String password = "pwd" + i;
//            String name = "아카데미" + i;
//            String profileFileName = null;
//            User user = new User(id, password, name, profileFileName);
//            userRepository.add(user);
//        }

//        User adminUser = new User("admin", "1234", "관리자");
//        userRepository.add(adminUser);
        return new MapUserRepository();
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

    @Bean(name="xmlMapper")
    public XmlMapper xmlMapper(){
        XmlMapper xmlMapper = new XmlMapper();
        //pretty print xml
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
        //value -> null 무시
        xmlMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //LocalDate, LocalDateTime support jsr310
        xmlMapper.registerModule(new JavaTimeModule());
        //timestamp 출력을 disable. -> 문자열형태로 출력
        xmlMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return xmlMapper;
    }

    //  MessageSource bean 등록, 번들!!
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("message");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:~/spring-jpa;DATABASE_TO_UPPER=false;MODE=LEGACY;"
                + "INIT=RUNSCRIPT FROM 'classpath:/script/schema.sql'");
        dataSource.setUsername("sa");
        dataSource.setPassword("");

        dataSource.setInitialSize(10);
        dataSource.setMaxTotal(10);
        dataSource.setMinIdle(10);
        dataSource.setMaxIdle(10);

        dataSource.setMaxWaitMillis(1000);

        dataSource.setTestOnBorrow(true);
        dataSource.setTestOnReturn(true);
        dataSource.setTestWhileIdle(true);

        return dataSource;
    }

}
