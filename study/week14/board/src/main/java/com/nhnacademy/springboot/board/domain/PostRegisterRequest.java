package com.nhnacademy.springboot.board.domain;

import lombok.Value;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Value
public class PostRegisterRequest {
    @NotBlank
    @Length(min = 2, max = 200)
    String title;

    @NotBlank
    @Length(max = 10000)
    String content;

    List<MultipartFile> files;
}
