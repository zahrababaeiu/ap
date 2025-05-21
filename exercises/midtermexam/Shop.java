package ap.exercises.midtermexam;

import java.util.ArrayList;

public class Shop {
    private ArrayList<Product> products;

    public Shop(int price, String brand) {
        this.products = new ArrayList<>();
    }

    public Shop() {
    }

    public void add(Product product) {
        products.add(product);
    }

    @Override
    public String toString() {
        return "products=" + products;
    }

    public void show() {
        for(Product product : products) {
            System.out.println(product);
        }
    }
}
