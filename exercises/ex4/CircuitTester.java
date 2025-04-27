package ap.exercises.ex4;

public class CircuitTester {
    public static void main(String[] args) {
        for (int FirstSwitchState = 0; FirstSwitchState <= 1; FirstSwitchState++) {
            for (int SecondSwitchState = 0; SecondSwitchState <= 1; SecondSwitchState++) {
                LampControl lampControl = new LampControl(FirstSwitchState, SecondSwitchState);
                int acLampState = lampControl.getLampState();
                int exLampState;

                if (FirstSwitchState != SecondSwitchState) {
                    exLampState = 1;
                } else {
                    exLampState = 0;
                }

                String FirstSwitch;
                if (FirstSwitchState == 1) {
                    FirstSwitch = "UP";
                } else {
                    FirstSwitch = "DOWN";
                }

                String SecondSwitch;
                if (SecondSwitchState == 1) {
                    SecondSwitch = "UP";
                } else {
                    SecondSwitch = "DOWN";
                }

                String extLampState;
                if (exLampState == 1) {
                    extLampState = "ON";
                } else {
                    extLampState = "OFF";
                }

                String actLampState;
                if (acLampState == 1) {
                    actLampState = "ON";
                } else {
                    actLampState = "OFF";
                }

                System.out.println("First Switch: " + FirstSwitch);
                System.out.println("Second Switch: " + SecondSwitch);
                System.out.println("Expected Lamp State: " + extLampState);
                System.out.println("Actual Lamp State: " + actLampState);
                System.out.println("---------------------------------------");
            }
        }
    }
}
