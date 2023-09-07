package com.nhnacademy.springboot.board.service;

import com.nhnacademy.springboot.board.domain.UserLoginRequest;
import com.nhnacademy.springboot.board.entity.User;
import com.nhnacademy.springboot.board.exception.InvalidUserException;
import com.nhnacademy.springboot.board.exception.LoginFailedException;
import com.nhnacademy.springboot.board.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;


@Service
@Transactional
public class BoardUserService implements UserService {
    private final UserRepository userRepository;

    public BoardUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> getUser(String userId) {
        return userRepository.findById(userId);
    }

    @Override
    public User login(UserLoginRequest request) {
        User user = userRepository.findByUserId(request.getId())
                .orElseThrow(() -> new InvalidUserException(request.getId()));

        if(!matches(request, user)) {
            throw new LoginFailedException(request.getPassword());
        }
        return user;
    }

    private boolean matches(UserLoginRequest request, User user) {
        return Objects.equals(request.getPassword(), user.getPassword());
    }
}
