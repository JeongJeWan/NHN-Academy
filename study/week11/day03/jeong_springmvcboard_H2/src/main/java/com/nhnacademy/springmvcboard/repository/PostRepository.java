package com.nhnacademy.springmvcboard.repository;

import com.nhnacademy.springmvcboard.domain.PostDto;
import com.nhnacademy.springmvcboard.entity.EntityPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<EntityPost, Long> {
    Page<PostDto> getAllBy(Pageable pageable);
}
