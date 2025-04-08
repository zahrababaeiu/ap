package ap.exercises.ex3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Book2 {
    String name;
    String author;
    int pages;
    int year;

    public Book2(String name, String author, int pages, int year) {
        this.name = name;
        this.author = author;
        this.pages = pages;
        this.year = year;
    }

    public String toString() {
        return name + "," + author + "," + pages + "," + year;
    }
}

class Student2 {
    String firstName;
    String lastName;
    int id;
    String major;

    public Student2(String firstName, String lastName, int id, String major) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.major = major;
    }

    public String toString() {
        return firstName + "," + lastName + "," + id + "," + major;
    }
}

public class Main_EX3_LM_1_2_C {


    public static int MaxSize = 100;

    public static void main(String[] args) {
        Book2[] books = new Book2[MaxSize];
        Student2[] students = new Student2[MaxSize];

        readBooks(books);
        readStudents(students);
    }

    public static void readBooks(Book2[] books) {
        try (BufferedReader bookReader = new BufferedReader(new FileReader("books.txt"))) {
            String line;
            int b = 0;

            while ((line = bookReader.readLine()) != null) {
                String[] parts = line.split(",");
                books[b] = new Book2(parts[0], parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
                b++;
            }
            bookReader.close();
            System.out.println("Books:");
            for (int i = 0; i < b; i++) {
                System.out.println(books[i]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readStudents(Student2[] students) {
        try (BufferedReader studentReader = new BufferedReader(new FileReader("students.txt"))) {
            String line;
            int s = 0;

            while ((line = studentReader.readLine()) != null) {
                String[] parts = line.split(",");
                students[s] = new Student2(parts[0], parts[1], Integer.parseInt(parts[2]), parts[3]);
                s++;
            }
            studentReader.close();
            System.out.println("\nStudents:");
            for (int i = 0; i < s; i++) {
                System.out.println(students[i]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

