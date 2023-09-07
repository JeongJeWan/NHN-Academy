package com.nhnacademy.springmvcboard.Service.impl;

import com.nhnacademy.springmvcboard.Service.PostEventService;
import com.nhnacademy.springmvcboard.common.pagination.Page;
import com.nhnacademy.springmvcboard.domain.Post;
import com.nhnacademy.springmvcboard.exception.DuplicatePostIdException;
import com.nhnacademy.springmvcboard.exception.PostNotFoundException;
import com.nhnacademy.springmvcboard.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Primary
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class DbPostServiceImpl implements PostEventService {

    private final PostMapper postMapper;

    @Override
    public long register(Post post) {
        if (postMapper.exitsById(post.getPostId())) {
            throw new DuplicatePostIdException(post.getPostId());
        }

        return postMapper.register(post);
    }

    @Override
    public void modify(Post post) {
        postMapper.modify(post);
    }

    @Override
    public void delete(long id) {
        postMapper.delete(id);
    }

    @Override
    public Page<Post> getPostList(int page, int size) {
        return new Page<Post>() {
            @Override
            public int getPageNumber() {
                return page;
            }

            @Override
            public int getPageSize() {
                return size;
            }

            @Override
            public int getTotalPageCount() {
                return (int)Math.ceil( (getTotalCount()*1.0) /getPageSize());
            }

            @Override
            public long getTotalCount() {
                return postMapper.getPosts().size();
            }

            @Override
            public List<Post> getList() {
                List<Post> posts = postMapper.getPosts().stream()
                        // .filter(o -> o.getRole().equals(Post.Role.USER))
                        .sorted(Comparator.comparing(Post::getPostId).reversed())
                        .collect(Collectors.toList());

                int start = (getPageNumber()-1)*getPageSize();
                int end = start + getPageSize();

                if(end > getTotalCount()){
                    end = (int) getTotalCount();
                }

                return posts.subList(start,end);
            }
        };
    }

    @Override
    public Post getPostById(long id) {
        Post post = postMapper.getPostById(id);
        if(Objects.isNull(post)) {
            throw new PostNotFoundException(id);
        }

        return postMapper.getPostById(id);
    }
}
