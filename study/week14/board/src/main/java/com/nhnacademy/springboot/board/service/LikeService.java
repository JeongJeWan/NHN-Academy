package com.nhnacademy.springboot.board.service;

public interface LikeService {
    void createLike(long postId, String userId);

    void deleteLike(long postId, String userId);

    boolean existsLike(long postId, String userId);
}
