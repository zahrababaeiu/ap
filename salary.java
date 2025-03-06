package ap;

import java.util.Scanner;

public class salary {
    public static void main(String[] args) {
        double salary, tax = 0;
        System.out.println("enter the salary");
        Scanner in = new Scanner(System.in);
        salary = in.nextDouble();
        in.close();
        if (salary > 500000) {
            tax += (salary - 500000) * 0.06;
            salary = 500000;
        } if (salary > 250000 && salary <= 500000) {
            tax += (salary - 250000) * 0.05;
            salary = 250000;
        }  if (salary > 100000 && salary <= 250000) {
            tax += (salary - 100000) * 0.04;
            salary = 100000;
        } if (salary > 75000 && salary <= 100000) {
            tax += (salary - 75000) * 0.03;
            salary = 75000;
        } if (salary > 50000 && salary <= 75000) {
            tax += (salary - 50000) * 0.02;
            salary = 50000;
        }  if (salary > 0 && salary <= 50000) {
            tax += salary * 0.01;
        }
        System.out.println("tax is " + tax);
    }
}
