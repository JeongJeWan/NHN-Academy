package com.nhnacademy.springboot.board.controller;

import com.nhnacademy.springboot.board.service.LikeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/like")
public class LikeController {
    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }


    @GetMapping("/{postId}")
    public String likeProcess(@PathVariable("postId") long postId,
                              HttpSession session) {
        String userId = (String) session.getAttribute("sessionId");

        if ((likeService.existsLike(postId, userId))) {
            likeService.deleteLike(postId, userId);
        } else {
            likeService.createLike(postId, userId);
        }
        return "redirect:/board/" + postId;
    }
}
