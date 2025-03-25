package ap.exercises.ex2;

import java.util.Scanner;
import java.util.Random;

public class Main_EX2_PM_1_3 {
    public static void main(String[] args) {
        System.out.println("enter n:");
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        System.out.println("enter c:");
        int c = input.nextInt();
        input.close();

        if (c > (n * n)) {
            System.out.println("Error!try again");
            return;
        }

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

        Random rand = new Random();
        int count = 0;
        while (count < c) {
            int k = rand.nextInt(n) + 1;
            int l = rand.nextInt(n) + 1;
            if (myarray[k][l] == ' ') {
                myarray[k][l] = '.';
                count++;
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
