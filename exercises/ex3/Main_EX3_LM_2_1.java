package ap.exercises.ex3;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class BookLoan {
    String stuName;
    String bookName;
    String authorName;
    int date;
    int stuId;

    public BookLoan(String stuName, String bookName, String authorName, int date, int stuId) {
        this.stuName = stuName;
        this.bookName = bookName;
        this.authorName = authorName;
        this.date = date;
        this.stuId = stuId;
    }

    public String toString() {
        return "Student: " + stuName +
                ", Book: " + bookName +
                ", Author: " + authorName +
                ", Date: " + date +
                ", ID: " + stuId;
    }
}

public class Main_EX3_LM_2_1 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("enter student name: ");
        String name = input.nextLine();
        System.out.print("enter book name: ");
        String bname = input.nextLine();
        System.out.print("enter author name: ");
        String author = input.nextLine();
        System.out.print("enter date: ");
        int date = input.nextInt();
        System.out.print("enter student ID: ");
        int stuId = input.nextInt();

        ArrayList<BookLoan> bookLoans = load("BookLoan.txt");

        BookLoan newLoan = new BookLoan(name, bname, author, date, stuId);
        bookLoans.add(newLoan);

        saveAll(bookLoans, "BookLoan.txt");

        System.out.println("\n--------------------------");
        for (BookLoan bl : bookLoans) {
            System.out.println(bl);
        }
    }

    private static ArrayList<BookLoan> load(String file) {
        ArrayList<BookLoan> list = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(file))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String stuName = parts[0].trim();
                    String bookName = parts[1].trim();
                    String authorName = parts[2].trim();
                    int date = Integer.parseInt(parts[3].trim());
                    int stuId = Integer.parseInt(parts[4].trim());
                    list.add(new BookLoan(stuName, bookName, authorName, date, stuId));
                }
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static void saveAll(ArrayList<BookLoan> loans, String file) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            for (BookLoan bl : loans) {
                writer.println(bl.stuName + "," + bl.bookName + "," + bl.authorName + "," + bl.date + "," + bl.stuId);
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }
}
