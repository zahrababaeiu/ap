package finalproject;

// MenuHandler.java

import java.util.List;
import java.util.Scanner;

public class MenuHandler {
    private Scanner scanner;
    private LibrarySystem librarySystem;
    private Student currentUser;

    public MenuHandler(LibrarySystem librarySystem) {
        this.scanner = new Scanner(System.in);
        this.librarySystem = librarySystem;
        this.currentUser = null;
    }

    public void displayMainMenu() {
        while (true) {
            System.out.println("\n=== University Library Management System ===");
            System.out.println("1. Student Registration");
            System.out.println("2. Student Login");
            System.out.println("3. Guest Login");
            System.out.println("4. Admin Login");
            System.out.println("5. Librarian Login");
            System.out.println("6. Exit");
            System.out.print("Please enter your choice: ");

            int choice = getIntInput(1, 6);

            switch (choice) {
                case 1:
                    handleStudentRegistration();
                    break;
                case 2:
                    handleStudentLogin();
                    break;
                case 3:
                    handleGuestLogin();
                    break;
                case 4:
                    adminMenuHandler();
                    break;
                case 5:
                    handelLibrarianlogin();
                    break;
                case 6:
                    System.out.println("Exiting system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
            System.out.println("___________________________");
        }
    }

    private void handelLibrarianlogin() {
        System.out.print("Enter your Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your Password: ");
        String password = scanner.nextLine();
        Admin admin = new Admin();
        List<Librarian> librarians = admin.loadLibrarians();

        boolean found = false;
        for (Librarian lib : librarians) {
            if (lib.getLibrarianUsername().equals(username) && lib.getPassword().equals(password)) {
                System.out.println("Welcome Librarian " + lib.getName());
                found = true;
                librarianMenueHandel();
                break;
            }
        }

        if (!found) {
            System.out.println("Invalid username or password. Try again.");
        }

    }

    private void librarianMenueHandel() {
        System.out.println("\n=== Librarian Menu ===");
        while (true) {
            System.out.println("1. Change Password");
            System.out.println("2. Add new book");
            System.out.println("3. Exit");
            System.out.println("Please enter your choice: ");
            int choice = getIntInput(1, 3);
            switch (choice) {
                case 1:
                    System.out.println("Enter your Username: ");
                    String username = scanner.nextLine();
                    System.out.println("Enter your New Password: ");
                    String password = scanner.nextLine();
                    librarySystem.changeLibrarianPassword(username, password);
                    break;

                case 2:
                    librarySystem.addNewBook();
                    break;

                case 3:
                    System.out.println("Exiting system. Goodbye!");
                    System.out.println("------------------------");
                    return;

                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    private void adminMenuHandler() {
        System.out.println("\n=== Admin Menu ===");
        while (true) {
            System.out.println("1. Add Librarian");
            System.out.println("2. Exit");
            System.out.print("Please enter your choice: ");
            int choice = getIntInput(1, 2);
            switch (choice) {
                case 1:
                    handleLibrarianRegistration();
                    break;
                case 2:
                    System.out.println("Exiting system. Goodbye!");
                    System.out.println("--------------------");
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    private void handleGuestLogin() {
        System.out.println("\n=== Welcome to system ===");
        while (true) {
            System.out.println("1.View the number of registered students");
            System.out.println("2.Search a book");
            System.out.println("3.View students and books information");
            System.out.println("4.Exit");
            System.out.print("Please enter your choice: ");
            int choice = getIntInput(1, 4);
            switch (choice) {
                case 1:
                    displayStudentCount();
                    break;
                case 2:
                    System.out.println("Enter Book Title: ");
                    String bookTitle = scanner.nextLine();
                    librarySystem.searchBooks(bookTitle);
                    break;
                case 3:
                    displayStudentCount();
                    displayBooksCount();
                    displayLoanedBookCount();
                    break;
                case 4:
                    System.out.println("Exiting system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    private void handleLibrarianRegistration() {
        System.out.println("Enter Librarian Name:");
        String librarianName = scanner.nextLine();
        System.out.println("Enter Librarian ID:");
        String librarianId = scanner.nextLine();
        System.out.println("Enter Librarian Username:");
        String librarianUsername = scanner.nextLine();
        System.out.println("Enter Librarian Password:");
        String librarianPassword = scanner.nextLine();
        Librarian newLibrarian = new Librarian(librarianName, librarianId, librarianUsername, librarianPassword);
        librarySystem.addLibrarian(newLibrarian);
        Admin admin = new Admin();
        admin.saveLibrariantoFile(newLibrarian);
        System.out.println("Librarian registered successfully!");

    }

    private void displayLoanedBookCount() {
        int loanedBook = librarySystem.getLoanedBooksCount();
        System.out.println("Total loaned Books: " + loanedBook + "\n");
    }

    private void displayStudentCount() {
        int studentCount = librarySystem.getStudentCount();
        System.out.println("\nTotal registered students: " + studentCount);
    }

    private void displayBooksCount() {
        int bookCount = librarySystem.getBooksCount();
        System.out.println("Total books: " + bookCount);
    }

    private void handleStudentRegistration() {
        System.out.println("\n--- New Student Registration ---");

        System.out.print("Student name: ");
        String name = scanner.nextLine();

        System.out.print("Student ID: ");
        String studentId = scanner.nextLine();

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        librarySystem.registerStudent(name, studentId, username, password);
    }

    private void handleStudentLogin() {
        System.out.println("\n--- Student Login ---");

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        currentUser = librarySystem.authenticateStudent(username, password);

        if (currentUser != null) {
            System.out.println("Login successful! Welcome, " + currentUser.getName());
            displayLoggedInStudentMenu();
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }

    private void displayLoggedInStudentMenu() {
        while (currentUser != null) {
            System.out.println("\n=== Student Dashboard ===");
            System.out.println("1. View My Information");
            System.out.println("2. Edit My Information");
            System.out.println("3. Borrow a Book");
            System.out.println("4. Return a Book");
            System.out.println("5. View Available Books");
            System.out.println("6. Search a Book");
            System.out.println("7. Logout");
            System.out.print("Please enter your choice: ");

            int choice = getIntInput(1, 6);

            switch (choice) {
                case 1:
                    System.out.println("\n--- My Information ---");
                    System.out.println(currentUser);
                    break;
                case 2:
                    librarySystem.editStudentInformation(currentUser);
                    break;
                case 3:
                    librarySystem.borrowBook(currentUser);
                    break;
                case 4:
                    librarySystem.returnBook(currentUser);
                    break;
                case 5:
                    librarySystem.displayAvailableBooks();
                    break;
                case 6:
                    System.out.println("Enter book title: ");
                    String bookTitle = scanner.nextLine();
                    System.out.println("Enter book author: ");
                    String bookAuthor = scanner.nextLine();
                    System.out.println("Enter book year: ");
                    String bookYear = scanner.nextLine();
                    librarySystem.searchBooks(bookTitle, bookAuthor, bookYear);
                    break;
                case 7:
                    currentUser = null;
                    System.out.println("Logged out successfully.");
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    private int getIntInput(int min, int max) {
        while (true) {
            try {
                int input = Integer.parseInt(scanner.nextLine());
                if (input >= min && input <= max) {
                    return input;
                }
                System.out.printf("Please enter a number between %d and %d: ", min, max);
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }
}
