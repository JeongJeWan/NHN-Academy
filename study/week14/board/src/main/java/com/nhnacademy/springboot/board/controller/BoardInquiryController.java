package com.nhnacademy.springboot.board.controller;

import com.nhnacademy.springboot.board.domain.PostComment;
import com.nhnacademy.springboot.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardInquiryController {
    private final PostService postService;
    private final int PAGE_SIZE = 20;



    @GetMapping
    public String inquiryPosts(@RequestParam("page") Optional<Integer> page,
                               @RequestParam("search") Optional<String> search,
                               @RequestParam("category") Optional<String> category, //빈칸(all), like
                               HttpSession session,
                               Model model) {
        int curPage = page.orElse(1);
        Pageable pageable = PageRequest.of(curPage - 1, PAGE_SIZE);

        String content = (category.isPresent()) ? null : (String) session.getAttribute("sessionId");
        Page<PostComment> posts = postService.getPostsWithComment(pageable, search, content);

        model.addAttribute("posts", posts);
        model.addAttribute("curPage", curPage);
        model.addAttribute("totalPage", posts.getTotalPages());

        return "board/list";
    }
}
