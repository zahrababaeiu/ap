package ap.exercises.ex2;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileReader;

class save {
    static char[][] board;
    static int score;

    public save(char[][] board, int score) {
        this.board = board;
        this.score = score;
    }
}

public class Main_EX2_PM_2_3 {
    public static final char Pacman = 'X';
    public static final char Wall = '*';
    public static final char Empty = ' ';
    public static final char Food = '.';

    public static void main(String[] args) {
        System.out.println("Welcome to Pacman!");
        Scanner in= new Scanner(System.in);
        System.out.println("Do you want to play again? (Y/N)");
        char ch = in.next().charAt(0);

        if (ch == 'Y' || ch == 'y') {
            System.out.println("W=UP\tS=DOWN\tA=LEFT\tD=RIGHT");
            load(in);
        } else {
            System.out.println("W=UP\tS=DOWN\tA=LEFT\tD=RIGHT");
            System.out.println("enter n:");
            int n = in.nextInt();
            System.out.println("enter number of food:");
            int c = in.nextInt();
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
            int x = center, y = center, score = 0, food = c;
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
            run(myarray, x, y, score, food, in);
        }
    }

    public static void run(char[][] board, int x, int y, int score, int food, Scanner in) {
        long start = System.currentTimeMillis();
        while (true) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    System.out.print(board[i][j] + "\t");
                }
                System.out.println();
            }
            System.out.println("Score: " + score);

            char select = in.next().charAt(0);
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
                case 'q': // Save game
                    save game = new save(board, score);
                    System.out.println("Do you want to save the game (Y/N)?");
                    char choice = in.next().charAt(0);
                    if (choice == 'y' || choice == 'Y') {
                        Save(game);
                        System.out.println("saved successfully!");
                        return;
                    } else if (choice == 'n' || choice == 'N') {
                        System.out.println("EXIT");
                        return;
                    } else {
                        System.out.println("Invalid choice");
                    }
            }

            if (board[y][x] == Wall) {
                System.out.println("hitting the game wall!");
                x = oldx;
                y = oldy;
            } else if (board[y][x] == Food) {
                board[oldy][oldx] = Empty;
                board[y][x] = Pacman;
                score++;
                food--;
            } else {
                board[oldy][oldx] = Empty;
                board[y][x] = Pacman;
            }

            if (food == 0) {
                long finish = System.currentTimeMillis();
                long timeElapsed = finish - start;
                System.out.println("You win!");
                System.out.println("Score: " + score);
                System.out.println("Time: " + timeElapsed + "ms");
                return;
            }
            try {
                Thread.sleep(500);
            } catch (Exception e) {
            }
        }
    }

    public static void Save(save game) {
        try {
            FileWriter fw = new FileWriter("pacman.txt");
            int size = game.board.length;
            fw.write(size + "\n");
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    fw.write(game.board[i][j]);
                }
                fw.write("\n");
            }
            fw.write(game.score + "\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void load(Scanner input) {
        try {
            FileReader fr = new FileReader("pacman.txt");
            Scanner sc = new Scanner(fr);

            if (!sc.hasNextInt()) {
                System.out.println("Not found.");
                sc.close();
                fr.close();
                return;
            }
            //Read
            int size = sc.nextInt();
            sc.nextLine();
            char[][] myboard = new char[size][size];
            int pX = 0, pY = 0, food = 0;
            for (int i = 0; i < size; i++) {
                String line = sc.nextLine();
                for (int j = 0; j < size; j++) {
                    myboard[i][j] = line.charAt(j);
                    if (myboard[i][j] == Pacman) {
                        pX = j;
                        pY = i;
                    }
                    else if (myboard[i][j] == Food) {
                        food++;
                    }
                }
            }
            int score = sc.nextInt();
            sc.close();
            fr.close();
            run(myboard, pX, pY, score, food, input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
