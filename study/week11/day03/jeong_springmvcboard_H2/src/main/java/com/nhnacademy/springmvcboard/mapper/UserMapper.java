package com.nhnacademy.springmvcboard.mapper;

import com.nhnacademy.springmvcboard.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    // 사용자 등록
    void register(User user);

    // 사용자 수정
    void modify(User user);

    // 사용자 삭제
    void delete(String id);

    // 사용자 아이디 조회
    User getUserById(String id);

    // 사용자 중복 아이디 확인
    boolean exitsById(String id);

    // 사용자 리스트
    List<User> getUsers();
}
