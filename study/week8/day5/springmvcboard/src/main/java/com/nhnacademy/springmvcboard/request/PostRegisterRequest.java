package com.nhnacademy.springmvcboard.request;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class PostRegisterRequest {

//    @NotBlank(message = "제목을 입력하세요")
    @Length(min = 1, max = 20, message = "제목은 최소 1자이상 20자 미만 입니다.")
    private String title;
//    @NotBlank(message = "내용을 입력하세요")
    @Length(min = 1, message = "내용은 최소 1자이상 이어야 합니다.")
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "PostRegisterRequest{" +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
