package com.nhnacademy.springmvcboard.mvcrepository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nhnacademy.springmvcboard.common.pagination.Page;
import com.nhnacademy.springmvcboard.domain.Post;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class JsonPostRepository implements PostRepository{

    private final ObjectMapper objectMapper;
    private static final String JSON_FILE_PATH = "/Users/jeongjewan/IdeaHomework/week8/day5/springmvcboard_paging/src/main/json/posts.json";

    public JsonPostRepository() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        File file = new File(JSON_FILE_PATH);
        if(file.exists()) {
            file.delete();
        }
    }

    private synchronized List<Post> readJsonFile() {
        List<Post> posts;

        File file = new File(JSON_FILE_PATH);
        if(!file.exists()) {
            return new ArrayList<>();
        }

        try (FileInputStream fileInputStream = new FileInputStream(file);
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

            posts = objectMapper.readValue(bufferedReader, new TypeReference<List<Post>>() {});
            return posts;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private synchronized void writeJsonFile(List<Post> postList) {
        File file = new File(JSON_FILE_PATH);

        try(FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            objectMapper.writeValue(bufferedWriter, postList);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public long register(Post post) {
        List<Post> posts = readJsonFile();
        long newId = 1;
        if (!posts.isEmpty()) {
            newId = posts.get(posts.size() - 1).getPostId() + 1;
        }
        post.setPostId(newId);
        posts.add(post);
        writeJsonFile(posts);
        return newId++;
    }

    @Override
    public void modify(Post post) {
        List<Post> posts = readJsonFile();
        Post targetPost = posts.stream()
                .filter(p -> p.getPostId() == post.getPostId())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Post not found"));
        targetPost.setTitle(post.getTitle());
        targetPost.setContent(post.getContent());
        writeJsonFile(posts);
    }

    @Override
    public Post removeById(long id) {
        List<Post> posts = readJsonFile();
        for(Post post : posts) {
            if(post.getPostId() == id) {
                posts.remove(post);
                writeJsonFile(posts);
                return post;
            }
        }
        return null;
    }

    @Override
    public Post getPostById(long id) {
        List<Post> posts = readJsonFile();
        return posts.stream()
                .filter(p -> p.getPostId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Post not found"));
    }

    @Override
    public List<Post> getPosts() {
        return readJsonFile();
    }

    @Override
    public int getTotalCount() {
        return readJsonFile().size();
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
                return readJsonFile().size();
            }

            @Override
            public List<Post> getList() {
                List<Post> posts = readJsonFile();
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
        return readJsonFile().contains(id);
    }
}
