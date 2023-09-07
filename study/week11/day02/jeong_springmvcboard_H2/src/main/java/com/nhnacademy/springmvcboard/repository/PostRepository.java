package com.nhnacademy.springmvcboard.repository;


import com.nhnacademy.springmvcboard.entity.EntityPost;
import com.nhnacademy.springmvcboard.entity.EntityUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends JpaRepository<EntityPost, Long> {

    // 해당 post 아이디에 대한 정보가져오기
    EntityPost findByPostId(Long postId);
    // 제목에 해당되는 정보 가져오기
    EntityPost findByTitle(String title);
    // 작성자로 게시물 목록 가져오
    List<EntityPost> findByUser_UserId(String userId);
}
