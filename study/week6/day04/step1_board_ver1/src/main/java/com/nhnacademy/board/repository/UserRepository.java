package com.nhnacademy.board.repository;

import com.nhnacademy.board.user.UserImpl;

import java.util.List;

public interface UserRepository {
    void add(UserImpl user);
    void modify(UserImpl user);
    UserImpl remove(String id);

    UserImpl getUser(String id);
    List<UserImpl> getUsers();
}
