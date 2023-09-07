package com.nhnacademy.springboot.board.domain;

import lombok.Value;

import javax.validation.constraints.NotBlank;


@Value
public class UserLoginRequest {
    int authority;
    @NotBlank
    String id;
    @NotBlank
    String password;
}
