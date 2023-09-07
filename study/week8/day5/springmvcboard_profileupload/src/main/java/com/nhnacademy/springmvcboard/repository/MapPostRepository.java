package com.nhnacademy.springmvcboard.repository;

import com.nhnacademy.springmvcboard.common.pagination.Page;
import com.nhnacademy.springmvcboard.domain.Post;
import com.nhnacademy.springmvcboard.domain.User;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Slf4j
public class MapPostRepository implements PostRepository{

    private Map<Long, Post> postMap;
    private long nextId;

    public MapPostRepository () {
        this.postMap = new ConcurrentHashMap<>();
        this.nextId = 1L;
    }

    @Override
    public long register(Post post) {
        post.setPostId(nextId);
        postMap.put(nextId, post);
        return nextId++;
    }

    @Override
    public void modify(Post post) {
        postMap.put(post.getPostId(), post);
    }

    @Override
    public Post removeById(long id) {

        return postMap.remove(id);
    }

    @Override
    public Post getPostById(long id) {
        return postMap.get(id);
    }

    @Override
    public List<Post> getPosts() {
        return new ArrayList<>(postMap.values());
    }

    @Override
    public int getTotalCount() {
        return postMap.size();
    }

    @Override
    public Page<Post> getPagedList(int page, int size) {
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
                return postMap.size();
            }

            @Override
            public List<Post> getList() {
                List<Post> posts = postMap.values().stream()
                        // .filter(o -> o.getRole().equals(Post.Role.USER))
                        .sorted(Comparator.comparing(Post::getWriteTime).reversed())
                        .collect(Collectors.toList());

                int start = (getPageNumber()-1)*getPageSize();
                int end = start + getPageSize();

                if(end > getTotalCount()){
                    end = (int) getTotalCount();
                }

                log.info("totalCount:{}" + getTotalCount());
                log.info("post-page-start:{}",start);
                log.info("post-page-end:{}",end);

                return posts.subList(start,end);
            }
        };
    }

    @Override
    public boolean exitsById(long id) {
        return postMap.containsKey(id);
    }
}
