package ap.mid_project;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SaveLoad {

    public SaveLoad() {
        ArrayList<Library> library = new ArrayList<>();
    }

    public void SaveStuToFile(Student student) {
        try {
            FileWriter fw = new FileWriter("E:\\java\\library\\Students.txt", true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(student);
            pw.close();
            System.out.println("Saved Successfully");
        } catch (IOException e) {
            System.out.println("Error in save to file " + e.getMessage());
        }
    }

    public void LoadStuFromFile() {
        try {
            File file = new File("E:\\java\\library\\Students.txt");
            Scanner sc = new Scanner(file);
            ArrayList<String> read = new ArrayList<>();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                read.add(line);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error in loading file " + e.getMessage());
        }
    }

    public void SaveLonersToFile(Loaner loaner) {
        try {
            FileWriter fw = new FileWriter("E:\\java\\library\\Loners.txt", true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(loaner);
            pw.close();
            System.out.println("Saved Successfully");
        } catch (IOException e) {
            System.out.println("Error in save to file " + e.getMessage());
        }

    }

    public void LoadLonersFromFile() {
        try {
            File file = new File("E:\\java\\library\\Loners.txt");
            Scanner sc = new Scanner(file);
            ArrayList<String> read = new ArrayList<>();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                read.add(line);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error in loading file " + e.getMessage());
        }

    }

    public void SaveBookToFile(Book book) {
        try {
            FileWriter fw = new FileWriter("E:\\java\\library\\Books.txt", true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(String.valueOf(book));
            pw.close();
            System.out.println("Saved Successfully");
        } catch (IOException e) {
            System.out.println("Error in save to file " + e.getMessage());
        }
    }

    public void LoadBookFromFile() {
        try {
            File file = new File("E:\\java\\library\\Books.txt");
            Scanner sc = new Scanner(file);
            ArrayList<String> read = new ArrayList<>();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                read.add(line);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error in loading file " + e.getMessage());
        }
    }
}

