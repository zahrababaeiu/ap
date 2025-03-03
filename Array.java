import java.util.Scanner;

public class Array {
    public static void main(String[] args) {
        System.out.println("enter n");
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        System.out.println("enter array elements");
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        while (true) {
            System.out.println("1)max & min");
            System.out.println("2)number of even and odd");
            System.out.println("3)cumulative totals");
            System.out.println("4)all adjacent duplicates");
            System.out.println("5)Exit");
            System.out.println("select(1-5)");
            int select = in.nextInt();
            switch (select) {
                case 1:
                    System.out.println("maximum is:" + findMax(arr, n));
                    System.out.println("minimum is:" + findMin(arr, n));
                    break;
                case 2:
                    int countE = 0, countO = 0;
                    for (int i = 0; i < n; i++) {
                        if (arr[i] % 2 == 0) {
                            countE++;
                        } else {
                            countO++;
                        }
                    }
                    System.out.println("number of even elements :" + countE);
                    System.out.println("number of odd elements :" + countO);
                    break;
                case 3:
                    int[] cum = new int[n];
                    int sum = 0;
                    for (int i = 0; i < n; i++) {
                        sum += arr[i];
                        cum[i] = sum;
                    }
                    System.out.println("cumulative total :");
                    for (int i = 0; i < n; i++) {
                        System.out.println(cum[i]);
                    }
                    break;
                case 4:
                    int[] save = new int[n];
                    for (int i = 0; i < n - 1; i++) {
                        if (arr[i] == arr[i + 1]) {
                            save[i] = arr[i];
                        }
                    }
                    for (int i = 0; i < n; i++) {
                        System.out.println(save[i]);
                    }
                    break;
                case 5:
                    in.close();
                    return;
                default:
                    System.out.println("Invalid selection");
                    break;

            }
        }
    }

    public static int findMax(int[] arr, int n) {
        int max = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    public static int findMin(int[] arr, int n) {
        int min = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        return min;
    }

}