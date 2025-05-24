package ap.exercises.ex4.part2;

import java.util.Scanner;

class Boat {
    private double x;
    private double y;
    private double direction;

    public Boat() {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getDirection() {
        return direction;
    }

    public void turn(double degrees) {
        direction += degrees;
    }

    public void move(double distance) {
        double Radians = Math.toRadians(direction);
        x += distance * Math.cos(Radians);
        y += distance * Math.sin(Radians);
    }
}

public class Main_EX4_P4_5 {
    public static void main(String[] args) {
        // Note: The game board and save/load logic are implemented using matrices.
        // This section only handles the movement logic. Movement methods are independent of board data structure.
        System.out.println("Select:");
        System.out.println("1)Up \t 2)Down \t 3)Left \t 4)Right \t 5)Turn");
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        Boat boat = new Boat();
        while (true) {
            switch (choice) {
                case 1:
                    int x = in.nextInt();
                    if (x >= 0) {
                        boat.move(x);
                    }
                    break;
                case 2:
                    int x2 = in.nextInt();
                    if (x2 < 0) {
                        boat.move(x2);
                    }
                    break;
                case 3:
                    int y = in.nextInt();
                    if (y >= 0) {
                        boat.move(y);
                    }
                    break;
                case 4:
                    int y2 = in.nextInt();
                    if (y2 < 0) {
                        boat.move(y2);
                    }
                    break;
                case 5:
                    int turn = in.nextInt();
                    boat.turn(turn);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
