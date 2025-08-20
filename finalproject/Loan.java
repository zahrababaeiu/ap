package finalproject;

public class Loan {
    private Student student;
    private Book book;
    private Date borrowDate;
    private Date returnDate;
    private boolean returned;
    private Date actualReturnDate;

    public Loan(Student student, Book book, Date borrowDate, Date returnDate, Date actualReturnDate) {
        this.student = student;
        this.book = book;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.returned = false;
        this.actualReturnDate = actualReturnDate;
    }


    public Book getBook() {
        return book;
    }

    public Student getStudent() {
        return student;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public void setActualReturnDate(Date actualReturnDate) {
        this.actualReturnDate = actualReturnDate;
    }

    public Date getActualReturnDate() {
        return actualReturnDate;
    }

    @Override
    public String toString() {
        String result;
        if (returned) {
            result = "Yes";
        } else {
            result = "No";
        }
        return "Book: " + book.getTitle() +
                ", Student: " + student.getUsername() +
                ", Borrow Date: " + borrowDate +
                ", Return Date: " + returnDate +
                ", Return status : " + result +
                ", Actual Return Date : " + actualReturnDate;
    }
}


