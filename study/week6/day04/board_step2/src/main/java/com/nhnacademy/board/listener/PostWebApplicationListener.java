package com.nhnacademy.board.listener;

import com.nhnacademy.board.repository.JsonPostRepository;
import com.nhnacademy.board.repository.PostRepository;
import com.nhnacademy.board.repository.PostRepositoryImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class PostWebApplicationListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ServletContext context = sce.getServletContext();
        PostRepository postRepository = new JsonPostRepository();

        // ... application scope에서 studentRepository 객체에 접근할 수 있도록 구현하기
        context.setAttribute("postRepository", postRepository);
    }
}
