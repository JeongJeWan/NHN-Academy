package com.nhnacademy.springboot.board.domain;

import com.nhnacademy.springboot.board.entity.File;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PostFile {
    private final Long id;
    private final String title;
    private final String content;
    private final List<File> files;
}
