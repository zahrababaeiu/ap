package ap.mid_project;

public class Book {
    private String title;
    private String author;
    private int year;
    private int pages;

    public Book(String title, String author, int year, int pages) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.pages = pages;
    }

    public Book() {

    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return title + "," + author + "," + year + ", pages: " + pages;
    }

    public int getPages() {
        return pages;
    }

    public int getYear() {
        return year;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public void stringForm(String line) {
        String[] parts = line.split(",");
        if (parts.length < 4) {
            System.out.println("Invalid book line: " + line);
            return;
        }

        this.title = parts[0].trim();
        this.author = parts[1].trim();

        try {
            this.year = Integer.parseInt(parts[2].trim());
            String pagesPart = parts[3].trim();
            if (pagesPart.startsWith("pages:")) {
                pagesPart = pagesPart.substring(6).trim();
            }
            this.pages = Integer.parseInt(pagesPart);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

}


