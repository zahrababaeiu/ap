package ap.exercises.ex4.part2;

import java.util.Scanner;

class Student {
    private String name;
    private int score;
    private int StudentTester;

    public Student(String name, int StudentTester) {
        this.name = name;
        this.StudentTester = StudentTester;
    }

    public String getName() {
        return name;
    }

    public void addQuize(int score) {
        if (score < 0) {
            System.out.println("invalid score");
            return;
        }
        this.score += score;
    }

    public int getTotalScore() {
        return score;
    }

    public Double getAverageScore() {
        double avg = 0;
        avg = getTotalScore() / this.StudentTester;
        return avg;
    }

    public void print() {
        System.out.println("Name: " + this.name + "\nAverage: " + getAverageScore());
    }
}

public class Main_EX4_P3_8 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the name of the student : ");
        String name = input.nextLine();
        System.out.println("Enter number of quizes : ");
        int quizes = input.nextInt();
        System.out.println("Enter score : ");
        Student student = new Student(name, quizes);
        for (int i = 0; i < quizes; i++) {
            int score = input.nextInt();
            student.addQuize(score);
        }
        student.print();
        input.close();
    }
}
