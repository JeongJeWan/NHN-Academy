package com.nhnacademy.todo.todolist.dto;


import java.time.LocalDate;

public class DailyRegisterCountResponseDto {
    private final long count;

    public DailyRegisterCountResponseDto(long count) {
        this.count = count;
    }

    public long getCount() {
        return count;
    }

}
