package com.nhnacademy.board.controller.postController;

import com.nhnacademy.board.controller.Command;
import com.nhnacademy.board.post.Post;
import com.nhnacademy.board.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class PostUpdateGetController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        PostRepository postRepository = (PostRepository) req.getServletContext().getAttribute("postRepository");

        long id = Long.parseLong(req.getParameter("id"));
        if(req.getParameter("id") == null) {
            throw new RuntimeException("게시물 사용자가 존재하지 않습니다.");
        }

        HttpSession session = req.getSession();
        log.error("id:{}", session.getAttribute("id"));

        Post post = postRepository.getPost(id);
        req.setAttribute("post", post);

        if(post.getWriterUserId().equals(session.getAttribute("id")) || session.getAttribute("id").equals("admin")) {

            return "/post/postRegister.jsp";
        }

        return "redirect:/postView.do?id=" + id;

    }
}
