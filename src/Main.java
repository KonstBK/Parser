
import parcer.BookStatistic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {
    static String booksPath = "src/books";
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String command = scan.next();

        if (command.equals("exit")){
            System. exit(0);
        }else{
            try {
                BookStatistic bookStatistic = new BookStatistic(new File(booksPath, command));
                System.out.println("Get poplar words");
                bookStatistic.getPopularWords().forEach(System.out::println);
                System.out.println("Get unique words count " + bookStatistic.countUniqueWords());
                bookStatistic.printStatisticInConsole();
                bookStatistic.printStatisticInFile(new File("statistic.txt"));
            } catch (FileNotFoundException e) {
                System.out.println("Book not found");
            }
        }
    }
}