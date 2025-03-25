package ap.exercises.ex2;

import java.util.Scanner;

public class Main_EX2_PM_1_2 {
    public static void main(String[] args) {
        System.out.println("enter n:");
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        input.close();
        char[][] myarray = new char[n + 2][n + 2];
        for (int i = 0; i < n + 2; i++) {
            for (int j = 0; j < n + 2; j++) {
                if (i == 0 || i == n + 1 || j == 0 || j == n + 1) {
                    myarray[i][j] = '*';
                } else {
                    myarray[i][j] = ' ';
                }
            }
        }
        for (int i = 0; i < n + 2; i++) {
            for (int j = 0; j < n + 2; j++) {
                System.out.print(myarray[i][j] + "\t");
            }
            System.out.print("\n");
        }
    }
}
