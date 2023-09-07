package com.nhnacademy.springmvcboard.mvcrepository;

import com.nhnacademy.springmvcboard.common.pagination.Page;
import com.nhnacademy.springmvcboard.domain.User;

import java.util.List;

public interface UserRepository {
    // 사용자 등록
    void add(User user);
    // 사용자 수정
    void modify(User user);
    // 사용자 삭제
    User remove(String id);
    // 사용자-조회_by id
    User getUserById(String id);
    // 사용자-리스트
    List<User> getUsers();

    int getTotalCount();
    Page<User> getPagedList(int page, int size);
    // 사용자 아이디 존재 여부
    boolean existById(String id);
}
