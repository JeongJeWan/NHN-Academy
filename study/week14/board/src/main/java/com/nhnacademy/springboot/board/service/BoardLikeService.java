package com.nhnacademy.springboot.board.service;

import com.nhnacademy.springboot.board.entity.Like;
import com.nhnacademy.springboot.board.entity.Post;
import com.nhnacademy.springboot.board.entity.User;
import com.nhnacademy.springboot.board.exception.InvalidPostAccessException;
import com.nhnacademy.springboot.board.exception.InvalidUserException;
import com.nhnacademy.springboot.board.repository.LikeRepository;
import com.nhnacademy.springboot.board.repository.PostRepository;
import com.nhnacademy.springboot.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardLikeService implements LikeService {
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;


    @Override
    public boolean existsLike(long postId, String userId) {
        Like.Pk pk = new Like.Pk(postId, userId);

        return likeRepository.existsById(pk);
    }


    @Override
    public void createLike(long postId, String userId) {
        User user= userRepository.findById(userId)
                .orElseThrow(() -> new InvalidUserException(userId));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new InvalidPostAccessException(postId));

        Like like = new Like();

        like.setPk(new Like.Pk(postId, userId));
        like.setCreatedAt(new Date());
        like.setUser(user);
        like.setPost(post);

        likeRepository.save(like);
    }

    @Override
    public void deleteLike(long postId, String userId) {
        Like.Pk pk = new Like.Pk(postId, userId);

        likeRepository.deleteById(pk);
    }
}
