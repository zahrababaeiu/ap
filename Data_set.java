package ap;

import java.util.ArrayList;
import java.util.Scanner;

public class Data_set {
    public static void main(String[] args) {
        ArrayList<Double> arr = new ArrayList<Double>();
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("1)add");
            System.out.println("2)avarage");
            System.out.println("3)max");
            System.out.println("4)min");
            System.out.println("5)exit");
            System.out.println("select(1-5)");
            int select = in.nextInt();
            switch (select) {
                case 1:
                    Add(arr);
                    System.out.println("Added Successfully");
                    break;
                case 2:
                    System.out.println("avrage " + getAverage(arr));
                    break;
                case 3:
                    System.out.println("max " + getMax(arr));
                    break;
                case 4:
                    System.out.println("min " + getMin(arr));
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid selection");

            }
        }
    }

    public static void Add(ArrayList<Double> arr) {
        System.out.println("enter the number");
        Scanner in = new Scanner(System.in);
        double num = in.nextDouble();
        arr.add(num);
    }

    public static double getAverage(ArrayList<Double> arr) {
        double sum = 0, avg = 0;
        for (int i = 0; i < arr.size(); i++) {
            sum += arr.get(i);
        }
        avg = sum / arr.size();
        return avg;
    }

    public static double getMax(ArrayList<Double> arr) {
        double max = arr.get(0);
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) > max) {
                max = arr.get(i);
            }
        }
        return max;
    }

    public static double getMin(ArrayList<Double> arr) {
        double min = arr.get(0);
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) < min)
                min = arr.get(i);
        }
        return min;
    }
}
