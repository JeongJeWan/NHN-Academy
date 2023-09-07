package com.nhnacademy.todo.todolist.exception;

public class EventIdNotFoundException extends Exception{
    public EventIdNotFoundException(Long id){super("eventId:" + id);}
}
