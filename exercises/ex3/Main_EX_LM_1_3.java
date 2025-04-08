package ap.exercises.ex3;

import java.io.*;
import java.util.*;

class Students3 {
    String firstName;
    String lastName;
    int id;
    String major;

    public Students3(String firstName, String lastName, int id, String major) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.major = major;
    }
}

public class Main_EX_LM_1_3 {
    public static int Maxstu = 100;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("enter first name: ");
        String fname = input.nextLine();
        System.out.print("enter last name: ");
        String lname = input.nextLine();

        ArrayList<Students3> studentsList = readInfo("students.txt");
        Search(studentsList, fname, lname);
    }

    public static ArrayList<Students3> readInfo(String filename) {
        ArrayList<Students3> list = new ArrayList<>();

        try (Scanner sc = new Scanner(new File(filename))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String fname = parts[0].trim();
                    String lname = parts[1].trim();
                    int id = Integer.parseInt(parts[2].trim());
                    String major = parts[3].trim();
                    list.add(new Students3(fname, lname, id, major));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void Search(ArrayList<Students3> students, String fname, String lname) {
        boolean found = false;
        for (Students3 student : students) {
            if (student.firstName.equals(fname) && student.lastName.equals(lname)) {
                System.out.println("***Found student***");
                System.out.println("Name: " + student.firstName + " " + student.lastName);
                System.out.println("ID: " + student.id);
                System.out.println("Major: " + student.major);
                found = true;
            }
        }
        if (found == false) {
            System.out.println("not found");
        }
    }
}