package com.nhnacademy.todo.todolist.domain;


public class User {
    private long id;
    private String userId;
    private String subject;
    private String eventDt;

    public User(String userId, String subject, String eventDt) {
        this.userId = userId;
        this.subject = subject;
        this.eventDt = eventDt;
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

    public String getEventDt() {
        return eventDt;
    }

    public void setEventDt(String eventDt) {
        this.eventDt = eventDt;
    }
}
