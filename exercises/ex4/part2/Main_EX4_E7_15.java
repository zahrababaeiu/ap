package ap.exercises.ex4.part2;

class Sequence2 {
    public final int maxsize = 10;
    private int[] values;

    public Sequence2(int[] values) {
        this.values = values;
    }

    public Sequence2(int size) {
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

    public Sequence2 sum(Sequence2 other) {
        int[] sum = new int[maxsize];
        for (int i = 0; i < maxsize; i++) {
            int value1;
            if (i < this.size()) {
                value1 = this.get(i);
            } else {
                value1 = 0;
            }

            int value2;
            if (i < other.size()) {
                value2 = other.get(i);
            } else {
                value2 = 0;
            }

            sum[i] = value1 + value2;
        }

        return new Sequence2(sum);
    }
}

public class Main_EX4_E7_15 {
    public static void main(String[] args) {
        Sequence2 s1 = new Sequence2(3);
        int[] values1 = {1, 2, 3};
        for (int i = 0; i < values1.length; i++) {
            s1.set(i, values1[i]);
        }
        Sequence2 s2 = new Sequence2(2);
        int[] values2 = {51, 12};
        for (int i = 0; i < values2.length; i++) {
            s2.set(i, values2[i]);
        }
        Sequence2 result = s1.sum(s2);
        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i) + "\t");
        }


    }
}
