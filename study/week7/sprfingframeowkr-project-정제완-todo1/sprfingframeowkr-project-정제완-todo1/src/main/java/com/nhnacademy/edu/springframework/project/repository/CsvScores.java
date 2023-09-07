package com.nhnacademy.edu.springframework.project.repository;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvScores implements Scores {

    private static CsvScores instance;

    List<Score> scores = new ArrayList<>();

    private CsvScores(){}
    public static Scores getInstance() {
        return instance != null ? instance : (instance = new CsvScores());
    }

    @Override
    public void load() {
        scores.clear();
        Resource resource = new ClassPathResource("/data/score.csv");
        try(BufferedReader br = new BufferedReader(new FileReader(resource.getFile()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int key = Integer.parseInt(values[0]);
                int value = Integer.parseInt(values[1]);
                Score newScore = new Score(key, value);
                scores.add(newScore);
            }
        } catch (IOException e) {
            System.err.println("score.csv 파일에서 데이터를 읽어 올 수 없습니다. class: CsvScore");
            e.printStackTrace();
        }

    }

    @Override
    public List<Score> findAll() {
        return scores;
    }
}
