import java.util.Scanner;

public class star {
    public static void main(String[] args) {
        int n, nb = 1, ns;
        System.out.println("enter a number");
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        in.close();
        ns = n - 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < ns; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < nb; j++) {
                System.out.print("*");
            }
            System.out.println();
            ns--;
            nb += 2;
        }
        ns = 1;
        nb = 2 * n - 3;
        for (int i = n - 1; i > 0; i--) {
            for (int j = 0; j < ns; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < nb; j++) {
                System.out.print("*");
            }
            System.out.println();
            ns++;
            nb -= 2;
        }
    }

}

