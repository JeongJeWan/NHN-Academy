package com.nhnacademy.springmvcboard.repository;

import com.nhnacademy.springmvcboard.domain.Post;

import java.util.List;

public interface PostRepository {
    long register(Post post);
    void modify(Post post);
    Post removeById(long id);

    Post getPostById(long id);
    List<Post> getPosts();
    boolean exitsById(long id);
}
