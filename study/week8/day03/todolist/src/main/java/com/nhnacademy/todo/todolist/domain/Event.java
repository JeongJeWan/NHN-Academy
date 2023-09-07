package com.nhnacademy.todo.todolist.domain;

import java.time.LocalDate;

public class Event {
    private long id;
    private String userId;
    private String subject;
    private LocalDate eventAt;

    public Event(String userId, String subject, LocalDate eventAt) {
        this.userId = userId;
        this.subject = subject;
        this.eventAt = eventAt;
    }

    public Event(long id, String userId, String subject, LocalDate eventAt) {
        this.id = id;
        this.userId = userId;
        this.subject = subject;
        this.eventAt = eventAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public LocalDate getEventAt() {
        return eventAt;
    }

    public void setEventAt(LocalDate eventAt) {
        this.eventAt = eventAt;
    }
}
