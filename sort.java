import java.util.Arrays;
import java.util.Scanner;

public class sort {
    public static void main(String[] args) {
        String[]arr = new String[3];
        System.out.println("enter strings:");
        Scanner in = new Scanner(System.in);
        for (int i=0;i<3;i++) {
            arr[i] = in.nextLine();
        }
        Arrays.sort(arr);
        System.out.println("=================");
        System.out.println("sorted array:");
        for (int i=0;i<3;i++) {
            System.out.println(arr[i]);
        }
    }
}
