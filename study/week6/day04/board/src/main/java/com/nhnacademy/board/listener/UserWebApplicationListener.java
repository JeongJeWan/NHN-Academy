package com.nhnacademy.board.listener;

import com.nhnacademy.board.user.UserImpl;
import com.nhnacademy.board.repository.UserRepository;
import com.nhnacademy.board.repository.UserRepositoryImpl;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@Slf4j
@WebListener
public class UserWebApplicationListener implements ServletContextListener{

    private static final String ADMIN_ID = "admin";
    private static final String ADMIN_PW = "12345";
    private static final String ADMIN_NAME = "관리자";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        UserRepository userRepository = new UserRepositoryImpl();

        UserImpl adminUser = new UserImpl(ADMIN_ID, ADMIN_PW, ADMIN_NAME);
        UserImpl user = new UserImpl("test1", "123", "사용자1", "프로필1");

        userRepository.add(adminUser);
        userRepository.add(user);
        log.error("adminUser:{}", userRepository.getUsers());

        // ... application scope에서 studentRepository 객체에 접근할 수 있도록 구현하기
        context.setAttribute("userRepository", userRepository);
    }
}
