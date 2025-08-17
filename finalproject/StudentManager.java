package finalproject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    private List<Student> students;
    private final String STUDENTS_FILE = "students.txt";

    public StudentManager() {
        students = new ArrayList<>();
        loadStudents();
    }

    //Load from file
    private void loadStudents() {
        File file = new File(STUDENTS_FILE);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                students.add(Student.fromString(line));
            }
        } catch (IOException e) {
            System.out.println("Error loading students: " + e.getMessage());
        }
    }

    public void registerStudent(String name, String studentId, String username, String password) {
        if (isUsernameTaken(username)) {
            System.out.println("This username already exists. Please choose a different username.");
            return;
        }

        Student newStudent = new Student(name, studentId, username, password);
        students.add(newStudent);
        saveStudent(newStudent);
        System.out.println("Student registration completed successfully.");
    }

    //Save in file
    private void saveStudent(Student student) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(STUDENTS_FILE, true))) {
            bw.write(student.toFileString());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error saving student: " + e.getMessage());
        }
    }

    public Student authenticateStudent(String username, String password) {
        return students.stream()
                .filter(s -> s.getUsername().equals(username) && s.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    public void displayStudents() {
        System.out.println("\n--- List of Registered Students ---");
        if (students.isEmpty()) {
            System.out.println("No students have registered yet.");
            return;
        }
        for (Student s : students) {
            System.out.println(s);
        }
    }

    private boolean isUsernameTaken(String username) {
        return students.stream().anyMatch(s -> s.getUsername().equals(username));
    }

    public int getStudentCount() {
        return students.size();
    }
}
