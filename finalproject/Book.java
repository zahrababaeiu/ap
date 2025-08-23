package finalproject;

public class Book {
    private String title;
    private String author;
    private int year;
    private boolean loaned;

    public Book(String title, String author, int year, boolean loaned) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.loaned = loaned;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isLoaned() {
        return loaned;
    }

    @Override
    public String toString() {
        return title + " , " + author + " , " + year;
    }

    public static Book fromString(String line) {
        String[] parts = line.split(",");
        if (parts.length != 4) {
            return null;
        }
        String title = parts[0].trim();
        String author = parts[1].trim();
        int year = Integer.parseInt(parts[2].trim());
        boolean loaned = Boolean.parseBoolean(parts[3].trim());
        return new Book(title, author, year, loaned);
    }

    public void setLoaned(boolean Loaned) {
        this.loaned = Loaned;
    }
}
