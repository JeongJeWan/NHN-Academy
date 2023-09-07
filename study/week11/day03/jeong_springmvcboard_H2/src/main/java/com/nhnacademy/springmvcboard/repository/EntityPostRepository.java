package com.nhnacademy.springmvcboard.repository;


import com.nhnacademy.springmvcboard.domain.EntityPostDto;
import com.nhnacademy.springmvcboard.domain.EntityPostListDto;
import com.nhnacademy.springmvcboard.entity.EntityPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface EntityPostRepository extends JpaRepository<EntityPost, Long>, EntityPostRepositoryCustomer{

    // 해당 post 아이디에 대한 정보가져오기
    EntityPost findByPostId(Long postId);
    // 제목에 해당되는 정보 가져오기
    EntityPost findByTitle(String title);
    // 작성자로 게시물 목록 가져오
    List<EntityPost> findByUser_UserId(String userId);

    // JPQL 쿼리로 현재 date 이전에 등록된 게시물 가져오기
    @Query("select ep from EntityPost ep where ep.writeTime < ?1")
    List<EntityPost> getEntityPostsByWriteTime(LocalDateTime writeTime);
    // JPQL 쿼리로 모든 정보 가져오기
    @Query("select ep " +
            "from EntityPost ep " +
            "left outer join fetch ep.user eu")
    List<EntityPost> getAllEntityPostWithAssosiation();

    // QueryDsl
    @EntityGraph("entityPostWithEntityUser")
    List<EntityPost> readAllBy();

    Page<EntityPostDto> getAllBy(Pageable pageable);

}
