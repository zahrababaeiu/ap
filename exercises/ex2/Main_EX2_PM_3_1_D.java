package ap.exercises.ex2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Main_EX2_PM_3_1_D {
    public void main(String[] args) {
        PacmanGUI frame = new PacmanGUI();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    class PacmanGUI extends JFrame implements KeyListener {

        Point pacmanPoint = new Point();
        final int width = 500, height = 500, boxSize = 10;
        static int direction = 1;
        final int LEFT = 1, RIGHT = 2, TOP = 3, BOTTOM = 4;
        Point dotPoint = new Point();
        int score = 0;
        final int maxScore = 10;
        final long maxTime = 50000;
        long startTime;

        public PacmanGUI() {
            addKeyListener(this);
            pacmanPoint.setLocation((width / boxSize) / 2, (height / boxSize) / 2);
            getNewDotPointLocation();
            setSize(width, height);
            setTitle("Pacman Game");
            setLocationRelativeTo(null);
            setResizable(false);
            startTime = System.currentTimeMillis();
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            Graphics2D g2D = (Graphics2D) g;
            g.clearRect(0, 0, width, height);
            logic();
            drawPacman(g2D);
            drawDotPoint(g2D);
            drawScore(g2D);
            drawTime(g2D);
        }

        private void drawPacman(Graphics2D g2d) {
            g2d.setColor(Color.BLUE);
            g2d.fillRect(pacmanPoint.x * boxSize, pacmanPoint.y * boxSize, boxSize, boxSize);
        }

        private void drawDotPoint(Graphics2D g2d) {
            g2d.setColor(Color.RED);
            g2d.fillRect(dotPoint.x * boxSize, dotPoint.y * boxSize, boxSize, boxSize);
        }

        private void drawScore(Graphics2D g2d) {
            g2d.setColor(Color.BLACK);
            String s = "Score: " + score;
            g2d.drawString(s, 25, 50);
        }

        private void drawTime(Graphics2D g2d) {
            long elapsedTime = System.currentTimeMillis() - startTime;
            long remainingTime = maxTime - elapsedTime;
            g2d.setColor(Color.BLACK);
            String timeText = "Time Left: " + remainingTime / 1000 + "s";
            g2d.drawString(timeText, 25, 75);
        }

        private void logic() {
            if (score >= maxScore) {
                JOptionPane.showMessageDialog(this, "Maximum Score Reached!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }

            long elapsedTime = System.currentTimeMillis() - startTime;
            if (elapsedTime >= maxTime) {
                JOptionPane.showMessageDialog(this, "Time's Up!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }

            if (dotPoint.x == pacmanPoint.x && dotPoint.y == pacmanPoint.y) {
                score++;
                System.out.println("Score: " + score);
                getNewDotPointLocation();
            }

            movePacman();
        }

        private void movePacman() {
            int xMovement = 1;
            int yMovement = 0;
            switch (direction) {
                case LEFT:
                    xMovement = -1;
                    yMovement = 0;
                    break;
                case RIGHT:
                    xMovement = 1;
                    yMovement = 0;
                    break;
                case TOP:
                    xMovement = 0;
                    yMovement = -1;
                    break;
                case BOTTOM:
                    xMovement = 0;
                    yMovement = 1;
                    break;
            }
            pacmanPoint.setLocation(pacmanPoint.x + xMovement, pacmanPoint.y + yMovement);
            handleCrossBorder();
        }

        private void getNewDotPointLocation() {
            Random rand = new Random();
            int delta = boxSize * 2;
            dotPoint.setLocation(rand.nextInt(width / boxSize - 2 * delta) + delta,
                    rand.nextInt(height / boxSize - 2 * delta) + delta);
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            char keyChar = e.getKeyChar();
            if (keyChar == 'q' || keyChar == 'Q') {
                System.exit(0);
            }
            if (e.getKeyCode() == KeyEvent.VK_UP && direction != BOTTOM)
                direction = TOP;
            else if (e.getKeyCode() == KeyEvent.VK_DOWN && direction != TOP)
                direction = BOTTOM;
            else if (e.getKeyCode() == KeyEvent.VK_LEFT && direction != RIGHT)
                direction = LEFT;
            else if (e.getKeyCode() == KeyEvent.VK_RIGHT && direction != LEFT)
                direction = RIGHT;
            else if (e.getKeyCode() == KeyEvent.VK_P)
                direction = 0;

            repaint();
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

        private void handleCrossBorder() {
            if (pacmanPoint.x < 0) {
                pacmanPoint.x = 0;
            } else if (pacmanPoint.x >= width / boxSize) {
                pacmanPoint.x = (width / boxSize) - 1;
            }
            if (pacmanPoint.y < 0) {
                pacmanPoint.y = 0;
            } else if (pacmanPoint.y >= height / boxSize) {
                pacmanPoint.y = (height / boxSize) - 1;
            }
        }
    }
}
