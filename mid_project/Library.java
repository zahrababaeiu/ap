package ap.mid_project;

import java.util.ArrayList;

public class Library {
    private String name = "ZNU";
    private ArrayList<Book> books;
    private ArrayList<Student> students;
    private ArrayList<Loaner> loaners;
    private ArrayList<Loan> loans;
    private boolean stulogin;
    private boolean loanerlogin;

    public Library(boolean stulogin, boolean loanerlogin) {
        this.books = new ArrayList<>();
        this.students = new ArrayList<>();
        this.loaners = new ArrayList<>();
        this.loans = new ArrayList<>();
        this.stulogin = stulogin;
        this.loanerlogin = loanerlogin;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addBook(Book book) {
        books.add(book);
        SaveLoad saveLoad = new SaveLoad();
        saveLoad.SaveBookToFile(book);
    }

    public void addStudent(Student student) {
        students.add(student);
        SaveLoad saveLoad = new SaveLoad();
        saveLoad.SaveStuToFile(student);
    }

    public void addLoaner(Loaner loaner) {
        loaners.add(loaner);
        SaveLoad saveLoad = new SaveLoad();
        saveLoad.SaveLonersToFile(loaner);

    }

    public void addLoan(Loan loan) {
        loans.add(loan);

    }

    public Student getFindStu(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public Book getFindBook(String title, String author) {
        for (Book book : books) {
            if (book.getTitle().equals(title) && book.getAuthor().equals(author)) {
                return book;
            }
        }
        return null;
    }

    public Loaner getFindLoaner(int id) {
        for (Loaner loaner : loaners) {
            if (loaner.getId() == id) {
                return loaner;
            }
        }
        return null;
    }

    public void setStulogin(boolean stulogin) {
        this.stulogin = stulogin;
    }

    public boolean getstulogin() {
        return stulogin;
    }

    public void setLoanerlogin(boolean loanerlogin) {
        this.loanerlogin = loanerlogin;
    }

    public boolean getloanerlogin() {
        return loanerlogin;
    }
}

