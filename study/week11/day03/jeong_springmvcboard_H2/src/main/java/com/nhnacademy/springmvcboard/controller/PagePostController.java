package com.nhnacademy.springmvcboard.controller;

import com.nhnacademy.springmvcboard.domain.EntityPostDto;
import com.nhnacademy.springmvcboard.domain.EntityPostListDto;
import com.nhnacademy.springmvcboard.repository.EntityPostRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PagePostController {
    private final EntityPostRepository entityPostRepository;

    public PagePostController(EntityPostRepository entityPostRepository) {
        this.entityPostRepository = entityPostRepository;
    }

    @GetMapping("/pages")
    public List<EntityPostDto> getPosts(Pageable pageable) {

        return entityPostRepository.getAllBy(pageable).getContent();
    }
}
