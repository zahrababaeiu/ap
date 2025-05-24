package ap.exercises.ex4.part2;

import java.util.ArrayList;

class Person {
    private String name;
    private ArrayList<Person> know;

    public Person(String name) {
        this.name = name;
        this.know = new ArrayList<Person>();
    }

    public String getName() {
        return name;
    }

    public void befriend(Person p) {
        know.add(p);
    }

    public void unfriend(Person p) {
        know.remove(p);
    }

    public String getFriendNames() {
        return know.toString();
    }

    @Override
    public String toString() {
        return name;
    }
}

public class Main_EX4_P3_6 {
    public static void main(String[] args) {
        Person person = new Person("Sara");
        Person f1 = new Person("sama");
        person.befriend(f1);
        Person f2 = new Person("zahra");
        person.befriend(f2);
        Person f3 = new Person("fatemeh");
        person.befriend(f3);
        System.out.println("Person Name: " + person.getName());
        System.out.println("People she knows:" + person.getFriendNames());
        person.unfriend(f1);
        System.out.println("her friends:" + person.getFriendNames());
    }
}
