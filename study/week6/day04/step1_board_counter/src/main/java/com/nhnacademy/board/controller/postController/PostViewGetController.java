package com.nhnacademy.board.controller.postController;

import com.nhnacademy.board.controller.Command;
import com.nhnacademy.board.post.ConcretePost;
import com.nhnacademy.board.post.Post;
import com.nhnacademy.board.repository.PostRepository;
import com.nhnacademy.board.util.CurrentCounterUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Slf4j
public class PostViewGetController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {


        PostRepository postRepository = (PostRepository) req.getServletContext().getAttribute("postRepository");


        long id = Long.parseLong(req.getParameter("id"));
        log.error("id:{}", id);
        if(req.getParameter("id") == null) {
            throw new RuntimeException("게시물 아아디가 존재하지 않습니다.");
        }

        ConcretePost post = postRepository.getPost(id);
        log.error("Post:{}" , post);
        if(Objects.isNull(post)) {
            throw new RuntimeException("게시물을 찾을 수 없습니다.");
        }

        post.increaseViewCount();


        req.setAttribute("post", post);

        return "/post/postView.jsp";
    }
}
