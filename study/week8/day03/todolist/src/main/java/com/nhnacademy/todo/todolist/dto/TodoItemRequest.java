package com.nhnacademy.todo.todolist.dto;

import lombok.Data;

@Data
public class TodoItemRequest {
    private String todoDate;
    private String todoSubject;
}
