package ap.exercises.ex4;

import java.util.Scanner;

class Circuit {
    private int[] switches;

    public Circuit(int firstSwitchState, int secondSwitchState) {
        switches = new int[2];
        switches[0] = firstSwitchState;
        switches[1] = secondSwitchState;
    }

    public int getSwitchState(int switchNum) {
        if (switchNum == 1 || switchNum == 2) {
            return switches[switchNum - 1];
        } else {
            System.out.println("Invalid");
            return 2;
        }
    }

    public int getLampState() {
        if (switches[0] != switches[1]) {
            return 1;
        } else {
            return 0;
        }
    }

    public void toggleSwitch(int switchNum) {
        if (switchNum == 1 || switchNum == 2) {
            switches[switchNum - 1] = 1 - switches[switchNum - 1];
        } else {
            System.out.println("Invalid");
        }
    }
}

public class CircuitMain {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("State (1=ON , 0=OFF)\n-----------------");
        System.out.print("Enter switch 1 state: ");
        int switch1 = input.nextInt();
        System.out.print("Enter switch 2 state: ");
        int switch2 = input.nextInt();

        if ((switch1 != 0 && switch1 != 1) || (switch2 != 0 && switch2 != 1)) {
            System.out.println("Invalid input");
            input.close();
            return;
        }

        Circuit circuit = new Circuit(switch1, switch2);

        while (true) {
            System.out.println("\n-------------------------");
            System.out.println("1) Toggle switch 1");
            System.out.println("2) Toggle switch 2");
            System.out.println("3) Show lamp state");
            System.out.println("4) Show switch states");
            System.out.println("5) Exit");
            System.out.print("Select (1-5): ");
            int select = input.nextInt();

            switch (select) {
                case 1:
                    circuit.toggleSwitch(1);
                    System.out.println("Switch 1 toggled.");
                    break;
                case 2:
                    circuit.toggleSwitch(2);
                    System.out.println("Switch 2 toggled.");
                    break;
                case 3:
                    int currentLampState = circuit.getLampState();
                    if (currentLampState == 0) {
                        System.out.println("Lamp state: OFF");
                    } else {
                        System.out.println("Lamp state: ON");
                    }
                    break;
                case 4:
                    if (circuit.getSwitchState(1) == 1) {
                        System.out.println("Switch 1 state: UP");
                    } else {
                        System.out.println("Switch 1 state: DOWN");
                    }

                    if (circuit.getSwitchState(2) == 1) {
                        System.out.println("Switch 2 state: UP");
                    } else {
                        System.out.println("Switch 2 state: DOWN");
                    }
                    break;
                case 5:
                    input.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid selection");
            }
        }
    }
}
