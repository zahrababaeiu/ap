package ap.exercises.ex2;

import java.util.Scanner;

public class Main_EX2_PM_1_4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("enter your char:");
            char yourChar = input.next().charAt(0);
            switch (yourChar) {
                case 'w':
                    System.out.println("UP");
                    break;
                case 'a':
                    System.out.println("LEFT");
                    break;
                case 's':
                    System.out.println("DOWN");
                    break;
                case 'd':
                    System.out.println("RIGHT");
                    break;
                case 'q':
                    System.out.println("EXIT");
                    input.close();
                    return;
                default:
                    System.out.println("WARNING");
            }
        }
    }
}
