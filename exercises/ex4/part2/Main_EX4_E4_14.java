package ap.exercises.ex4.part2;

import java.util.Scanner;

public class Main_EX4_E4_14 {
    public static void main(String[] args) {
        String Pattern1 = "+--+--+--+";
        String Pattern2 = "|  |  |  |";
        System.out.println("Enter count");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.println(Pattern1);
            System.out.println(Pattern2);
        }
        System.out.println(Pattern1);
    }
}
