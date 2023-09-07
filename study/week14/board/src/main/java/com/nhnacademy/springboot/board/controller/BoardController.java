package com.nhnacademy.springboot.board.controller;

import com.nhnacademy.springboot.board.domain.PostAll;
import com.nhnacademy.springboot.board.domain.PostFile;
import com.nhnacademy.springboot.board.domain.PostModifyRequest;
import com.nhnacademy.springboot.board.domain.PostRegisterRequest;
import com.nhnacademy.springboot.board.exception.InvalidPostAccessException;
import com.nhnacademy.springboot.board.exception.ValidationFailedException;
import com.nhnacademy.springboot.board.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {
    private final PostService postService;

    public BoardController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/register")
    public String registerPostForm() {
        return "board/registerForm";
    }

    @PostMapping("/register")
    public String registerPost(@Valid @ModelAttribute PostRegisterRequest request,
                               BindingResult bindingResult,
                               HttpServletRequest req) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        String userId = (String) req.getSession().getAttribute("sessionId");

        postService.createPost(userId, request.getTitle(), request.getContent(), request.getFiles());

        return "redirect:/board";
    }

    @GetMapping("/{postId}")
    public String showPostDetail(@PathVariable("postId") Long postId,
                                 HttpServletRequest req,
                                 Model model) {
        String userId = (String) req.getSession().getAttribute("sessionId");

        PostAll post = postService.getPostContainAll(postId, userId, false);

        model.addAttribute("like", post.getLike() > 0);
        model.addAttribute("post", post);

        return "board/view";
    }

    @GetMapping("/{postId}/modify")
    public String modifyPostForm(@PathVariable("postId") Long postId,
                                 Model model) {
        PostFile post = postService.getPostContainFiles(postId);

        model.addAttribute("post", post);

        return "board/modifyForm";
    }

    @PostMapping("/{postId}/modify")
    public String modifyPost(@PathVariable("postId") Long postId,
                             @Valid @ModelAttribute PostModifyRequest request,
                             BindingResult bindingResult,
                             HttpServletRequest req) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        String modifier = (String) req.getSession().getAttribute("sessionId");

        postService.modifyPost(postId,
                request.getTitle(),
                request.getContent(),
                modifier,
                request.getFiles(),
                request.getDeleteFiles());

        return "redirect:/board/" + postId;
    }


    @GetMapping("/{postId}/delete")
    public String deletePost(@PathVariable("postId") Long postId) {
        postService.deletePostById(postId);

        return "redirect:/board";
    }
}
