package ap.exercises.ex4;

class Employee {
    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public void raiseSalary(double byPercent) {
        salary = salary + (salary * byPercent / 100);
    }
}

public class EmployeeTester {
    public static void main(String[] args) {

        Employee harry = new Employee("Harry", 5000);
        System.out.println("Name: " + harry.getName());
        System.out.println("Salary: " + harry.getSalary());
        harry.raiseSalary(10);
        System.out.println("New Salary: " + harry.getSalary());
    }
}
