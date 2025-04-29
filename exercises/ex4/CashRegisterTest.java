package ap.exercises.ex4;

class CashRegister {
    private double total;
    private int Count;
    private String prices;

    public CashRegister() {
        this.total = 0;
        this.Count = 0;
        this.prices = "";
    }

    public void addItem(double price) {
        total = total + price;
        Count++;
        prices = prices.concat(String.valueOf(price)).concat("\n");
    }

    public void printReceipt() {
        System.out.println("Receipt:" + prices);
        System.out.println("Total: " + total);
    }
}

public class CashRegisterTest {
    public static void main(String[] args) {
        CashRegister cr = new CashRegister();
        //TEST
        cr.addItem(108.5);
        cr.addItem(4.95);
        cr.addItem(5.25);
        cr.printReceipt();
    }
}
