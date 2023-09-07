package com.nhnacademy.springmvcboard.Service;

import com.nhnacademy.springmvcboard.common.pagination.Page;
import com.nhnacademy.springmvcboard.domain.PostDto;
import com.nhnacademy.springmvcboard.domain.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserEventService {
    void register(User user);
    void modify(User user);
    void removeById(String id);
    User getUserById(String id);
    List<User> getUsers();
    Page<User> getUserList(int page, int size);
}
