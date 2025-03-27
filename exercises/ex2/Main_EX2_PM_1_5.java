package ap.exercises.ex2;

import java.util.Random;
import java.util.Scanner;

public class Main_EX2_PM_1_5 {
    public static final char Pacman = 'X';
    public static final char Wall = '*';
    public static final char Empty = ' ';

    public static void main(String[] args) {
        System.out.println("enter n:");
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        input.close();

        char[][] myarray = new char[n + 2][n + 2];
        for (int i = 0; i < n + 2; i++) {
            for (int j = 0; j < n + 2; j++) {
                if (i == 0 || i == n + 1 || j == 0 || j == n + 1) {
                    myarray[i][j] = Wall;
                } else {
                    myarray[i][j] = Empty;
                }
            }
        }

        int center = (myarray.length) / 2;
        myarray[center][center] = Pacman;
        int x = center, y = center;

        while (true) {
            for (int i = 0; i < n + 2; i++) {
                for (int j = 0; j < n + 2; j++) {
                    System.out.print(myarray[i][j] + "\t");
                }
                System.out.println();
            }
            System.out.println();

            Random rand = new Random();
            int randMove = rand.nextInt(4);
            int oldx= x, oldy = y;

            switch (randMove) {
                case 0:
                    y++;
                    break;
                case 1:
                    x--;
                    break;
                case 2:
                    y--;
                    break;
                case 3:
                    x++;
                    break;
            }
            if (myarray[x][y] == Wall) {
                System.out.println("hitting the game wall!");
                x = oldx;
                y = oldy;
            } else {
                myarray[oldx][oldy] = Empty;
                myarray[x][y] = Pacman;
            }
            try {
                Thread.sleep(1000);
            } catch (Exception e) {}
        }
    }
}
