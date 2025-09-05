package finalproject;

public class Loan {
    private Student student;
    private Book book;
    private Date borrowDate;
    private Date returnDate;
    private boolean returned;
    private Date actualReturnDate;
    private String librarianId;

    public Loan(Student student, Book book, Date borrowDate, Date returnDate, Date actualReturnDate, String librarianId) {
        this.student = student;
        this.book = book;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.returned = (actualReturnDate != null);
        this.actualReturnDate = actualReturnDate;
        this.librarianId = librarianId;
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

    public String getLibrarianId() {
        return librarianId;
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
        return student.getUsername() + "," +
                book.getTitle() + "," +
                borrowDate.toString() + "," +
                returnDate.toString() + "," +
                (returned ? "Yes" : "No") + "," +
                (actualReturnDate != null ? actualReturnDate.toString() : "null") + "," +
                librarianId;
    }

    public static Loan fromString(String line, Student student, Book book) {
        String[] parts = line.split(",");
        if (parts.length < 7) {
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

        String librarianId = parts[6];

        Loan loan = new Loan(student, book, borrowDate, returnDate, actualReturnDate, librarianId);
        loan.setReturned(returned);
        return loan;
    }
}


