package ap.exercises.ex4;

import java.util.Scanner;

class LampControl {
    private int FirstSwitchState = 0;
    private int SecondSwitchState = 0;

    public LampControl(int FirstSwitchState, int SecondSwitchState) {
        this.FirstSwitchState = FirstSwitchState;
        this.SecondSwitchState = SecondSwitchState;
    }

    public int getFirstSwitchState() {
        return FirstSwitchState;
    }

    public int getSecondSwitchState() {
        return SecondSwitchState;
    }

    public void toggleFirstSwitch() {
        if (FirstSwitchState == 0) {
            FirstSwitchState = 1;
        } else {
            FirstSwitchState = 0;
        }
    }

    public void toggleSecondSwitch() {
        if (SecondSwitchState == 0) {
            SecondSwitchState = 1;
        } else {
            SecondSwitchState = 0;
        }
    }

    public int getLampState() {
        if (FirstSwitchState != SecondSwitchState) {
            return 1;
        } else {
            return 0;
        }
    }
}

public class Main_EX4_E3_4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("State (1=ON , 0=OFF)\n-----------------");
        System.out.print("Enter switch 1 state: ");
        int switch1 = input.nextInt();
        System.out.print("Enter switch 2 state: ");
        int switch2 = input.nextInt();

        if ((switch1 != 0 && switch1 != 1) || (switch2 != 0 && switch2 != 1)) {
            System.out.println("Invalid");
            input.close();
            return;
        }

        LampControl lampControl = new LampControl(switch1, switch2);

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
                    lampControl.toggleFirstSwitch();
                    System.out.println("Switch 1 toggled.");
                    break;
                case 2:
                    lampControl.toggleSecondSwitch();
                    System.out.println("Switch 2 toggled.");
                    break;
                case 3:
                    int currentLampState = lampControl.getLampState();
                    if (currentLampState == 0) {
                        System.out.println("Lamp state: OFF");
                    } else if (currentLampState == 1) {
                        System.out.println("Lamp state: ON");
                    }
                    break;
                case 4:
                    if (lampControl.getFirstSwitchState() == 1) {
                        System.out.println("Switch 1 state: UP");
                    } else {
                        System.out.println("Switch 1 state: DOWN");
                    }

                    if (lampControl.getSecondSwitchState() == 1) {
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

