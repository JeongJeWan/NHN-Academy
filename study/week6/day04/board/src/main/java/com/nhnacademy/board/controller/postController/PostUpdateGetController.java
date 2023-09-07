package com.nhnacademy.board.controller.postController;

import com.nhnacademy.board.controller.Command;
import com.nhnacademy.board.post.Post;
import com.nhnacademy.board.repository.PostRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostUpdateGetController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        PostRepository postRepository = (PostRepository) req.getServletContext().getAttribute("postRepository");

        long id = Long.parseLong(req.getParameter("id"));
        if(req.getParameter("id") == null) {
            throw new RuntimeException("게시물 사용자가 존재하지 않습니다.");
        }

        Post post = postRepository.getPost(id);
        req.setAttribute("post", post);

        return "/post/postRegister.jsp";
    }
}
