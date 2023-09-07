package com.nhnacademy.springmvcboard.mvcrepository;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nhnacademy.springmvcboard.common.pagination.Page;
import com.nhnacademy.springmvcboard.domain.User;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class JsonUserRepository implements UserRepository {

    private final ObjectMapper objectMapper;
    private static final String JSON_FILE_PATH = "/Users/jeongjewan/IdeaHomework/week8/day5/springmvcboard_paging/src/main/json/users.json";

    public JsonUserRepository() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        File file = new File(JSON_FILE_PATH);
        if(file.exists()) {
            file.delete();
        }
    }

    private synchronized List<User> readJsonFile() {
        List<User> users;

        File file = new File(JSON_FILE_PATH);
        if(!file.exists()) {
            return new ArrayList<>();
        }

        try (FileInputStream fileInputStream = new FileInputStream(file);
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

            users = objectMapper.readValue(bufferedReader, new TypeReference<List<User>>() {});
            return users;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private synchronized void writeJsonFile(List<User> userList) {
        File file = new File(JSON_FILE_PATH);

        try(FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            objectMapper.writeValue(bufferedWriter, userList);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(User user) {
        List<User> users = readJsonFile();
        users.add(user);
        writeJsonFile(users);
    }

    @Override
    public void modify(User user) {
        List<User> users = readJsonFile();
        int index = users.indexOf(user);
        if(index >= 0) {
            users.set(index, user);
            writeJsonFile(users);
        }
    }

    @Override
    public User remove(String id) {
        List<User> users = readJsonFile();
        for (User user : users) {
            if(user.getUserId().equals(id)) {
                users.remove(user);
                writeJsonFile(users);
                return user;
            }
        }
        return null;
    }

    @Override
    public User getUserById(String id) {
        List<User> users = readJsonFile();
        for (User user : users) {
            if(user.getUserId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> getUsers() {
        return readJsonFile();
    }

    @Override
    public int getTotalCount() {
        return readJsonFile().size();
    }

    @Override
    public Page<User> getPagedList(int page, int size) {
        return new Page<User>() {
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
            public List<User> getList() {
                List<User> users = readJsonFile();

                int start = (getPageNumber()-1)*getPageSize();
                int end = start + getPageSize();

                if(end > getTotalCount()){
                    end = (int) getTotalCount();
                }

                log.info("user-page-start:{}",start);
                log.info("user-page-end:{}",end);

                return users.subList(start,end);
            }
        };
    }

    @Override
    public boolean existById(String id) {
        return readJsonFile().contains(id);
    }
}
