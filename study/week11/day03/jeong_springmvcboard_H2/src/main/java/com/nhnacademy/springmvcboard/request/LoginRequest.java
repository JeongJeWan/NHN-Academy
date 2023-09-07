package com.nhnacademy.springmvcboard.request;

import lombok.Setter;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
    @Setter
    @NotBlank(message = "userId is empty")
    private String userId;
    @Setter
    @NotBlank(message ="userPassword  is empty!")
    private String userPassword;
    public String getUserId() {
        return userId;
    }
    public String getUserPassword() {
        return userPassword;
    }
}
