package com.nhnacademy.student.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nhnacademy.student.domain.Student;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JsonStudentRepository implements StudentRepository {
    private final ObjectMapper objectMapper;
    private static final String JSON_FILE_PATH="/Users/nhn/IdeaProjects/servlet:jsp/student/src/main/json/student.json";

    public JsonStudentRepository(){
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        File file =new File(JSON_FILE_PATH);
        if(file.exists()){
           file.delete();
        }

    }

    private synchronized List<Student> readJsonFile(){
        List<Student> students;
        File file =new File(JSON_FILE_PATH);

        if(!file.exists()){
            return new ArrayList<>();
        }

        try(FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        ) {
            students = objectMapper.readValue(bufferedReader, new TypeReference<List<Student>>() {});
            return  students;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private synchronized void writeJsonFile(List<Student> studentList){

        File file = new File(JSON_FILE_PATH);

        try(
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        ) {
            objectMapper.writeValue(bufferedWriter,studentList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Student student) {
        List<Student> students = readJsonFile();
        students.add(student);
        writeJsonFile(students);
    }

    @Override
    public void update(Student student) {
        List<Student> students = readJsonFile();
        Optional<Student> target = students.stream()
                .filter(o->o.getId().equals(student.getId()))
                .findFirst();

        if(target.isPresent()){
            target.get().update(student.getName(),student.getGender(),student.getAge());
            writeJsonFile(students);
        }
    }

    @Override
    public void deleteById(String id) {
        List<Student> students = readJsonFile();
        boolean isDeleted = students.removeIf(o->o.getId().equals(id));
        if(isDeleted){
            writeJsonFile(students);
        }
    }

    @Override
    public Student getStudentById(String id) {
        List<Student> students = readJsonFile();
        Optional<Student> student = students.stream().filter(o->o.getId().equals(id)).findFirst();
        if(student.isPresent()){
            return student.get();
        }
        return null;
    }

    @Override
    public List<Student> getStudents() {
        return readJsonFile();
    }

    @Override
    public boolean existById(String id) {
        List<Student> students = readJsonFile();
        long count = students.stream()
                .filter(o->o.getId().equals(id))
                .count();

        return  count > 0  ? true : false;
    }
}
