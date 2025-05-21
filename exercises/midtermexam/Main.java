package ap.exercises.midtermexam;

public class Main {
    public static void main(String[] args) {
        Laptop laptop1 = new Laptop(100, "sumsung", "black", "laptop1");
        Laptop laptop2 = new Laptop(200, "Apple", "red", "laptop2");

        Case case1 = new Case(150, "case1", "sumsung", 1, "10V");
        Case case2 = new Case(170, "case2", "sumsung", 2, "50V");

        Shop shop = new Shop();
        shop.add(laptop1);
        shop.add(laptop2);
        shop.add(case1);
        shop.add(case2);

        shop.show();


    }
}
