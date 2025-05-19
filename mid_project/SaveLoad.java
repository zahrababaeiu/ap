package ap.mid_project;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SaveLoad {
    ArrayList<String> loans = new ArrayList<>();

    public SaveLoad() {
        ArrayList<Library> library = new ArrayList<>();
    }

    public void SaveStuToFile(Student student) {
        try {
            FileWriter fw = new FileWriter("E:/java/library/Students.txt", true);
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
            File file = new File("E:/java/library/Students.txt");
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
            FileWriter fw = new FileWriter("E:/java/library/Loners.txt", true);
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
            File file = new File("E:/java/library/Loners.txt");
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
            FileWriter fw = new FileWriter("E:/java/library/Books.txt", true);
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
            File file = new File("E:/java/library/Books.txt");
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

    public void SaveLoansTofile(ArrayList<Loan> loans) {
        try {
            FileWriter fw = new FileWriter("E:/java/library/Loans.txt");
            PrintWriter pw = new PrintWriter(fw);

            for (Loan loan : loans) {
                pw.println(loan.toString());
            }

            pw.close();
            System.out.println("Saved Successfully");
        } catch (IOException e) {
            System.out.println("Error in save to file " + e.getMessage());
        }
    }

    public boolean LoadLoansFromFile() {
        boolean found = false;
        loans.clear();
        try {
            File file = new File("E:/java/library/Loans.txt");
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                loans.add(line);
                String sec = split(line);
                if (sec != null) {
                    found = true;
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error in loading file " + e.getMessage());
        }
        return found;
    }

    public ArrayList<Loan> readLoansFromFile() {
        ArrayList<Loan> loans = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File("E:/java/library/Loans.txt"));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                Loan loan = new Loan();
                loan = loan.stringForm(line);
                loans.add(loan);
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return loans;
    }

    public String split(String line) {
        String[] section = line.split(",");
        String sec = null;
        if (section.length > 5) {
            sec = section[5];
        }
        return sec;
    }

}

