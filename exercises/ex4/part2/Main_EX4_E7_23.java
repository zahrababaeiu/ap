package ap.exercises.ex4.part2;

import java.util.ArrayList;
import java.util.Scanner;

class Sequence3 {
    private ArrayList<Integer> values;

    public Sequence3() {
        values = new ArrayList<Integer>();
    }

    public void add(int n) {
        values.add(n);
    }

    @Override
    public String toString() {
        return values.toString();
    }

    public Sequence3 append(Sequence3 other) {
        Sequence3 app = new Sequence3();

        for (int val : this.values) {
            app.add(val);
        }

        for (int val : other.values) {
            app.add(val);
        }

        return app;
    }
}

public class Main_EX4_E7_23 {
    public static void main(String[] args) {
        Sequence3 s1 = new Sequence3();
        System.out.println("Enter count of sequence1:");
        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();
        System.out.println("Enter items");

        for (int i = 0; i < count; i++) {
            int items = sc.nextInt();
            s1.add(items);
        }
        Sequence3 s2 = new Sequence3();
        System.out.println("Enter count of sequence2:");
        int count2 = sc.nextInt();
        System.out.println("Enter items");
        for (int i = 0; i < count2; i++) {
            int items = sc.nextInt();
            s2.add(items);
        }
        Sequence3 s3 = s1.append(s2);
        System.out.println(s3);
    }
}
