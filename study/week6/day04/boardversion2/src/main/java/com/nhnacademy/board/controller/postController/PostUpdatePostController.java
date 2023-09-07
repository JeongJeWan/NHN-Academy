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
public class PostUpdatePostController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        PostRepository postRepository = (PostRepository) req.getServletContext().getAttribute("postRepository");

        log.error("title:{}", req.getParameter("title"));
        String title = req.getParameter("title");
        if(Objects.isNull(title)) {
            throw new RuntimeException("제목이 존재하지 않습니다.");
        }

        log.error("content:{}", req.getParameter("content"));
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

        long id = Long.parseLong(req.getParameter("postId"));
        if(req.getParameter("id") == null) {
            throw new RuntimeException("게시물 사용자가 존재하지 않습니다.");
        }

        Post post = postRepository.getPost(id);
        log.error("writerUserId:{}", post.getWriterUserId());
        ConcretePost concretePost = new ConcretePost(post.getId(), title, content, writerUserId);

        postRepository.modify(concretePost);


        return "redirect:/postView.do?id=" + post.getId();
    }
}
