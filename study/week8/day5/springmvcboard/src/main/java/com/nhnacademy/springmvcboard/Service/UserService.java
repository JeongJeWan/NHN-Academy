package com.nhnacademy.springmvcboard.Service;

import com.nhnacademy.springmvcboard.common.pagination.Page;
import com.nhnacademy.springmvcboard.domain.User;
import com.nhnacademy.springmvcboard.exception.DuplicateStudentIdException;
import com.nhnacademy.springmvcboard.exception.UserNotFoundException;
import com.nhnacademy.springmvcboard.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class UserService{

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(User user) {
        if (userRepository.existById(user.getUserId())) {
            throw new DuplicateStudentIdException(user.getUserId());
        }
        userRepository.add(user);
    }

    public void modify(User user) {
        userRepository.modify(user);
    }

    public void removeById(String id) {
        userRepository.remove(id);
    }

    public User getUserById(String id) {
        User user = userRepository.getUserById(id);
        if(Objects.isNull(user)) {
            throw new UserNotFoundException(id);
        }
        return userRepository.getUserById(id);
    }

    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    public Page<User> getUserList(int page, int size) {
        return userRepository.getPagedList(page, size);
    }
}
