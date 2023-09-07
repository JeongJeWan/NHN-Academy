package com.nhnacademy.board.controller.postController;

import com.nhnacademy.board.controller.Command;
import com.nhnacademy.board.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class PostDeletePostController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        PostRepository postRepository = (PostRepository) req.getServletContext().getAttribute("postRepository");

        long id = Long.parseLong(req.getParameter("id"));
        log.error("id:{}",id);
        if(req.getParameter("id") == null) {
            log.error("게시물 아이디가 존재하지 않습니다.");
        }
        HttpSession session = req.getSession();
        log.error("id:{}",session.getAttribute("id"));

        postRepository.remove(id);

        return "redirect:/postList.do";
    }
}
