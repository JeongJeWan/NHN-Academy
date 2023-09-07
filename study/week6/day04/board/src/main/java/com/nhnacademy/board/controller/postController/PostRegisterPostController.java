package com.nhnacademy.board.controller.postController;

import com.nhnacademy.board.controller.Command;
import com.nhnacademy.board.post.ConcretePost;
import com.nhnacademy.board.post.Post;
import com.nhnacademy.board.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Slf4j
public class PostRegisterPostController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        PostRepository postRepository = (PostRepository) req.getServletContext().getAttribute("postRepository");

        String title = req.getParameter("title");
        if(Objects.isNull(title)) {
            throw new RuntimeException("제목이 존재하지 않습니다.");
        }

        String content = req.getParameter("content");
        if(Objects.isNull(content)) {
            throw new RuntimeException("본문이 존재하지 않습니다.");
        }

        HttpSession session = req.getSession();
        log.error("id:{}", session.getAttribute("id"));

        String writerUserId = (String) session.getAttribute("id");
        log.error("writerUserId:{}", writerUserId);
        if(Objects.isNull(writerUserId)) {
            throw new RuntimeException("사용자 아이디가 존재하지 않습니다.");
        }

        Post post = new ConcretePost();
        post.setTitle(title);
        post.setContent(content);
        post.setWriterUserId(writerUserId);
        log.error("writerUserId:{}", post.getWriterUserId());

        long id = postRepository.register(post);
        post.setId(id);


        log.error("post:{}", post);
        log.error("posts:{}", postRepository.getPosts());

        session.setAttribute("post", post);

        return "redirect:/postView.do?id=" + id;
    }
}
