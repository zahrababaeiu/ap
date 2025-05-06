package ap.exercises;
import java.util.ArrayList;

class Pen{
    private String color;
    private int price;
    private String brand;

    public Pen(String color, int price, String brand){
        this.color = color;
        this.price = price;
        this.brand = brand;
    }
    public String getColor(){
        return color;
    }
    public int getPrice(){
        return price;
    }
    public String getBrand(){
        return brand;
    }
}
class Book{
    private String title;
    private int price;

    public Book(String title, int price){
        this.title = title;
        this.price = price;
    }
    public String getTitle(){
        return title;
    }
    public int getPrice(){
        return price;
    }
}

public class Main {
    public static void main(String[] args) {
        ArrayList<Pen> pen = new ArrayList<>();
        ArrayList<Book> book = new ArrayList<>();
        for(int i = 0; i < 2; i++){
            pen.add(new Pen("color"+(i+1),100+i,"brand"+(i+1)));
            book.add(new Book("title"+(i+1),100+i));
        }
        for(Book b : book){
            System.out.println(b.getTitle()+" "+b.getPrice());
        }
        System.out.println("-------------");
        for(Pen p : pen){
            System.out.println(p.getColor()+" "+p.getPrice()+" "+p.getBrand());
        }

    }
}

