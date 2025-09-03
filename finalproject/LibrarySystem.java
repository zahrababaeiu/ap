package finalproject;

import java.io.*;
import java.util.*;

// LibrarySystem.java

public class LibrarySystem {
    private StudentManager studentManager;
    private MenuHandler menuHandler;
    private final String BOOKS_FILE = "E:/books.txt";
    private List<Book> books;
    private String LOANS_FILE = "E:/loans.txt";
    private List<Loan> loans;
    private List<Librarian> librarians;
    private final String STUDENTS_FILE = "E:/students.txt";

    public LibrarySystem() {
        this.studentManager = new StudentManager();
        this.menuHandler = new MenuHandler(this);
        this.books = new ArrayList<>();
        this.loans = new ArrayList<>();
        this.librarians = new ArrayList<>();
    }

    public void addLibrarian(Librarian librarian) {
        librarians.add(librarian);
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
            FileWriter writer = new FileWriter(filePath, true);
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
                    System.out.println("Available State: No" + "\n");
                } else {
                    System.out.println("Available State: Yes" + "\n");
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

    public int getBooksCount() {
        if (books.isEmpty()) {
            loadBooks();
        }
        return books.size();
    }

    public int getLoanedBooksCount() {
        if (books.isEmpty()) {
            loadBooks();
        }
        int count = 0;
        for (Book b : books) {
            if (b.isLoaned()) {
                count++;
            }
        }
        return count;
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

        Loan loan = new Loan(student, bookToBorrow, borrowDate, returnDate, null);
        loans.add(loan);
        saveLoanToFile(student, bookToBorrow, borrowDate, returnDate, null);

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


            saveLoans();
            saveBooks();

        } else {
            System.out.println("No borrowed book found with this title for the student.");
        }
    }

    public void displayAvailableBooks() {
        System.out.println("Not implemented.");
    }

    public void changeLibrarianPassword(String username, String newPassword) {
        Admin admin = new Admin();
        List<Librarian> librarians = admin.loadLibrarians();
        boolean found = false;

        for (Librarian lib : librarians) {
            if (lib.getUserName().equals(username)) {
                lib.setLibrariabPassword(newPassword);
                found = true;
                break;
            }
        }

        if (found) {

            try (FileWriter writer = new FileWriter(admin.LIBRARIANS_FILE)) {
                for (Librarian lib : librarians) {
                    writer.write(lib.toFileString() + "\n");
                }
                System.out.println("Password changed successfully!");
                System.out.println("------------------------------");
            } catch (IOException e) {
                System.out.println("Error saving librarians: " + e.getMessage());
            }
        } else {
            System.out.println("Librarian not found!");
        }
    }

    public void addNewBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new book title:");
        String title = scanner.nextLine().trim();
        System.out.println("Enter new book author:");
        String author = scanner.nextLine().trim();
        System.out.println("Enter book year:");
        int year = scanner.nextInt();
        Book book = new Book(title, author, year, false);
        books.add(book);
        saveBooksToFile(BOOKS_FILE);
        System.out.println("Book added successfully!");
        System.out.println("------------------------");

    }

    public void editBooksInformations() {
        Scanner scanner = new Scanner(System.in);
        loadBooks();

        System.out.println("Enter Book name:");
        String bookname = scanner.nextLine().trim();
        System.out.println("Enter Book author:");
        String author = scanner.nextLine().trim();
        System.out.println("Enter Book year:");
        String year = scanner.nextLine().trim();

        Book bookToEdit = null;

        for (Book book : books) {
            if (!bookname.isEmpty() && !book.getTitle().trim().equalsIgnoreCase(bookname)) {
                continue;
            }
            if (!author.isEmpty() && !book.getAuthor().trim().equalsIgnoreCase(author)) {
                continue;
            }
            if (!year.isEmpty() && book.getYear() != Integer.parseInt(year)) {
                continue;
            }

            bookToEdit = book;
            break;
        }

        if (bookToEdit == null) {
            System.out.println("---Book not found---");
            return;
        }

        System.out.println("Enter new title:");
        String newTitle = scanner.nextLine().trim();
        System.out.println("Enter new author:");
        String newAuthor = scanner.nextLine().trim();
        System.out.println("Enter new year:");
        int newYear = Integer.parseInt(scanner.nextLine().trim());

        bookToEdit.setTitle(newTitle);
        bookToEdit.setAuthor(newAuthor);
        bookToEdit.setYear(newYear);
        bookToEdit.setLoaned(false);

        try {
            FileWriter writer = new FileWriter(BOOKS_FILE);
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
        System.out.println("Book edited successfully!");
        System.out.println("-------------------------");
    }

    public void start() {
        menuHandler.displayMainMenu();
    }

    public static void main(String[] args) {
        LibrarySystem system = new LibrarySystem();
        system.start();
    }

    public void cheking(List<Student> students, List<Book> books) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter today's date (Year/Month/Day): ");
        String todayStr = scanner.nextLine().trim();

        String[] parts = todayStr.split("/");
        Date today = new Date(
                Integer.parseInt(parts[0]),
                Integer.parseInt(parts[1]),
                Integer.parseInt(parts[2])
        );

        List<Loan> validLoans = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(LOANS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");

                String username = fields[0].split(":")[1].trim();
                String booktitle = fields[3].split(":")[1].trim();

                Student student = null;
                for (Student s : students) {
                    if (s.getUsername().equals(username)) {
                        student = s;
                        break;
                    }
                }

                Book book = null;
                for (Book b : books) {
                    if (b.getTitle().equals(booktitle)) {
                        book = b;
                        break;
                    }
                }

                if (student == null || book == null) {
                    System.out.println("Student or Book not found");
                    continue;
                }

                Loan loan = Loan.fromString(line, student, book);
                if (loan != null) {
                    Date loanDate = loan.getBorrowDate();

                    if (loanDate.equals(today) || loanDate.isYesterdayOf(today)) {
                        validLoans.add(loan);
                        System.out.println("Request approved for " + student.getName() + " - " + book.getTitle());
                    } else {
                        System.out.println("Request rejected for " + student.getName() + " - " + book.getTitle());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading loans: " + e.getMessage());
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(LOANS_FILE, false))) {
            for (Loan loan : validLoans) {
                bw.write(loan.toFileString());
                bw.newLine();
            }
        } catch (Exception e) {
            System.out.println("Error writing loans: " + e.getMessage());
        }

        System.out.println("Loan requests validated and saved.");
    }

    public List<Book> getBooks() {
        if (books.isEmpty()) {
            loadBooks();
        }
        return new ArrayList<>(books);
    }

    private void loadLoans() {
        loans.clear();
        try {
            File file = new File(LOANS_FILE);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    Loan loan = Loan.fromString(line, null, null);
                    if (loan != null) {
                        loans.add(loan);
                    }
                }
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Error in reading file: " + e.getMessage());
        }
    }

    public void studentLoanReport(String stuusername) {
        List<Loan> loanList = new ArrayList<>();
        loadLoans();
        int totalLoans = 0, notReturned = 0, lateReturns = 0;

        for (Loan loan : loans) {
            if (stuusername != null && !stuusername.isEmpty()
                    && loan.getStudent() != null
                    && loan.getStudent().getUsername().trim().equals(stuusername.trim())) {

                loanList.add(loan);
                totalLoans++;

                if (!loan.isReturned()) {
                    notReturned++;
                } else if (loan.getActualReturnDate() != null && loan.getReturnDate() != null) {
                    if (loan.getActualReturnDate().late(loan.getActualReturnDate(), loan.getReturnDate())) {
                        lateReturns++;
                    }
                }
            }
        }

        if (loanList.isEmpty()) {
            System.out.println("No loans found for student: " + stuusername);
        } else {
            System.out.println("--- Loan History for " + stuusername + " ---");
            for (Loan loan : loanList) {
                System.out.println(loan);
            }
            System.out.println("Total Loans: " + totalLoans);
            System.out.println("Not Returned: " + notReturned);
            System.out.println("Late Returns: " + lateReturns);
        }
    }

    public void active(String stuUsername) {
        List<Student> students = this.studentManager.getAllStudents();
        boolean found = false;

        for (Student student : students) {
            if (student.getUsername().equals(stuUsername)) {
                Scanner sc = new Scanner(System.in);
                System.out.println("State: " + (student.isActive() ? "Active" : "Deactivated"));
                System.out.print("Active status? (yes/no): ");
                String choice = sc.nextLine().trim().toLowerCase();

                if (choice.equals("yes")) {
                    student.setActive(true);
                    System.out.println("Student " + stuUsername + " is now Active.");
                } else {
                    student.setActive(false);
                    System.out.println("Student " + stuUsername + " is now Deactivated.");
                }
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Student not found!");
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(STUDENTS_FILE, false))) {
            List<String> seenUsernames = new ArrayList<>();
            for (Student s : students) {
                if (!seenUsernames.contains(s.getUsername())) {
                    bw.write(s.toFileString());
                    bw.newLine();
                    seenUsernames.add(s.getUsername());
                }
            }
            System.out.println("Changes successfully.");
        } catch (IOException e) {
            System.out.println("Error saving students: " + e.getMessage());
        }
        System.out.println("--------------------");
    }

    public boolean chekingState(String username) {
        try (BufferedReader br = new BufferedReader(new FileReader(STUDENTS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                Student student = Student.fromString(line);
                if (student != null && student.getUsername().equals(username)) {
                    return student.isActive();
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading students: " + e.getMessage());
        }
        return false;
    }

    public void getLoanBooks() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the student's username: ");
        String stuUsername = input.nextLine().trim();

        System.out.print("Enter the title of the book to return: ");
        String title = input.nextLine().trim();

        Loan loanToReturn = null;
        for (Loan loan : loans) {
            if (loan.getStudent().getUsername().equals(stuUsername) &&
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

            System.out.println("Book returned successfully by librarian!");
            System.out.println("Actual return date: " + actualReturn.getYear() + "/" +
                    actualReturn.getMonth() + "/" + actualReturn.getDay());

            saveLoans();
            saveBooks();

        } else {
            System.out.println("No borrowed book found with this title for the student.");
        }
    }

    private void saveBooks() {
        try (FileWriter writer = new FileWriter(BOOKS_FILE)) {
            for (Book book : books) {
                writer.write(book.getTitle() + ","
                        + book.getAuthor() + ","
                        + book.getYear() + ","
                        + book.isLoaned() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving books to file: " + e.getMessage());
        }
    }

    private void saveLoans() {
        try (FileWriter writer = new FileWriter(LOANS_FILE)) {
            for (Loan loan : loans) {
                writer.write("Student Name: " + loan.getStudent().getName() +
                        ", Student ID: " + loan.getStudent().getStudentId() +
                        ", Username: " + loan.getStudent().getUsername() +
                        ", Book Title: " + loan.getBook().getTitle() +
                        ", Book Author: " + loan.getBook().getAuthor() +
                        ", Borrow Date: " + loan.getBorrowDate().getYear() + "/" +
                        loan.getBorrowDate().getMonth() + "/" +
                        loan.getBorrowDate().getDay() +
                        ", Return Date: " + loan.getReturnDate().getYear() + "/" +
                        loan.getReturnDate().getMonth() + "/" +
                        loan.getReturnDate().getDay());

                if (loan.getActualReturnDate() != null) {
                    writer.write(", Actual Return Date: " + loan.getActualReturnDate().getYear() + "/" +
                            loan.getActualReturnDate().getMonth() + "/" +
                            loan.getActualReturnDate().getDay());
                }
                writer.write("\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving loans: " + e.getMessage());
        }
    }
}
