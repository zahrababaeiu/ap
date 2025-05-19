package ap.mid_project;

public class Request {
    private String bookTitle;
    private String bookAuthor;
    private int stuId;

    public Request(String bookTitle, String bookAuthor, int stuId) {
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.stuId = stuId;
    }

    @Override
    public String toString() {
        return bookTitle +" "+ bookAuthor+" "+stuId;
    }

}
