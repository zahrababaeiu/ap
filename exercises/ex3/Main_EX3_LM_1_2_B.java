package ap.exercises.ex3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Book1 {
    String name;
    String author;
    int pages;
    int year;

    public Book1(String name, String author, int pages, int year) {
        this.name = name;
        this.author = author;
        this.pages = pages;
        this.year = year;
    }

    public String toString() {
        return name + "," + author + "," + pages + "," + year;
    }
}

class Student1 {
    String firstName;
    String lastName;
    int id;
    String major;

    public Student1(String firstName, String lastName, int id, String major) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.major = major;
    }

    public String toString() {
        return firstName + "," + lastName + "," + id + "," + major;
    }
}

public class Main_EX3_LM_1_2_B {
    public static int MaxSize=100;
    public static void main(String[] args) {
        /*Book[] books = {
                new Book("Riazi", "Ali Mohammadi", 125, 1378),
                new Book("Arabi", "Maryam Abbasi", 86, 1395),
                new Book("Farsi", "Sara Kamali", 560, 1387),
                new Book("A", "Mahdieh Sadeghi", 432, 1368)
        };

        Student[] students = {
                new Student("Zahra", "Babaei", 1231, "Computer"),
                new Student("Mahdi", "Alavi", 1232, "Architecture"),
                new Student("Sima", "Borji", 1233, "Computer")
        };*/

        Book1[] books1 = new Book1[MaxSize];
        Student[] students1 = new Student[MaxSize];

        try {
            int b = 0;
            BufferedReader bookReader = new BufferedReader(new FileReader("books.txt"));
            String line;
            while ((line = bookReader.readLine()) != null) {
                String[] parts = line.split(",");
                books1[b] = new Book1(parts[0], parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
                b++;
            }
            bookReader.close();
            System.out.println("Books:");
            for (int i = 0; i < b; i++) {
                System.out.println(books1[i]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            int s = 0;
            BufferedReader studentReader = new BufferedReader(new FileReader("students.txt"));
            String line;
            while ((line = studentReader.readLine()) != null) {
                String[] parts = line.split(",");
                students1[s] = new Student(parts[0], parts[1], Integer.parseInt(parts[2]), parts[3]);
                s++;
            }
            studentReader.close();
            System.out.println("\nStudents:");
            for (int i = 0; i < s; i++) {
                System.out.print(students1[i]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
