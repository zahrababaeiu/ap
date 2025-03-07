package ap;

import java.util.Scanner;

public class star2 {
    public static void main(String[] args) {
        System.out.println("enter n:");
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.close();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("*\t");
            }
            System.out.print("\t");
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 1; k++) {
                    if (i == 0 || i == n - 1) {
                        System.out.print("*\t");
                    } else {
                        if (j == 0 || j == n - 1) {
                            System.out.print("*\t");
                        } else {
                            System.out.print("\t");
                        }
                    }
                }
            }
            System.out.println();
        }
    }
}
