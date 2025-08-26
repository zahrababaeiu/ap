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

    public String toFileString() {
        String result;
        if (returned) {
            result = "Yes";
        } else {
            result = "No";
        }
        return student.getUsername() + "," +
                book.getTitle() + "," +
                borrowDate.toString() + "," +
                returnDate.toString() + "," +
                returned + result + "," +
                (actualReturnDate != null ? actualReturnDate.toString() : "null");
    }

    public static Loan fromString(String line, Student student, Book book) {
        String[] parts = line.split(",");
        if (parts.length < 6) {
            return null;
        }

        String[] borrowParts = parts[2].split("/");
        Date borrowDate = new Date(
                Integer.parseInt(borrowParts[0]),
                Integer.parseInt(borrowParts[1]),
                Integer.parseInt(borrowParts[2])
        );

        String[] returnParts = parts[3].split("/");
        Date returnDate = new Date(
                Integer.parseInt(returnParts[0]),
                Integer.parseInt(returnParts[1]),
                Integer.parseInt(returnParts[2])
        );

        boolean returned = parts[4].equalsIgnoreCase("Yes");

        Date actualReturnDate = null;
        if (!parts[5].equals("null")) {
            String[] actualParts = parts[5].split("/");
            actualReturnDate = new Date(
                    Integer.parseInt(actualParts[0]),
                    Integer.parseInt(actualParts[1]),
                    Integer.parseInt(actualParts[2])
            );
        }

        Loan loan = new Loan(student, book, borrowDate, returnDate, actualReturnDate);
        loan.setReturned(returned);
        return loan;
    }
}


