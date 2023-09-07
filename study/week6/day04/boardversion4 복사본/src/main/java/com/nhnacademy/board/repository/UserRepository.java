package com.nhnacademy.board.repository;

import com.nhnacademy.board.user.User;

import java.util.List;

public interface UserRepository {
    void add(User user);
    void modify(User user);
    User remove(String id);

    User getUser(String id);
    List<User> getUsers();
}
