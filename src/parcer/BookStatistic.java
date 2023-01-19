package parcer;

import java.io.*;
import java.util.*;

public class BookStatistic {

    private final Map<String, Integer> words;
    private final static long limitOfPopularWords = 10L;

    public BookStatistic(File book) throws FileNotFoundException {
        if (!book.exists()){
            throw new FileNotFoundException();
        }
        words = getWordsByCount(book);
    }

    public List<String> getPopularWords(){
        return words.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(limitOfPopularWords)
                .map(Map.Entry::getKey)
                .toList();
    }

    public int countUniqueWords(){
        return words.entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .toList()
                .size();
    }

    public void printStatisticInFile(File statistic){
        StringBuilder stringBuilder = new StringBuilder();

        words.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .forEach(entry -> stringBuilder.append(entry.getKey())
                                .append(" -> ")
                                .append(entry.getValue()));

        stringBuilder.append("Total number of words ").append(words.size());

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(statistic));
            writer.write(stringBuilder.toString());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void printStatisticInConsole(){
        words.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(entry -> System.out.println(entry.getKey() + " -> " + entry.getValue()));
    }

    private Map<String, Integer> getWordsByCount(File book){
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
