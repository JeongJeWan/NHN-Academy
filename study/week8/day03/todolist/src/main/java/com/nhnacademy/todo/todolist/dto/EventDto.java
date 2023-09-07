package com.nhnacademy.todo.todolist.dto;

import lombok.Data;

@Data
public class EventDto {
    private String subject;
    private String eventAt;

    public EventDto(){}
    public EventDto(String subject, String eventAt) {
        this.subject = subject;
        this.eventAt = eventAt;
    }
}
