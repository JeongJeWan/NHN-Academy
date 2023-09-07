package com.nhnacademy.springboot.board.controller;

import com.nhnacademy.springboot.board.domain.PostAll;
import com.nhnacademy.springboot.board.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/board")
public class AdminController {
    private final PostService postService;

    public AdminController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{postId}")
    public String showPostDetail(@PathVariable("postId") Long postId,
                                 HttpSession session,
                                 Model model) {
        String userId = (String) session.getAttribute("sessionId");

        PostAll post = postService.getPostContainAll(postId, userId, true);

        model.addAttribute("like", post.getLike() > 0);
        model.addAttribute("post", post);

        return "board/view";
    }

    @GetMapping("/{postId}/recover")
    public String recoverPost(@PathVariable("postId") Long postId) {
        postService.recoverPostById(postId);

        return "redirect:/board";
    }

    @GetMapping("/{postId}/delete")
    public String deletePostPermanently(@PathVariable("postId") Long postId) {
        postService.deletePostPermanentlyById(postId);

        return "redirect:/board";
    }
}
