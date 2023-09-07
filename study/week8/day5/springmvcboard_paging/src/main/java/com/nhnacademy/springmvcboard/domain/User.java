package com.nhnacademy.springmvcboard.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@AllArgsConstructor
public class User {
    private String userId;
    private String userPassword;
    private String userName;
    private String profileFileName;
    private MultipartFile profileFile;


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

    public void update(String userPassword, String userName){
        this.userPassword = userPassword;
        this.userName = userName;
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

    public MultipartFile getProfileFile() {
        return profileFile;
    }

    public void setProfileFile(MultipartFile profileFile) {
        this.profileFile = profileFile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return userId != null ? userId.equals(user.userId) : user.userId == null;
    }

    @Override
    public int hashCode() {
        return userId != null ? userId.hashCode() : 0;
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
