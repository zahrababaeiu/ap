package ap.exercises.ex2;
import java.util.Scanner;

public class Main_EX2_PM_1_1 {
    public static void main(String[] args) {
        System.out.println("enter n:");
        Scanner input= new Scanner(System.in);
        int n = input.nextInt();
        input.close();
        System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.print("*\t");
        }
        System.out.print("\n");
        for (int i = 1; i < n - 1; i++) {
            System.out.print("*\t");
            for (int j = 1; j < n - 1; j++) {
                System.out.print("\t");
            }
            System.out.println("*");
        }
        for (int i = 0; i < n; i++) {
            System.out.print("*\t");
        }
    }
}
