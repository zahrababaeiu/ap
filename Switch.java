import java.util.Scanner;

public class Switch {
    public static void main(String[] args) {
        int select;
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("1)sum-even(2-100)");
            System.out.println("2)sum-square(1-100)");
            System.out.println("3)square(2-220)");
            System.out.println("4)sum-odd(a,b)");
            System.out.println("5)sum-odd-digit");
            System.out.println("6)Exit");
            System.out.println("select(1-6)");
            select = in.nextInt();
            int sum = 0;
            switch (select) {
                case 1:
                    for (int i = 2; i <= 100; i = i + 2) {
                        sum += i;
                    }
                    System.out.println("sum is :" + sum);
                    break;

                case 2:
                    for (int i = 1; i <= 100; i++) {
                        sum += (i * i);
                    }
                    System.out.println("sum is :" + sum);
                    break;

                case 3:
                    for (int i = 2; i <= 220; i++) {
                        System.out.println(i*i);
                    }
                    break;

                case 4:
                    int a, b;
                    System.out.println("enter a & b");
                    a = in.nextInt();
                    b = in.nextInt();
                    for (int i = a; i <= b; i++) {
                        if (i % 2 == 1) {
                            sum += i;
                        }
                    }
                    System.out.println("sum is :" + sum);
                    break;

                case 5:
                    int num, temp;
                    System.out.print("enter number:");
                    num = in.nextInt();
                    while (num != 0) {
                        temp = num % 10;
                        if (temp % 2 == 1) {
                            sum += temp;
                        }
                        num /= 10;
                    }
                    System.out.println("Sum of odd digits: " + sum);
                    break;

                case 6:
                    in.close();
                    return;

                default:
                    System.out.println("Invalid selection");
                    break;
            }
        }

    }

}
