package com.nhnacademy.board.controller.postController;

import com.nhnacademy.board.controller.Command;
import com.nhnacademy.board.post.ConcretePost;

import com.nhnacademy.board.repository.PostRepository;
import com.nhnacademy.board.util.CurrentCounterUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
public class PostListGetController implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        CurrentCounterUtils.increaseCounter(req.getServletContext());

        PostRepository postRepository = (PostRepository) req.getServletContext().getAttribute("postRepository");
        List<ConcretePost> posts = postRepository.getPosts();
        log.error("posts:{}", posts);
        req.setAttribute("posts", posts);

        return "/post/postList.jsp";
    }
}
