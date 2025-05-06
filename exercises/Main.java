package ap.exercises;

import java.util.ArrayList;

class Pen {
    private String color;
    private double price;
    private String brand;

    public Pen(String color, double price, String brand) {
        this.color = color;
        this.price = price;
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }
}

class Book {
    private String title;
    private double price;

    public Book(String title, double price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

public class Main {
    public static void main(String[] args) {
        ArrayList<Pen> pen = new ArrayList<>();
        ArrayList<Book> book = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            pen.add(new Pen("color" + (i + 1), 100 + i, "brand" + (i + 1)));
            book.add(new Book("title" + (i + 1), 100 + i));
        }
        for (Book b : book) {
            System.out.println(b.getTitle() + "\nbefore discount:" + b.getPrice() + "\tafter discount:" + discount(b.getPrice()));
            System.out.println("-------");
        }
        System.out.println("---------------------------");
        for (Pen p : pen) {
            System.out.println(p.getColor() + " " + p.getBrand() + "\nbefore discount:" + p.getPrice() + "\tafter discount:" + discount(p.getPrice()));
            System.out.println("-------");
        }

    }

    public static double discount(double price) {
        price -= (price * 0.1);
        return price;

    }
}

