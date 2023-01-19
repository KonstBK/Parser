import java.io.File;

public class BookParser {
    private final File booksPath = new File("src/books");

    public File getFile(String fileName) {
        File book = null;
        File[] directoryFiles = getBooksPath().listFiles();
        if (directoryFiles != null) {
            for (File fileInDirectory : directoryFiles) {
                if (fileInDirectory.getName().equals(fileName)){
                    book = fileInDirectory;
                }
            }
        }
        return book;
    }

    public File getBooksPath() {
        return booksPath;
    }
}
