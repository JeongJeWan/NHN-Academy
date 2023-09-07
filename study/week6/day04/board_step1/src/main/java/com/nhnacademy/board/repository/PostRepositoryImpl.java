package com.nhnacademy.board.repository;

import com.nhnacademy.board.post.ConcretePost;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PostRepositoryImpl implements PostRepository{

    private Map<Long, ConcretePost> postMap;
    private long nextId;

    public PostRepositoryImpl(){
        this.postMap = new ConcurrentHashMap<>();
        this.nextId = 1L;
    }

    @Override
    public long register(ConcretePost post) {
        post.setId(nextId);
        postMap.put(nextId, post);
        return nextId++;
    }

    @Override
    public void modify(ConcretePost post) {
        postMap.put(post.getId(), post);
    }

    @Override
    public void remove(long id) {
        postMap.remove(id);
    }

    @Override
    public ConcretePost getPost(long id) {
        return postMap.get(id);
    }

    @Override
    public List<ConcretePost> getPosts() {
        return new ArrayList<>(postMap.values());
    }
}
