package com.nhnacademy.springmvcboard.Service;

import com.nhnacademy.springmvcboard.common.pagination.Page;
import com.nhnacademy.springmvcboard.domain.Post;

public interface PostEventService {
    long register(Post post);
    void modify(Post post);
    void delete(long id);
    Page<Post> getPostList(int page, int size);
    Post getPostById(long id);
}
