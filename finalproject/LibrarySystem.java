package finalproject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// LibrarySystem.java
public class LibrarySystem {
    private StudentManager studentManager;
    private MenuHandler menuHandler;
    private final String BOOKS_FILE = "E:/books.txt";


    public LibrarySystem() {
        this.studentManager = new StudentManager();
        this.menuHandler = new MenuHandler(this);
    }


    public List<Book> loadBooks() {
        List<Book> books = new ArrayList<>();
        try {
            File file = new File(BOOKS_FILE);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    Book book = Book.fromString(line);
                    if (book != null) {
                        books.add(book);
                    }
                }
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Error in reading file: " + e.getMessage());
        }
        return books;
    }

    public void searchBooks(String title, String author, String year) {
        List<Book> result = new ArrayList<>();
        List<Book>books = loadBooks();

        for (Book book : books) {
            if (title != null && !title.isEmpty() && !book.getTitle().trim().equalsIgnoreCase(title.trim())) {
                continue;
            }
            if (author != null && !author.isEmpty() && !book.getAuthor().trim().equalsIgnoreCase(author.trim())) {
                continue;
            }
            if (year != null && !year.isEmpty() && !String.valueOf(book.getYear()).trim().equals(year.trim())) {
                continue;
            }
            result.add(book);
        }

        if (result.isEmpty()) {
            System.out.println("Book not found.");
        } else {
            System.out.println("Found books:");
            for (Book book : result) {
                System.out.println(book);
                if (book.isLoaned()) {
                    System.out.println("Available State: No");
                } else {
                    System.out.println("Available State: Yes");
                }
            }
        }
    }

    public int getStudentCount() {
        return this.studentManager.getStudentCount();
    }

    public void registerStudent(String name, String studentId, String username, String password) {
        studentManager.registerStudent(name, studentId, username, password);
    }

    public Student authenticateStudent(String username, String password) {
        return studentManager.authenticateStudent(username, password);
    }

    public void editStudentInformation(Student student) {
        System.out.println("Not implemented.");
    }

    public void borrowBook(Student student) {
        System.out.println("Not implemented.");
    }

    public void returnBook(Student student) {
        System.out.println("Not implemented.");
    }

    public void displayAvailableBooks() {
        System.out.println("Not implemented.");
    }

    public void start() {
        menuHandler.displayMainMenu();
    }

    public static void main(String[] args) {
        LibrarySystem system = new LibrarySystem();
        system.start();
    }
}
