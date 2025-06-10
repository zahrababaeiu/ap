package ap.mid_project;

public class Loan {
    private Book book;
    private Student student;
    private Loaner loaner;
    private Date dateLoan;
    private Date dateReceive;
    private Date dateRealReceive;

    public Loan(Book book, Student student, Loaner loaner, Date dateLoan, Date dateReceive, Date dateRealReceive) {
        this.book = book;
        this.student = student;
        this.loaner = loaner;
        this.dateLoan = dateLoan;
        this.dateReceive = dateReceive;
        this.dateRealReceive = dateRealReceive;
    }

    public Loan() {
    }

    public Book getBook() {
        return book;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setLoaner(Loaner loaner) {
        this.loaner = loaner;
    }

    public Loaner getLoaner() {
        return loaner;
    }

    public Date getDateLoan() {
        return dateLoan;
    }

    public void setDateLoan(Date dateLoan) {
        this.dateLoan = dateLoan;
    }

    public void setDateReceive(Date dateReceive) {
        this.dateReceive = dateReceive;
    }

    public Date getDateReceive() {
        return dateReceive;
    }

    public void setDateRealReceive(Date dateRealReceive) {
        this.dateRealReceive = dateRealReceive;
    }

    public Date getDateRealReceive() {
        return dateRealReceive;
    }

    public Loan stringForm(String line) {
        String[] parts = line.split(",");
        String bookTitle = parts[0];
        String bookAuthor = parts[1];
        int studentId = Integer.parseInt(parts[2]);
        Date dateLoan = parseDate(parts[3]);
        Date dateReceive = parseDate(parts[4]);
        Date dateRealReceive = parseDate(parts[5]);

        Book book = new Book(bookTitle, bookAuthor, 0, 0);
        Student student = new Student(null, null, studentId, null, null);

        Loan loan = new Loan(book, student, loaner, dateLoan, dateReceive, dateRealReceive);
        loan.setDateLoan(dateLoan);
        loan.setDateReceive(dateReceive);
        loan.setDateRealReceive(dateRealReceive);

        return loan;
    }

    private Date parseDate(String str) {
        if (str.equals("null")) return null;
        String[] parts = str.split("/");
        return new Date(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
    }

    @Override
    public String toString() {
        return book + " | " + student + " | " + loaner + " | " + dateLoan + " | " + dateReceive + " | " + (dateRealReceive == null ? "null" : dateRealReceive);
    }

}