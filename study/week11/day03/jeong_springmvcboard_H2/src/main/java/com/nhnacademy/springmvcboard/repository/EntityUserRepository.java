package com.nhnacademy.springmvcboard.repository;

import com.nhnacademy.springmvcboard.domain.EntityUserDto;
import com.nhnacademy.springmvcboard.entity.EntityUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface EntityUserRepository extends JpaRepository<EntityUser, String>, EntityUserRepositoryCustomer{
    // 해당 학생 아이디로 정보 가져오기
    EntityUser findByUserId(String userId);

    // 학생에 이름으로 정보 가져오기
    EntityUser findByUserName(String userName);

    // EntityDto
    List<EntityUserDto> readByUserId(String userId);

    // JPQL 쿼리로 현재 date 이전에 등록된 사용자 가져오기
    @Query("select eu from EntityUser eu where eu.createdAt < ?1")
    List<EntityUser> getEntityUsersByCreatedAt(LocalDateTime createdAt);
    // JPQL 쿼리로 모든 정보 가져오기
    @Query("select eu " +
            "from EntityUser eu " +
            "left outer join fetch eu.post ep")
    List<EntityUser> getAllEntityUserWithAssosiation();

    // QueryDsl
    @EntityGraph("entityUserWithEntityPost")
    List<EntityUser> readAllBy();

    Page<EntityUserDto> getAllBy(Pageable pageable);

}
