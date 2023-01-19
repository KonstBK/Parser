import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BookStatistic {

    public Map<String, Integer> countWords(File book){
        Map<String, Integer> result = new HashMap<>();

        try (FileReader fileReader = new FileReader(book); BufferedReader bufferedReader = new BufferedReader(fileReader)){
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] words = removeCharacters(line);
                for (String word : words){
                    if (word.length() <3) continue;
                    if (result.containsKey(word)) result.put(word, result.get(word) +1);
                    else result.put(word, 1);
                }
            }
        } catch (IOException e) {
            System.out.println("book is not available");
        }
        return result;
    }

    private String[] removeCharacters(String line){
        String charsToRemove = "!.?,{}[]()#";
        for (Character c: charsToRemove.toCharArray()){
            line = line.replace(c.toString(), "");
        }
        return line.toLowerCase().split(" ");
    }
}
