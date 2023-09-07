package com.nhnacademy.board.listener;

import com.nhnacademy.board.repository.JsonUserRepository;
import com.nhnacademy.board.repository.UserRepositoryImpl;
import com.nhnacademy.board.user.UserImpl;
import com.nhnacademy.board.repository.UserRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

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

        userRepository.add(adminUser);

        // ... application scope에서 studentRepository 객체에 접근할 수 있도록 구현하기
        context.setAttribute("userRepository", userRepository);
    }
}
