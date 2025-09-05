package finalproject;

public class Book {
    private String title;
    private String author;
    private int year;
    private boolean loaned;
    private String registeredBy;

    public Book(String title, String author, int year, boolean loaned, String registeredBy) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.loaned = loaned;
        this.registeredBy = registeredBy;
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

    public void setLoaned(boolean loaned) {
        this.loaned = loaned;
    }

    public String getRegisteredBy() {
        return registeredBy;
    }

    public void setRegisteredBy(String registeredBy) {
        this.registeredBy = registeredBy;
    }

    @Override
    public String toString() {
        return title + " , " + author + " , " + year + " , Loaned: " + loaned + " , Registered by: " + registeredBy;
    }

    public String toFileString() {
        return title + "," + author + "," + year + "," + loaned + "," + registeredBy;
    }

    public static Book fromString(String line) {
        String[] parts = line.split(",");
        if (parts.length != 5) {
            return null;
        }
        String title = parts[0].trim();
        String author = parts[1].trim();
        int year = Integer.parseInt(parts[2].trim());
        boolean loaned = Boolean.parseBoolean(parts[3].trim());
        String registeredBy = parts[4].trim();
        return new Book(title, author, year, loaned, registeredBy);
    }
}