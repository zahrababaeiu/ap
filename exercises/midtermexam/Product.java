package ap.exercises.midtermexam;

class Product {
    private int price;
    private String brand;

    public Product(int price, String brand) {
        this.price = price;
        this.brand = brand;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return getPrice() + brand;
    }
}
