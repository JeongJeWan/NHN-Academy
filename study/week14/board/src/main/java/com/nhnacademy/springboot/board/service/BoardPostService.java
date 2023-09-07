package com.nhnacademy.springboot.board.service;

import com.nhnacademy.springboot.board.domain.PostAll;
import com.nhnacademy.springboot.board.domain.PostComment;
import com.nhnacademy.springboot.board.domain.PostFile;
import com.nhnacademy.springboot.board.entity.*;
import com.nhnacademy.springboot.board.exception.InvalidPostAccessException;
import com.nhnacademy.springboot.board.exception.InvalidUserException;
import com.nhnacademy.springboot.board.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BoardPostService implements PostService {
    private final PostRepository postRepository;
    private final FileRepository fileRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;

    @Override
    public void createPost(String userId, String title, String content, List<MultipartFile> files) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new InvalidUserException(userId));

        Post savedPost = getSavedPost(title, content, user);

        if (!files.get(0).isEmpty())
            saveFiles(files, savedPost, new Date());
    }

    private void saveFiles(List<MultipartFile> files, Post post, Date createdAt) {
        File file;
        for (MultipartFile multipartFile : files) {
            file = new File();
            file.setPost(post);
            file.setFileName(multipartFile.getOriginalFilename());
            file.setCreatedAt(createdAt);

            fileRepository.save(file);
        }
    }

    private Post getSavedPost(String title, String content, User user) {
        Post post = new Post();

        post.setUser(user);
        post.setTitle(title);
        post.setContent(content);
        post.setCreatedAt(new Date());
        post.setDeleted(false);

        return postRepository.save(post);
    }

    @Override
    @Transactional(readOnly = true)
    public PostAll getPostContainAll(Long postId, String userId, boolean deleted) {
        Post post = postRepository.findByIdAndDeleted(postId, deleted)
                .orElseThrow(() -> new InvalidPostAccessException(postId));

        return getPostAll(postId, userId, post);
    }

    private PostAll getPostAll(Long postId, String userId, Post post) {
        long likes = likeRepository.countByUser_UserId(userId);
        List<File> files = fileRepository.findByPost_Id(postId);
        List<Comment> comments = commentRepository.findByPost_IdOrderByCreatedAt(postId);

        return new PostAll(post, likes, files, comments, null);
    }

    @Override
    @Transactional(readOnly = true)
    public PostFile getPostContainFiles(Long postId) {
        Post post = postRepository.findByIdAndDeleted(postId, false)
                .orElseThrow(() -> new InvalidPostAccessException(postId));

        List<File> files = fileRepository.findByPost_Id(postId);

        return new PostFile(post.getId(), post.getTitle(), post.getContent(), files);
    }


    @Override
    public void modifyPost(Long postId, String title, String content, String modifier, List<MultipartFile> insertFiles, List<Long> deleteFiles) {
        Post post = postRepository.findByIdAndDeleted(postId, false)
                .orElseThrow(() -> new InvalidPostAccessException(postId));

        post.setTitle(title);
        post.setContent(content);
        post.setModifier(modifier);

        postRepository.save(post);

        if (Objects.nonNull(deleteFiles)) {
            for (Long fileId : deleteFiles) {
                fileRepository.deleteById(fileId);
            }
        }

        if (!insertFiles.get(0).isEmpty())
            saveFiles(insertFiles, post, new Date());
    }

    @Override
    public void recoverPostById(Long postId) {
        checkInvalidPost(postId);
        postRepository.updateByPostId(postId, false);
    }

    @Override
    public void deletePostById(Long postId) {
        checkInvalidPost(postId);
        postRepository.updateByPostId(postId, true);
    }

    @Override
    public void deletePostPermanentlyById(Long postId) {
        checkInvalidPost(postId);
        postRepository.deleteById(postId);
    }

    @Override
    public Page<PostComment> getPostsWithComment(Pageable pageable, Optional<String> search, String content) {
        Page<Post> posts;
        if (search.isPresent() && Objects.nonNull(content)) {    //검색어 + like
//            posts = new PageImpl<>(null);
        } else if (search.isPresent()) {   //검색어
//            posts = postRepository.findBy2(pageable, search.get());
        } else if (Objects.nonNull(content)) {  // like
//            posts = postRepository.findBy2(pageable, content);
        } else {    //all
            posts = postRepository.findBy(pageable);
        }
        posts = postRepository.findBy(pageable);

        return getPostCommentsToPage(posts);
    }

    private Page<PostComment> getPostCommentsToPage(Page<Post> posts) {
        long total = posts.getTotalElements();
        Pageable pageable = posts.getPageable();

        List<PostComment> postComments = new ArrayList<>();
        for (Post post : posts.getContent()) {
            long commentCount = commentRepository.countByPost_Id(post.getId());
            postComments.add(new PostComment(
                    post.getId(),
                    post.getUser().getUserId(),
                    post.getTitle(),
                    post.getCreatedAt(),
                    post.getModifier(),
                    post.getUpdatedAt(),
                    (int) commentCount
            ));
        }
        return new PageImpl<>(postComments, pageable, total);
    }

    private void checkInvalidPost(Long postId) {
        if (!postRepository.existsById(postId)) {
            throw new InvalidPostAccessException(postId);
        }
    }
}
