package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.service.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class CsvStudents implements Students {

    List<Student> students = new ArrayList<>();

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
