package com.nhnacademy.board.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nhnacademy.board.user.User;
import com.nhnacademy.board.user.UserImpl;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class JsonUserRepository implements UserRepository {

    private final ObjectMapper objectMapper;
    private static final String JSON_FILE_PATH = "/Users/jeongjewan/IdeaHomework/week6/day04/boardversion4_Json/src/main/json/users.json";

    public JsonUserRepository() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        File file = new File(JSON_FILE_PATH);
        if(file.exists()) {
            file.delete();
        }
    }

    private synchronized List<UserImpl> readJsonFile() {
        List<UserImpl> users;

        File file = new File(JSON_FILE_PATH);
        if(!file.exists()) {
            return new ArrayList<>();
        }

        try (FileInputStream fileInputStream = new FileInputStream(file);
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

            users = objectMapper.readValue(bufferedReader, new TypeReference<List<UserImpl>>() {});
            return users;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private synchronized void writeJsonFile(List<UserImpl> userList) {
        File file = new File(JSON_FILE_PATH);

        try(FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            objectMapper.writeValue(bufferedWriter, userList);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(UserImpl user) {
        List<UserImpl> users = readJsonFile();
        users.add(user);
        writeJsonFile(users);
    }

    @Override
    public void modify(UserImpl user) {
        List<UserImpl> users = readJsonFile();
        int index = users.indexOf(user);
        if(index >= 0) {
            users.set(index, user);
            writeJsonFile(users);
        }
    }

    @Override
    public UserImpl remove(String id) {
        List<UserImpl> users = readJsonFile();
        for (UserImpl user : users) {
            if(user.getId().equals(id)) {
                users.remove(user);
                writeJsonFile(users);
                return user;
            }
        }
        return null;
    }

    @Override
    public UserImpl getUser(String id) {
        List<UserImpl> users = readJsonFile();
        for (UserImpl user : users) {
            if(user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<UserImpl> getUsers() {
        return readJsonFile();
    }
}
