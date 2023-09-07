package com.nhnacademy.board.repository;

import com.nhnacademy.board.post.ConcretePost;
import com.nhnacademy.board.post.Post;

import java.util.List;

public interface PostRepository {
    long register(ConcretePost post);
    void modify(ConcretePost post);
    void remove(long id);

    ConcretePost getPost(long id);
    List<ConcretePost> getPosts();
}