package com.nhnacademy.springmvcboard.repository;

import com.nhnacademy.springmvcboard.entity.EntityUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<EntityUser, String> {
    // 해당 학생 아이디로 정보 가져오기
    EntityUser findByUserId(String userId);

    // 학생에 이름으로 정보 가져오기
    EntityUser findByUserName(String userName);


}
