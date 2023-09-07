package com.nhnacademy.todo.todolist.domain;

public class EventDto {
    private String subject;
    private String eventAt;

    public EventDto(){}

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEventAt() {
        return eventAt;
    }

    public void setEventAt(String eventAt) {
        this.eventAt = eventAt;
    }

    public EventDto(String subject, String eventAt) {
        this.subject = subject;
        this.eventAt = eventAt;
    }
}
