package ap.exercises.ex4.part2;

import java.util.Arrays;

class Sequence {
    int[] values;

    public Sequence(int[] values) {
        this.values = values;
    }

    public Sequence(int size) {
        values = new int[size];
    }

    public void set(int i, int n) {
        values[i] = n;
    }

    public int get(int i) {
        return values[i];
    }

    public int size() {
        return values.length;
    }

    public boolean equals(Sequence other) {
        if (this.size() != other.size()) {
            return false;
        }

        for (int i = 0; i < this.size(); i++) {
            if (this.get(i) != other.get(i)) {
                return false;
            }
        }

        return true;
    }

    public boolean isPermutationOf(Sequence other) {
        if (this.size() != other.size()) {
            return false;
        }
        Arrays.sort(this.values);
        Arrays.sort(other.values);

        for (int i = 0; i < this.size(); i++) {
            if (this.get(i) != other.get(i)) {
                return false;
            }
        }
        return true;
    }

    public class Main_EX4_E7_14 {
        public static void main(String[] args) {
            Sequence s1 = new Sequence(3);
            int[] values1 = {1, 2, 3};
            for (int i = 0; i < values1.length; i++) {
                s1.set(i, values1[i]);
            }
            Sequence s2 = new Sequence(2);
            int[] values2 = {51, 12};
            for (int i = 0; i < values2.length; i++) {
                s2.set(i, values2[i]);
            }
            System.out.println("result=" + s1.isPermutationOf(s2));
        }
    }
}
