package ap.exercises.midtermexam;

class Case extends Product {
    private String power;
    private int size;

    public Case(int price, String name, String brand, int size, String power) {
        super(price, brand);
        this.size = size;
        this.power = power;
    }

    @Override
    public String toString() {
        return super.toString() + "\tSize: " + size + "\tPower: " + power;
    }
}
