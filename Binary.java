import java.util.Scanner;

public class Binary {
    public static void main(String[] args) {
        System.out.println("enter your number");
        Scanner in = new Scanner(System.in);
        int number = in.nextInt();
        int[] bin = new int[100];
        if (number == 0) {
            System.out.println(0);
            return;
        }
        int i = 0;
        while (number != 0) {
            bin[i] = number % 2;
            i++;
            number /= 2;
        }
        for (int j = i - 1; j >= 0; j--) {
            System.out.print(bin[j]);
        }
        in.close();
    }
}
