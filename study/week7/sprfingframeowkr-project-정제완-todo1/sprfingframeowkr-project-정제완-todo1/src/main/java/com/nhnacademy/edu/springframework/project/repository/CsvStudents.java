package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.service.Student;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class CsvStudents implements Students {

    private static CsvStudents instance;
    List<Student> students = new ArrayList<>();

    private CsvStudents() {

    }
    public static Students getInstance() {
        return instance != null ? instance : (instance = new CsvStudents());
    }

    @Override
    public void load() {
        students.clear();

        Resource resource = new ClassPathResource("/data/student.csv");

        try(BufferedReader br = new BufferedReader(new FileReader(resource.getFile()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int key = Integer.parseInt(values[0]);
                String value = values[1];
                Student student = new Student(key, value);
                students.add(student);
            }
        } catch (IOException e) {
            System.err.println("student.csv 파일에서 데이터를 읽어 올 수 없습니다, Class: CsvStudents");
            e.printStackTrace();
        }
    }

    @Override
    public Collection<Student> findAll() {
        return students;
    }

    /**
     * TODO 8 : students 데이터에 score 정보를 추가하세요.
     * @param scores
     */
    @Override
    public void merge(Collection<Score> scores) {
        for(Score score : scores) {
            for(Student student : students) {
                if(score.getStudentSeq() == student.getSeq()) {
                    student.setScore(score);
                    break;
                }
            }
        }
    }
}
