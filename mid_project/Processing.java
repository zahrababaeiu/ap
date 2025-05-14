package ap.mid_project;

import java.util.Scanner;

public class Processing {
    private Scanner scanner;

    public  Processing() {
       this.scanner = new Scanner(System.in);
    }

    public String getString(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

    public int getInt(String message) {
        while (true) {
            System.out.println(message);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            }
        }
    }

    public void close() {
        scanner.close();
    }
}
