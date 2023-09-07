package com.nhnacademy.springmvcboard.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class User {
    private String userId;
    private String userPassword;
    private String userName;
    private String profileFileName;
    private boolean isAdmin;

    @JsonFormat(pattern = "yyyy-MM-dd:HH-mm-ss")
    private LocalDateTime createdAt;

    public User() {}

    public User(String userId, String userPassword, String userName) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
        this.createdAt = LocalDateTime.now();
    }

    public User(String userId, String userPassword, String userName, String profileFileName) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
        this.profileFileName = profileFileName;
        this.createdAt = LocalDateTime.now();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProfileFileName() {
        return profileFileName;
    }

    public void setProfileFileName(String profileFileName) {
        this.profileFileName = profileFileName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isAdmin() {
        return getUserId().equals("admin");
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userName='" + userName + '\'' +
                ", profileFileName='" + profileFileName + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
