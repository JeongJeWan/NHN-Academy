package com.nhnacademy.springboot.board.controller;

import com.nhnacademy.springboot.board.entity.Comment;
import com.nhnacademy.springboot.board.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{postId}")
    public String registerComment(@PathVariable("postId") long postId,
                                  @RequestParam String content,
                                  HttpSession session) {
        String writer = (String) session.getAttribute("sessionId");

        commentService.createComment(postId, writer, content);

        return "redirect:/board/" + postId;
    }

    @GetMapping("/{commentId}/modify")
    public String modifyComment(@PathVariable("commentId") long commentId,
                                Model model) {
        Comment comment = commentService.getComment(commentId);

        model.addAttribute("comment", comment);

        return "board/commentModifyForm";
    }

    @PostMapping("/{commentId}/modify")
    public String modifyComment(@PathVariable("commentId") long commentId,
                                @RequestParam("content") String content) {
        long postId = commentService.updateComment(commentId, content);

        return "redirect:/board/" + postId;
    }

    @GetMapping("/{commentId}/delete")
    public String deleteComment(@PathVariable("commentId") long commentId) {
        long postId = commentService.getComment(commentId).getPost().getId();

        commentService.deleteComment(commentId);

        return "redirect:/board/" + postId;
    }
}
