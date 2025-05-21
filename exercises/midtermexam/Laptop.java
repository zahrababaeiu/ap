package ap.exercises.midtermexam;

public class Laptop extends Product{
    String color;
    String name;

    public Laptop(int price, String brand, String color, String name) {
        super(price, brand);
        this.color = color;
        this.name = name;
    }

    @Override
    public String toString() {
        return super.toString() + "\tColor: " + color + "\tName: " + name;
    }
}

