package com.nhnacademy.springmvcboard.controller;

import com.nhnacademy.springmvcboard.Service.PostService;
import com.nhnacademy.springmvcboard.common.pagination.Page;
import com.nhnacademy.springmvcboard.domain.Post;
import com.nhnacademy.springmvcboard.exception.ValidationFailedException;
import com.nhnacademy.springmvcboard.request.PostRegisterRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/post")
public class PostController implements BaseController{

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/list.do")
    public String list(Model model, @RequestParam(name = "page", defaultValue = "1") int page, @RequestParam(name = "size", defaultValue = "10")int size) {
//        List<Post> postList = postService.getPosts();
        Page<Post> postPage = postService.getPostList(page, size);
        for(int i=0; i < postPage.getList().size(); i++) {
            log.info("post: {}", postPage.getList().get(i));
        }
//        model.addAttribute("postList", postList);
        model.addAttribute("postPage", postPage);
        return "post/postList";
    }

    @PostMapping("/register.do")
    public String register(@Valid PostRegisterRequest postRegisterRequest, BindingResult bindingResult, HttpServletRequest request) {
        if(bindingResult.hasFieldErrors()) {
            throw new ValidationFailedException(bindingResult);
        }



        HttpSession session = request.getSession();
        log.info("ID <<<<<>>>>>> {}", session.getAttribute("userId"));

        String writerUserId = (String) session.getAttribute("userId");

        Post post = new Post();
        post.setWriterUserId(writerUserId);
        post.setTitle(postRegisterRequest.getTitle());
        post.setContent(postRegisterRequest.getContent());

        long id = postService.register(post);
        post.setPostId(id);

        return "redirect:/post/view.do?postId=" + post.getPostId();
    }

    @GetMapping("/register.do")
    public String registerForm(Model model) {
        model.addAttribute(new Post());
        return "post/postRegister";
    }

    @PostMapping("/delete.do")
    public String delete(HttpServletRequest request) {
        long postId = Long.parseLong(request.getParameter("postId"));
        log.info("DELETE POSTID <><><><<><><>>: {}", postId);
        HttpSession session = request.getSession();

        Post post = postService.getPostById(postId);

        if(post.getWriterUserId().equals(session.getAttribute("userId")) || session.getAttribute("userId").equals("admin")) {
            postService.delete(postId);
            return "redirect:/post/list.do";
        }
        return "redirect:/post/view.do?postId=" + postId;
    }

    @PostMapping("/update.do")
    public String update(@Valid PostRegisterRequest postRegisterRequest, BindingResult bindingResult, HttpServletRequest request) {
        if(bindingResult.hasFieldErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        long postId = Long.parseLong(request.getParameter("postId"));
        log.info("UPDATE POSTID<><><>><><>: {}", postId);
        Post post = postService.getPostById(postId);
        log.info("POST WRITERUSERID<><>><><><>: {}", post.getWriterUserId());
        log.info("POST getPOSTID<><>><><><>: {}", post.getPostId());

        Post postUpdate = new Post(post.getPostId(), postRegisterRequest.getTitle(), postRegisterRequest.getContent(), post.getWriterUserId(), post.getViewCount());
        postService.modify(postUpdate);

        return "redirect:/post/view.do?postId=" + post.getPostId();
    }

    @GetMapping("/update.do")
    public String updateForm(Model model, HttpServletRequest request) {
        long postId = Long.parseLong(request.getParameter("postId"));
        HttpSession session = request.getSession();

        Post post = postService.getPostById(postId);
        log.info("Post UPDATE<<<<<<<>>>>>>>>>: {}", postId);
        model.addAttribute("post", post);

        if(post.getWriterUserId().equals(session.getAttribute("userId")) || session.getAttribute("userId").equals("admin")) {
            return "post/postRegister";
        }
        return "redirect:/post/view.do?postId=" + postId;
    }

    @GetMapping("/view.do")
    public String view(Model model, @RequestParam(name = "postId", required = true) long postId) {
        Post post = postService.getPostById(postId);
        post.increaViewCount();
        log.info("Post View<<<<<<<>>>>>>>>>: {}", post);
        model.addAttribute("post", post);
        return "post/postView";
    }
}
