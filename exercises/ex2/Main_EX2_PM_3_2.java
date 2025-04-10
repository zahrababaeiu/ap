package ap.exercises.ex2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main_EX2_PM_3_2 {
    public static void main(String[] args) {
        new PacmanGUI();
    }
}

class PacmanGUI extends JFrame implements KeyListener {
    public final int n = 10;
    public final char Pacman = 'X';
    public final char Wall = '*';
    public final char Empty = ' ';

    public char[][] board;
    public int x, y;

    public PacmanGUI() {
        setTitle("Pacman GUI");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
        initGame();
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                requestFocusInWindow();
            }
        });
    }

    private void initGame() {
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
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        int cellSize = 30;
        for (int i = 0; i < n + 2; i++) {
            for (int j = 0; j < n + 2; j++) {
                int drawX = j * cellSize + 50;
                int drawY = i * cellSize + 50;

                if (board[i][j] == Wall) {
                    g.setColor(Color.DARK_GRAY);
                    g.fillRect(drawX, drawY, cellSize, cellSize);
                } else if (board[i][j] == Pacman) {
                    g.setColor(Color.BLUE);
                    g.fillOval(drawX, drawY, cellSize, cellSize);
                } else {
                    g.setColor(Color.WHITE);
                    g.fillRect(drawX, drawY, cellSize, cellSize);
                }

                g.setColor(Color.BLACK);
                g.drawRect(drawX, drawY, cellSize, cellSize);
            }
        }
    }

    public void move(int dx, int dy) {
        int newX = x + dx;
        int newY = y + dy;

        if (board[newY][newX] == Wall) {
            System.out.println("hitting the wall!");
            return;
        }

        board[y][x] = Empty;
        x = newX;
        y = newY;
        board[y][x] = Pacman;

        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        char key = Character.toLowerCase(e.getKeyChar());

        switch (key) {
            case 'w':
                move(0, -1);
                break;
            case 'a':
                move(-1, 0);
                break;
            case 's':
                move(0, 1);
                break;
            case 'd':
                move(1, 0);
                break;
            case 'q':
                System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
