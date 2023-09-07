package com.nhnacademy.springmvcboard.repository;

import com.nhnacademy.springmvcboard.domain.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
    public boolean exitsById(long id) {
        return postMap.containsKey(id);
    }
}
