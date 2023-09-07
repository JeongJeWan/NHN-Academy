package com.nhnacademy.springmvcboard.controller;

import com.nhnacademy.springmvcboard.domain.EntityUserDto;
import com.nhnacademy.springmvcboard.repository.EntityUserRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class PageUserController {
    private final EntityUserRepository entityUserRepository;

    public PageUserController(EntityUserRepository entityUserRepository) {
        this.entityUserRepository = entityUserRepository;
    }

    @GetMapping("/pages")
    public List<EntityUserDto> getPosts(Pageable pageable) {

        return entityUserRepository.getAllBy(pageable).getContent();
    }
}
