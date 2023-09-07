package com.nhnacademy.board.controller.postController;

import com.nhnacademy.board.controller.Command;
import com.nhnacademy.board.post.Post;
import com.nhnacademy.board.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Slf4j
public class PostDeletePostController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        PostRepository postRepository = (PostRepository) req.getServletContext().getAttribute("postRepository");

        long id = Long.parseLong(req.getParameter("id"));
        log.error("DELETE id:{}",id);
        if(req.getParameter("id") == null) {
            log.error("게시물 아이디가 존재하지 않습니다.");
        }

        HttpSession session = req.getSession();
        log.error("DELETE SESSION id:{}",session.getAttribute("id"));


        Post post = postRepository.getPost(id);

        if(post.getWriterUserId().equals(session.getAttribute("id")) || session.getAttribute("id").equals("admin")) {
            postRepository.remove(id);
            return "redirect:/postList.do";
        }

        return "redirect:/postView.do?id=" + id;
    }
}
