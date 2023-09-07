package com.nhnacademy.springmvcboard.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequest {

    @NotBlank(message = "아이디를 입력해주세요")
    private String userId;
    @NotBlank(message = "비밀번호를 입력해주세요")
    private String userPassword;
    @NotBlank(message = "이름을 입력해주세요")
    private String userName;
    private String profileFileName;
    private String profileFile;

    public UserRegisterRequest(String userId, String userPassword, String userName, String profileFileName) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.profileFileName = profileFileName;
    }
}
