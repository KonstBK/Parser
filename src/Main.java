
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.PriorityQueue;
import javafx.util.Pair;


public class Main {
    public static void main(String[] args) {
        StringBuilder stat = new StringBuilder();
        BookParser reader = new BookParser();
        File file = reader.getFile("sapkovskiy_vedmak_1_poslednee-zhelanie_d62ohq_329732.txt");
        BookStatistic statistic = new BookStatistic();

        PriorityQueue<Pair<String, Integer>> pairs = new PriorityQueue<>(
                (Pair<String, Integer> first,
                Pair<String, Integer> second) -> second.getValue() - first.getValue());

        Map<String, Integer> words = statistic.countWords(file);

        words.forEach((String key, Integer value) -> pairs.add(new Pair<>(key, value)));

        for (int i = 0; i < 10 && i < pairs.size(); i++) {

            Pair<String, Integer> peek = pairs.poll();

            if (peek != null) {

                stat.append(peek.getKey()).append(" -> ").append(peek.getValue()).append("\n");

            }
        }

//        for (int i = 0; i < words.size(); i++) {
//            if (i == words.size()-1){
//                stat.append("Total number of words ").append(i);
//            }
//
//        }
        stat.append("Total number of words ").append(words.size());
        System.out.println(stat);

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("statistic.txt"));
            writer.write(stat.toString());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

//try {
//        FileReader fl = new FileReader(file);
//        char[] buffer = new char[10000000];
//        int chars = fl.read(buffer);
//        while (chars != -1) {
//        System.out.println(String.valueOf(buffer, 0, chars));
//        chars = fl.read(buffer);
//        }
//        fl.close();
//
//        } catch (IOException e) {
//        throw new RuntimeException(e);
//        }