package com.nhnacademy.todo.todolist.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Event {
    private long id;
    private String subject;
    private String eventAt;
    @JsonFormat(pattern = "yy-MM-dd:HH-mm-ss")
    private LocalDateTime localDateTime;

    public Event(String subject, String eventAt) {
        this.subject = subject;
        this.eventAt = eventAt;
        this.localDateTime = LocalDateTime.now();
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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
}
