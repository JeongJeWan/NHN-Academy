package org.nhnacademy;

import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class WordSearchProgram {
    public static void main(String[] args) {
        HashMap<String, String> words = new HashMap<>();
        try {
            Scanner scanner = new Scanner(new File("words.txt"));
            String[] lines;
            while (scanner.hasNext()) {
                String word = scanner.nextLine();
                int index = word.indexOf(".");
                String value = word.substring(index+1).trim();
                word = word.replaceAll("[^a-zA-z()]", " ");
                word = word.replaceAll("^\\s+", "");
                word = word.replaceFirst("\\s{2,}", "/").trim();
                lines = word.split("/");
                value = value.replaceFirst(lines[0], "");
                lines[0] = lines[0].replaceAll("[()]", "");
                words.put(lines[0], value);
            }
            System.out.println("Dictionary loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("words.txt 파일을 찾을 수 없습니다.");
            System.exit(1);
        }

        Scanner inputScanner = new Scanner(System.in);
        while (true) {
            System.out.print("검색한 단어를 입력하세요: ");
            String input = inputScanner.nextLine();
            if (input.equals("exit()")) {
                break;
            }
            if (words.containsKey(input)) {
                System.out.println(input + "은(는) words.txt 파일에 포함되어 있습니다.");
                try {
                    Scanner scanner = new Scanner(new File("words.txt"));
                    int lineNumber = 1; // 라인 번호 초기화
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        if (line.contains(input)) { // 입력된 단어가 해당 라인에 포함되어 있는지 여부 확인
                            System.out.println(lineNumber + ": " + line); // 라인 번호와 해당 라인 출력
                        }
                        lineNumber++; // 다음 라인으로 이동
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("words.txt 파일을 찾을 수 없습니다.");
                    System.exit(1);
                }
            } else {
                System.out.println(input + "은(는) words.txt 파일에 포함되어 있지 않습니다.");
            }
        }
        inputScanner.close();
    }
}
