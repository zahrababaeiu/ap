package ap.exercises.ex2;

import java.io.*;
import java.util.*;


public class Main_EX2_PM_2_4 {
    public static void main(String[] args) {
        System.out.println("Welcome to Pacman!");
        Scanner in = new Scanner(System.in);
        System.out.println("Do you want to play again? (Y/N)");
        char ch = in.next().charAt(0);

        if (ch == 'Y' || ch == 'y') {
            System.out.println("W=UP\tS=DOWN\tA=LEFT\tD=RIGHT");
            PacmanEngine.load(in);
        } else {
            System.out.println("W=UP\tS=DOWN\tA=LEFT\tD=RIGHT");
            System.out.print("enter n:");
            int n = in.nextInt();
            System.out.print("enter number of food: ");
            int c = in.nextInt();

            PacmanEngine engine = new PacmanEngine(n, c);
            engine.run(in);
        }
    }
}

class PacmanEngine {
    public final char Pacman = 'X';
    public final char Wall = '*';
    public final char Empty = ' ';
    public final char Food = '.';

    public char[][] board;
    public int x, y;
    public int score;
    public int food;
    public long startTime;

    public PacmanEngine(int n, int c) {
        board = new char[n + 2][n + 2];
        for (int i = 0; i < n + 2; i++) {
            for (int j = 0; j < n + 2; j++) {
                if (i == 0 || i == n + 1 || j == 0 || j == n + 1) {
                    board[i][j] = Wall;
                } else {
                    board[i][j] = Empty;
                }
            }
        }

        int center = (n + 2) / 2;
        x = center;
        y = center;
        board[y][x] = Pacman;

        Random rand = new Random();
        int count = 0;
        while (count < c) {
            int i = rand.nextInt(n) + 1;
            int j = rand.nextInt(n) + 1;
            if (board[i][j] == Empty) {
                board[i][j] = Food;
                count++;
            }
        }

        score = 0;
        food = c;
        startTime = System.currentTimeMillis();
    }

    public PacmanEngine(char[][] board, int x, int y, int score, int food) {
        this.board = board;
        this.x = x;
        this.y = y;
        this.score = score;
        this.food = food;
        this.startTime = System.currentTimeMillis();
    }

    public void run(Scanner in) {
        while (true) {
            printMatrix();
            printScore();

            char input = in.next().charAt(0);
            int dir = -1;
            switch (input) {
                case 'w':
                    dir = 0;
                    break;
                case 'd':
                    dir = 1;
                    break;
                case 's':
                    dir = 2;
                    break;
                case 'a':
                    dir = 3;
                    break;
                case 'q':
                    System.out.println("Do you want to save the game? (Y/N)");
                    char ch = in.next().charAt(0);
                    if (ch == 'Y' || ch == 'y') {
                        save();
                        System.out.println("Saved successfully!");
                    }
                    return;
            }

            if (dir != -1) {
                move(dir);
            }

            if (food == 0) {
                long finish = System.currentTimeMillis();
                long timeElapsed = finish - startTime;
                System.out.println("You win!");
                System.out.println("Score: " + score);
                System.out.println("Time: " + timeElapsed + "ms");
                return;
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
        }
    }

    public void move(int direction) {
        int newX = x;
        int newY = y;

        switch (direction) {
            case 0:
                newY--;
                break;
            case 1:
                newX++;
                break;
            case 2:
                newY++;
                break;
            case 3:
                newX--;
                break;
        }

        if (board[newY][newX] == Wall) {
            System.out.println("hitting the wall!");
            return;
        }

        if (board[newY][newX] == Food) {
            score++;
            food--;
        }

        board[y][x] = Empty;
        board[newY][newX] = Pacman;
        x = newX;
        y = newY;
    }

    public void printMatrix() {
        for (char[] row : board) {
            for (char ch : row) {
                System.out.print(ch + "\t");
            }
            System.out.println();
        }
    }

    public void printScore() {
        System.out.println("Score: " + score);
    }

    public void save() {
        try (FileWriter fw = new FileWriter("pacman.txt")) {
            int size = board.length;
            fw.write(size + "\n");
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    fw.write(board[i][j]);
                }
                fw.write("\n");
            }
            fw.write(x + "\n" + y + "\n" + score + "\n" + food + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void load(Scanner in) {
        try (FileReader fr = new FileReader("pacman.txt");
             Scanner sc = new Scanner(fr)) {

            int size = sc.nextInt();
            sc.nextLine();
            char[][] board = new char[size][size];
            int x = 0, y = 0, score = 0, food = 0;

            for (int i = 0; i < size; i++) {
                String line = sc.nextLine();
                for (int j = 0; j < size; j++) {
                    board[i][j] = line.charAt(j);
                }
            }

            x = sc.nextInt();
            y = sc.nextInt();
            score = sc.nextInt();
            food = sc.nextInt();

            PacmanEngine engine = new PacmanEngine(board, x, y, score, food);
            engine.run(in);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}