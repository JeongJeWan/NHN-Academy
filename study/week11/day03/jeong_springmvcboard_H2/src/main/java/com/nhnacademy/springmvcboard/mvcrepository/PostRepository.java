package com.nhnacademy.springmvcboard.mvcrepository;

import com.nhnacademy.springmvcboard.common.pagination.Page;
import com.nhnacademy.springmvcboard.domain.Post;
import com.nhnacademy.springmvcboard.domain.User;

import java.util.List;

public interface PostRepository {
    long register(Post post);
    void modify(Post post);
    Post removeById(long id);

    Post getPostById(long id);
    List<Post> getPosts();

    int getTotalCount();
    Page<Post> getPagedList(int page, int size);
    boolean exitsById(long id);
}
