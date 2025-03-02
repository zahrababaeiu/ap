import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("enter your number:");
        double num = in.nextDouble();
        if (num == 0) {
            System.out.println("zero");
        } else if (num > 0) {
            System.out.println("positive");
        } else {
            System.out.println("negative");
        }
        //large or small
        if (Math.abs(num) > 1000000) {
            System.out.println("large");
        } else if (Math.abs(num) < 1) {
            System.out.println("small");
        }
    }
}
