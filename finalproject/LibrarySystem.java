package finalproject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// LibrarySystem.java
public class LibrarySystem {
    private StudentManager studentManager;
    private MenuHandler menuHandler;
    private final String BOOKS_FILE = "E:/books.txt";
    private List<Book> books;
    private String LOANS_FILE = "loans.txt";
    private List<Loan> loans;


    public LibrarySystem() {
        this.studentManager = new StudentManager();
        this.menuHandler = new MenuHandler(this);
        this.books = new ArrayList<>();
        this.loans = new ArrayList<>();
    }


    public void loadBooks() {
        books.clear();
        try {
            File file = new File(BOOKS_FILE);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    Book book = Book.fromString(line);
                    if (book != null) {
                        books.add(book);
                    }
                }
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Error in reading file: " + e.getMessage());
        }
    }

    public void saveBooksToFile(String filePath) {

        try {
            FileWriter writer = new FileWriter(filePath);
            for (Book book : books) {
                writer.write(book.getTitle() + ","
                        + book.getAuthor() + ","
                        + book.getYear() + ","
                        + book.isLoaned() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving books to file: " + e.getMessage());
        }
    }

    public void searchBooks(String title, String author, String year) {
        List<Book> result = new ArrayList<>();
        loadBooks();

        for (Book book : books) {
            if (title != null && !title.isEmpty() && !book.getTitle().trim().equalsIgnoreCase(title.trim())) {
                continue;
            }
            if (author != null && !author.isEmpty() && !book.getAuthor().trim().equalsIgnoreCase(author.trim())) {
                continue;
            }
            if (year != null && !year.isEmpty() && !String.valueOf(book.getYear()).trim().equals(year.trim())) {
                continue;
            }
            result.add(book);
        }

        if (result.isEmpty()) {
            System.out.println("---Book not found---");
        } else {
            System.out.println("---Found books---");
            for (Book book : result) {
                System.out.println(book);
                if (book.isLoaned()) {
                    System.out.println("Available State: No"+"\n");
                } else {
                    System.out.println("Available State: Yes"+"\n");
                }
            }
        }
    }

    public void searchBooks(String title) {
        searchBooks(title, null, null);
    }

    public int getStudentCount() {
        return this.studentManager.getStudentCount();
    }

    public void registerStudent(String name, String studentId, String username, String password) {
        studentManager.registerStudent(name, studentId, username, password);
    }

    public Student authenticateStudent(String username, String password) {
        return studentManager.authenticateStudent(username, password);
    }

    public void editStudentInformation(Student student) {
        System.out.println("Not implemented.");
    }

    public void borrowBook(Student student) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter book title to borrow: ");
        String title = input.nextLine();

        Book bookToBorrow = null;
        String inputTitle = title.trim().toLowerCase();

        for (Book book : books) {
            String bookTitle = book.getTitle().trim().toLowerCase();
            if (bookTitle.equals(inputTitle) && !book.isLoaned()) {
                bookToBorrow = book;
                break;
            }
    }

        if (bookToBorrow == null) {
            System.out.println("Book not available for borrowing");
            return;
        }
        bookToBorrow.setLoaned(true);

        System.out.println("Enter borrow date:");
        System.out.print("Year: ");
        int by = input.nextInt();
        System.out.print("Month: ");
        int bm = input.nextInt();
        System.out.print("Day: ");
        int bd = input.nextInt();
        Date borrowDate = new Date(by, bm, bd);

        System.out.println("Enter return date:");
        System.out.print("Year: ");
        int ry = input.nextInt();
        System.out.print("Month: ");
        int rm = input.nextInt();
        System.out.print("Day: ");
        int rd = input.nextInt();
        Date returnDate = new Date(ry, rm, rd);

        Loan loan = new Loan(student, bookToBorrow, borrowDate, returnDate,null);
        loans.add(loan);
        saveLoanToFile(student, bookToBorrow, borrowDate, returnDate,null);

        System.out.println("Book borrowed successfully!");
        System.out.println("Borrow date: " + borrowDate.getYear() + "/" + borrowDate.getMonth() + "/" + borrowDate.getDay());
        System.out.println("Return date: " + returnDate.getYear() + "/" + returnDate.getMonth() + "/" + returnDate.getDay());

        saveBooksToFile(BOOKS_FILE);
    }

    private void saveLoanToFile(Student student, Book book, Date borrowDate, Date returnDate, Date actualReturnDate) {
        try (FileWriter writer = new FileWriter(LOANS_FILE, true)) {
            writer.write("Student Name: " + student.getName() +
                    ", Student ID: " + student.getStudentId() +
                    ", Username: " + student.getUsername() +
                    ", Book Title: " + book.getTitle() +
                    ", Book Author: " + book.getAuthor() +
                    ", Borrow Date: " + borrowDate.getYear() + "/" + borrowDate.getMonth() + "/" + borrowDate.getDay() +
                    ", Return Date: " + returnDate.getYear() + "/" + returnDate.getMonth() + "/" + returnDate.getDay());

            if (actualReturnDate != null) {
                writer.write(", Actual Return Date: " + actualReturnDate.getYear() + "/" + actualReturnDate.getMonth() + "/" + actualReturnDate.getDay());
            }

            writer.write("\n");
        } catch (IOException e) {
            System.out.println("Error saving loan information: " + e.getMessage());
        }
    }

    public void returnBook(Student student) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the title of the book to return: ");
        String title = input.nextLine().trim();

        Loan loanToReturn = null;
        for (Loan loan : loans) {
            if (loan.getStudent().getUsername().equals(student.getUsername()) &&
                    loan.getBook().getTitle().equalsIgnoreCase(title) &&
                    loan.getActualReturnDate() == null) {
                loanToReturn = loan;
                break;
            }
        }

        if (loanToReturn != null) {
            System.out.println("Enter actual return date:");
            System.out.print("Year: ");
            int y = input.nextInt();
            System.out.print("Month: ");
            int m = input.nextInt();
            System.out.print("Day: ");
            int d = input.nextInt();

            Date actualReturn = new Date(y, m, d);

            loanToReturn.setActualReturnDate(actualReturn);
            loanToReturn.getBook().setLoaned(false);

            System.out.println("Book returned successfully!");
            System.out.println("Actual return date: " + actualReturn.getYear() + "/" +
                    actualReturn.getMonth() + "/" + actualReturn.getDay());

            saveLoanToFile(loanToReturn.getStudent(), loanToReturn.getBook(),
                    loanToReturn.getBorrowDate(), loanToReturn.getReturnDate(),
                    loanToReturn.getActualReturnDate());

            saveBooksToFile(BOOKS_FILE);

        } else {
            System.out.println("No borrowed book found with this title for the student.");
        }
    }

    public void displayAvailableBooks() {
        System.out.println("Not implemented.");
    }

    public void start() {
        menuHandler.displayMainMenu();
    }

    public static void main(String[] args) {
        LibrarySystem system = new LibrarySystem();
        system.start();
    }
}
