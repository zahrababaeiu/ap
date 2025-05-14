package ap.mid_project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Menu {
    private Library library;
    private Processing Processing;

    public Menu() {
        this.library = new Library(false, false);
        this.Processing = new Processing();
    }

    public void showMenu() {
        System.out.println("----Menu----");
        System.out.println("Who do you want to log in as?");
        int select = Processing.getInt("1)Library Manager\n2)Loaner\n3)Student");
        if (select == 1) {
            showLibraryManagerMenu();
        } else if (select == 2) {
            showLoanerMenu();
        } else if (select == 3) {
            showStuMenu();
        } else {
            System.out.println("invalid selection");
            System.exit(0);
        }
    }

    public void showLibraryManagerMenu() {
        while (true) {
            System.out.println("----Library Manager Menu----");
            System.out.println("1)Add new loaner");
            System.out.println("2)Exit");
            int choice = Processing.getInt("Enter your choice(1-2)");
            switch (choice) {
                case 1:
                    addNewLoaner();
                    break;
                case 2:
                    return;
                default:
                    System.out.println("invalid choice");
            }
        }
    }

    public void showStuMenu() {
        while (true) {
            System.out.println("----Student Menu----");
            System.out.println("1)Sign up student");
            System.out.println("2)Login student");
            System.out.println("3)Search book");
            System.out.println("4)Exit");
            int choice = Processing.getInt("Enter your choice(1-4)");
            switch (choice) {
                case 1:
                    signUp();
                    break;
                case 2:
                    stuLogin();
                    break;
                case 3:
                    searchBook();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }

    }

    public void showLoanerMenu() {
        while (true) {
            System.out.println("----Loaner Menu----");
            System.out.println("1)Login");
            System.out.println("2)Edit information");
            System.out.println("3)Add new book");
            System.out.println("4)Exit");
            int choice = Processing.getInt("Enter your choice(1-4)");
            switch (choice) {
                case 1:
                    loanerLogin();
                    break;
                case 2:
                    editLoanerInfo();
                    break;
                case 3:
                    addNewBook();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice");

            }
        }
    }

    public void signUp() {
        System.out.println("---STUDENT INFO---");
        String firstName = Processing.getString("Enter First Name: ");
        String lastName = Processing.getString("Enter Last Name: ");
        int id = Processing.getInt("Enter ID: ");
        String major = Processing.getString("Enter Major: ");

        System.out.println("Enter Date of membership:");
        int year = Processing.getInt("year: ");
        int month = Processing.getInt("month: ");
        int day = Processing.getInt("day: ");

        Date dateOfMembership = new Date(year, month, day);
        Student student = new Student(firstName, lastName, id, major, dateOfMembership);
        library.addStudent(student);
        System.out.println("Student added successfully");
    }

    public void stuLogin() {
        //Assumption: Login without password and only with ID
        int id = Processing.getInt("Enter ID: ");
        Student student = library.getFindStu(id);
        if (student == null) {
            System.out.println("Student not found");
        } else {
            System.out.println("Login successful!");
            boolean state = true;
            library.setStulogin(state);
        }
    }

    public void searchBook() {
        String title = Processing.getString("Enter Book Name: ");
        String author = Processing.getString("Enter Author: ");
        File file = new File("E:\\java\\library\\Books.txt");
        boolean find = false;
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.contains(title) && line.contains(author)) {
                    System.out.println("----Book found----");
                    System.out.println(line);
                    find = true;
                    break;
                }
            }
            if (!find) {
                System.out.println("Book not found");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    public void loanerLogin() {
        //Assumption: Login without password and only with ID
        int id = Processing.getInt("Enter ID: ");
        Loaner loaner = library.getFindLoaner(id);
        if (loaner == null) {
            System.out.println("Loaner not found");
        } else {
            System.out.println("Login successful!");
            boolean state = true;
            library.setLoanerlogin(state);
        }
    }

    public void editLoanerInfo() {
        System.out.println("----Edit Loaner Info----");
        int id = Processing.getInt("Enter ID: ");
        if (library.getFindLoaner(id) == null) {
            System.out.println("Loaner not found");
        } else {
            String firstName = Processing.getString("Enter First Name: ");
            String lastName = Processing.getString("Enter Last Name: ");
            //We assume that the ID is fixed and cannot be changed
            Loaner loaner = new Loaner(firstName, lastName, id);
            library.addLoaner(loaner);
            SaveLoad saveLoad = new SaveLoad();
            saveLoad.SaveLonersToFile(loaner);
            System.out.println("Successful!");
        }
    }

    public void addNewBook() {
        System.out.println("----Add new book----");
        String title = Processing.getString("Enter Title: ");
        String author = Processing.getString("Enter Author: ");
        int year = Processing.getInt("Enter Year: ");
        int pages = Processing.getInt("Enter Pages: ");
        Book newbook = new Book(title, author, year, pages);
        library.addBook(newbook);
        System.out.println("Book added successfully");
    }

    public void addNewLoaner() {
        System.out.println("----Add new loaner----");
        String firstName = Processing.getString("Enter First Name: ");
        String lastName = Processing.getString("Enter Last Name: ");
        int id = Processing.getInt("Enter ID: ");
        Loaner loaner = new Loaner(firstName, lastName, id);
        library.addLoaner(loaner);
        System.out.println("Loaner added successfully");
    }
}

