package com.nhnacademy.springmvcboard.Service.impl;

import com.nhnacademy.springmvcboard.common.pagination.Page;
import com.nhnacademy.springmvcboard.domain.Post;
import com.nhnacademy.springmvcboard.exception.DuplicatePostIdException;
import com.nhnacademy.springmvcboard.exception.PostNotFoundException;
import com.nhnacademy.springmvcboard.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post getPostById(long id) {
        Post post = postRepository.getPostById(id);
        if(Objects.isNull(post)) {
            throw new PostNotFoundException(id);
        }
        return postRepository.getPostById(id);
    }

    public Post delete(long id) {
        return postRepository.removeById(id);
    }

    public List<Post> getPosts() {
        return postRepository.getPosts();
    }

    public long register(Post post) {
        if(postRepository.exitsById(post.getPostId())) {
            throw new DuplicatePostIdException(post.getPostId());
        }

        return postRepository.register(post);
    }

    public void modify(Post post) {
        postRepository.modify(post);
    }

    public Page<Post> getPostList(int page, int size) {
        return postRepository.getPagedList(page, size);
    }
}
