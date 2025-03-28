package ap.exercises.ex2;

import java.util.Random;
import java.util.Scanner;

public class Main_EX2_PM_2_2 {
    public static final char Pacman = 'X';
    public static final char Wall = '*';
    public static final char Empty = ' ';
    public static final char Food = '.';

    public static void main(String[] args) {
        System.out.println("welcome to Pacman!");
        System.out.println("W=UP\tS=DOWN\tA=LEFT\tD=RIGHT");
        System.out.println("enter n:");

        Scanner input = new Scanner(System.in);
        int n = input.nextInt();

        System.out.println("enter number of food:");
        int c = input.nextInt();

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
        int x = center, y = center, score = 0,food = c;
        Random rand = new Random();
        int count = 0;
        while (count < c) {
            int k = rand.nextInt(n) + 1;
            int l = rand.nextInt(n) + 1;
            if (myarray[k][l] == Empty) {
                myarray[k][l] = Food;
                count++;
            }
        }
        long start = System.currentTimeMillis();
        while (true) {
            for (int i = 0; i < n + 2; i++) {
                for (int j = 0; j < n + 2; j++) {
                    System.out.print(myarray[i][j] + "\t");
                }
                System.out.println();
            }
            System.out.println("Score: " + score);

            char select = input.next().charAt(0);
            int oldx = x, oldy = y;

            switch (select) {
                case 's':
                    y++;
                    break;
                case 'a':
                    x--;
                    break;
                case 'w':
                    y--;
                    break;
                case 'd':
                    x++;
                    break;
                case 'q':
                    System.out.println("exit");
                    input.close();
                    return;
            }
            if (myarray[y][x] == Wall) {
                System.out.println("hitting the game wall!");
                x = oldx;
                y = oldy;
            } else if (myarray[y][x] == Food) {
                myarray[oldy][oldx] = Empty;
                myarray[y][x] = Pacman;
                score++;
                food--;
            } else {
                myarray[oldy][oldx] = Empty;
                myarray[y][x] = Pacman;
            }
            if(food==0)
            {
                long finish = System.currentTimeMillis();
                long timeElapsed = finish - start;
                System.out.println("you win!");
                System.out.print("score: "+score+"\ntime: "+timeElapsed+"ms");
                input.close();
                return;
            }
            try {
                Thread.sleep(500);
            } catch (Exception e) {
            }
        }
    }
}



