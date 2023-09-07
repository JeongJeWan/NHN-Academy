package com.nhnacademy.springmvcboard.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDateTime;


/*
CREATE TABLE `users` (
	`user_id` varchar(255) NOT NULL COMMENT '사용자 아이디' primary key,
    `user_password` varchar(255) NOT NULL COMMENT '사용자 비밀번호',
    `user_name` varchar(255) NOT NULL COMMENT '사용자 이름',
    `profile_file_name` varchar(255) NOT NULL COMMENT '사용자 프로필',
    `created_at` datetime NOT NULL default current_timestamp COMMENT '등록 날짜'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
 */
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id")
    private String userId;
    @Column(name = "user_password")
    private String userPassword;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "profile_file_name")
    private String profileFileName;
    @JsonFormat(pattern = "yyyy-MM-dd:HH-mm-ss")
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Transient
    private MultipartFile profileFile;



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
