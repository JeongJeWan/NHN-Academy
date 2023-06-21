import java.io.*;
import java.util.HashMap;

public class Dictionary {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("words.txt"));
        HashMap<String, String> hashMap = new HashMap<>();
        String keyLine;
        String[] lines;
        String value;
        while ( (keyLine=reader.readLine()) != null ) {
            int index = keyLine.indexOf(".");
            value = keyLine.substring(index+1).trim();

            keyLine = keyLine.replaceAll("[^a-zA-z()]", " ");
            keyLine = keyLine.replaceAll("^\\s+", "");
            keyLine = keyLine.replaceFirst("\\s{2,}", "/").trim();
            lines = keyLine.split("/");
            value = value.replaceFirst(lines[0], "");
            System.out.println(value);
            lines[0] = lines[0].replaceAll("[()]", "");

        }
    }
}
