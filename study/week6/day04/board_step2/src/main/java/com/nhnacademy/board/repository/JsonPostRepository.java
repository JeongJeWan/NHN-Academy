package com.nhnacademy.board.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nhnacademy.board.post.ConcretePost;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class JsonPostRepository implements PostRepository{

    private final ObjectMapper objectMapper;
    private static final String JSON_FILE_PATH = "/Users/jeongjewan/IdeaHomework/week6/day04/board_step2/src/main/json/posts.json";

    public JsonPostRepository() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        File file = new File(JSON_FILE_PATH);
        if(file.exists()) {
            file.delete();
        }
    }

    private synchronized List<ConcretePost> readJsonFile() {
        List<ConcretePost> users;

        File file = new File(JSON_FILE_PATH);
        if(!file.exists()) {
            return new ArrayList<>();
        }

        try (FileInputStream fileInputStream = new FileInputStream(file);
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

            users = objectMapper.readValue(bufferedReader, new TypeReference<List<ConcretePost>>() {});
            return users;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private synchronized void writeJsonFile(List<ConcretePost> userList) {
        File file = new File(JSON_FILE_PATH);

        try(FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            objectMapper.writeValue(bufferedWriter, userList);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public long register(ConcretePost post) {
        List<ConcretePost> posts = readJsonFile();
        long newId = 1;
        if (!posts.isEmpty()) {
            newId = posts.get(posts.size() - 1).getId() + 1;
        }
        post.setId(newId);
        posts.add(post);
        writeJsonFile(posts);
        return newId;
    }

    @Override
    public void modify(ConcretePost post) {
        List<ConcretePost> posts = readJsonFile();
        ConcretePost targetPost = posts.stream()
                .filter(p -> p.getId() == post.getId())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Post not found"));
        targetPost.setTitle(post.getTitle());
        targetPost.setContent(post.getContent());
        writeJsonFile(posts);
    }

    @Override
    public void remove(long id) {
        List<ConcretePost> posts = readJsonFile();
        posts.removeIf(p -> p.getId() == id);
        writeJsonFile(posts);
    }

    @Override
    public ConcretePost getPost(long id) {
        List<ConcretePost> posts = readJsonFile();
        return posts.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Post not found"));
    }

    @Override
    public List<ConcretePost> getPosts() {
        return readJsonFile();
    }
}
