package com.nhnacademy.todo.todolist.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nhnacademy.todo.todolist.Base;
import com.nhnacademy.todo.todolist.repository.EventRepository;
import com.nhnacademy.todo.todolist.repository.impl.EventRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackageClasses = Base.class, excludeFilters = {@ComponentScan.Filter(Controller.class)})
public class RootConfig {

//    @Bean("memoryRepository")
//    public MemoryRepository memoryRepository() {
//        MemoryRepository memoryRepository = new MemoryRepository();
//        memoryRepository.save("2023-04-01", new Event("asb", "2023-04-01"));
//        return memoryRepository;
//    }

    @Bean("eventRepository")
    public EventRepository eventRepository() {

        return new EventRepositoryImpl();
    }

//    @Bean("eventRepository")
//    public EventRepository eventRepository() {
//        EventRepository eventRepository = new EventRepositoryImpl();
//        eventRepository.save(new EventFF(1, "1", ))
//    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        //  pretty pring json
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        // value -> null 무시, 데이터가 널이면 빼버림
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // LocatDate, LocalDateTime suport jsr310
        objectMapper.registerModule(new JavaTimeModule());
        // timestamp 출력을 disable -> 리스트 형태에서 문자열형태로 출력
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return objectMapper;
    }
}
