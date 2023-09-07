package com.nhnacademy.springmvcboard.Service.impl;

import com.nhnacademy.springmvcboard.Service.UserEventService;
import com.nhnacademy.springmvcboard.common.pagination.Page;
import com.nhnacademy.springmvcboard.domain.PostDto;
import com.nhnacademy.springmvcboard.domain.User;
import com.nhnacademy.springmvcboard.exception.DuplicateUserIdException;
import com.nhnacademy.springmvcboard.exception.UserNotFoundException;
import com.nhnacademy.springmvcboard.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Primary
@Service
@RequiredArgsConstructor
@Transactional
public class DbUserServiceImpl implements UserEventService {

    private final UserMapper userMapper;

    @Override
    public void register(User user) {
        if (userMapper.exitsById(user.getUserId())) {
            throw new DuplicateUserIdException(user.getUserId());
        }

        userMapper.register(user);
    }

    @Override
    public void modify(User user) {
        userMapper.modify(user);
    }

    @Override
    public void removeById(String id) {
        userMapper.delete(id);
    }

    @Override
    public User getUserById(String id) {
        User user = userMapper.getUserById(id);
        if(Objects.isNull(user)) {
            throw new UserNotFoundException(id);
        }
        return userMapper.getUserById(id);
    }

    @Override
    public List<User> getUsers() {
        return userMapper.getUsers();
    }

    @Override
    public Page<User> getUserList(int page, int size) {

        return new Page<User>() {
            @Override
            public int getPageNumber() {
                return page;
            }

            @Override
            public int getPageSize() {
                return size;
            }

            @Override
            public int getTotalPageCount() {
                return (int)Math.ceil( (getTotalCount()*1.0) /getPageSize());
            }

            @Override
            public long getTotalCount() {
                return userMapper.getUsers().size();
            }

            @Override
            public List<User> getList() {
                List<User> users = userMapper.getUsers().stream()
                        // .filter(o -> o.getRole().equals(User.Role.USER))
                        .sorted(Comparator.comparing(User::getCreatedAt).reversed())
                        .collect(Collectors.toList());

                int start = (getPageNumber()-1)*getPageSize();
                int end = start + getPageSize();

                if(end > getTotalCount()){
                    end = (int) getTotalCount();
                }


                return users.subList(start,end);
            }
        };
    }


    public String getProfileImagePath(String id){

        return null;
    }
}
