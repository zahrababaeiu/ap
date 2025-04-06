package ap.exercises.ex3;

import java.io.FileWriter;
import java.io.IOException;

class Book {
    String name;
    String author;
    int pages;
    int year;

    public Book(String name, String author, int pages, int year) {
        this.name = name;
        this.author = author;
        this.pages = pages;
        this.year = year;
    }

    public String toString() {

        return name + "," + author + "," + pages + "," + year + "\n";
    }
}

class Student {
    String firstName;
    String lastName;
    int id;
    String major;

    public Student(String firstName, String lastName, int id, String major) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.major = major;
    }

    public String toString() {

        return firstName + "," + lastName + "," + id + "," + major + "\n";
    }
}

public class Main_EX3_LM_1_2_A {
    public static void main(String[] args) {
        Book[] books = {
                new Book("Riazi", "Ali Mohammadi", 125, 1378),
                new Book("Arabi", "Maryam Abbasi", 86, 1395),
                new Book("Farsi", "Sara Kamali", 560, 1387),
                new Book("A", "Mahdieh Sadeghi", 432, 1368)
        };

        Student[] students = {
                new Student("Zahra", "Babaei", 1231, "Computer"),
                new Student("Mahdi", "Alavi", 1232, "Architecture"),
                new Student("Sima", "Borji", 1233, "Computer")
        };

        try (FileWriter fw = new FileWriter("books.txt")) {
            for (int i = 0; i < books.length; i++) {
                fw.write(books[i].toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter fw = new FileWriter("students.txt")) {
            for (int i = 0; i < students.length; i++) {
                fw.write(students[i].toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("save successful");
    }
}
