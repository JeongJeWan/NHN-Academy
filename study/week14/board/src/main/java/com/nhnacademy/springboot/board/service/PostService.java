package com.nhnacademy.springboot.board.service;

import com.nhnacademy.springboot.board.domain.PostAll;
import com.nhnacademy.springboot.board.domain.PostComment;
import com.nhnacademy.springboot.board.domain.PostFile;
import com.nhnacademy.springboot.board.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface PostService {

    PostAll getPostContainAll(Long postId, String userId, boolean deleted);

    PostFile getPostContainFiles(Long postId);


    void createPost(String userId, String title, String content, List<MultipartFile> files);

    void modifyPost(Long postId, String title, String content, String modifier, List<MultipartFile> insertFiles, List<Long> deleteFiles);

    void recoverPostById(Long postId);

    void deletePostById(Long postId);


    void deletePostPermanentlyById(Long postId);

    Page<PostComment> getPostsWithComment(Pageable pageable, Optional<String> search, String content);
}
