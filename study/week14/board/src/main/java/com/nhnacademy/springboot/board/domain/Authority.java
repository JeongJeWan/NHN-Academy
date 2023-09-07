package com.nhnacademy.springboot.board.domain;

import lombok.Getter;

@Getter
public enum Authority {
    ADMIN(1),
    USER(2);

    private final int value;

    Authority(int value) {
        this.value = value;
    }
}
