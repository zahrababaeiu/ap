package ap.mid_project;

public class Loan {
    private Book book;
    private Student student;
    private Loaner loaner1;
    private Loaner loaner2;
    private Date dateLoan;
    private Date dateReceive;
    private Date dateRealReceive;

    public Loan(Book book, Student student, Loaner loaner1, Loaner loaner2, Person librarian) {
        this.book = book;
        this.student = student;
        this.loaner1 = loaner1;
        this.loaner2 = loaner2;
        this.dateLoan = dateLoan;
        this.dateReceive = dateReceive;
        this.dateRealReceive = dateRealReceive;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setLoaner1(Loaner loaner1) {
        this.loaner1 = loaner1;
    }

    public Person getLoaner1() {
        return loaner1;
    }

    public void setloaner2(Loaner loaner2) {
        this.loaner2 = loaner2;
    }

    public Person getloaner2() {
        return loaner2;
    }

    public void setDateLoan(Date dateLoan) {
        this.dateLoan = dateLoan;
    }

    public Date getDateLoan() {
        return dateLoan;
    }

    public void setDateReceive(Date dateReceive) {
        this.dateReceive = dateReceive;
    }

    public Date getDateRealReceive() {
        return dateRealReceive;
    }
}
