package ap.mid_project;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private Library library;
    private Processing Processing;
    private int Request = 0;
    private ArrayList<Loan> loans;
    private Loan selectedLoan;

    public Menu() {
        this.library = new Library(false, false);
        this.Processing = new Processing();
        loans = new ArrayList<>();
        selectedLoan = null;
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
            System.out.println("2)delay");
            System.out.println("3)Number of books delivered and received");
            System.out.println("4)Most Loan");
            System.out.println("5)Exit");
            int choice = Processing.getInt("Enter your choice(1-5)");
            switch (choice) {
                case 1:
                    addNewLoaner();
                    break;
                case 2:
                    Delay();
                    break;
                case 3:
                    NumberOfBooksDeliveredAndReceived();
                    break;
                case 4:
                    mostLoan();
                    break;
                case 5:
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
            System.out.println("3)Exit");
            int choice = Processing.getInt("Enter your choice(1-3)");
            switch (choice) {
                case 1:
                    signUp();
                    break;
                case 2:
                    if (stuLogin()) {
                        showstuMenu2();
                    }
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    public void showstuMenu2() {
        while (true) {
            System.out.println("----Student Menu2---");
            System.out.println("1) Search book");
            System.out.println("2) Request book");
            System.out.println("3) Show unreturned books");
            System.out.println("4) Return book");
            System.out.println("5) Back to main menu");
            int choice = Processing.getInt("Enter your choice (1-5):");
            switch (choice) {
                case 1:
                    searchBook();
                    break;
                case 2:
                    RequestBook();
                    break;
                case 3:
                    ShowUnreturnedBooks();
                    break;
                case 4:
                    ReturnLoanRequest();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public void ReturnLoanRequest() {
        Processing processing = new Processing();
        int id = processing.getInt("Enter your ID");
        SaveLoad saveLoad = new SaveLoad();

        boolean found = false;
        ArrayList<Loan> loanList = new ArrayList<>();

        if (saveLoad.LoadLoansFromFile()) {
            for (String loanLine : saveLoad.loans) {
                Loan loan = new Loan();
                loan.stringForm(loanLine);
                loanList.add(loan);
            }

            for (Loan loan : loanList) {
                if (loan.getStudent().getId() == id && loan.getDateRealReceive() == null) {
                    found = true;
                    System.out.println("Loan found: " + loan);
                    System.out.println("Enter return date:");
                    int year = processing.getInt("Year:");
                    int month = processing.getInt("Month:");
                    int day = processing.getInt("Day:");
                    Date returnDate = new Date(year, month, day);
                    loan.setDateRealReceive(returnDate);
                    break;
                }
            }

            if (!found) {
                System.out.println("not found");
                return;
            }
            saveLoad.SaveLoansTofile(loanList);
        } else {
            System.out.println("Error opening file.");
        }
    }

    public void showLoanerMenu() {
        while (true) {
            System.out.println("----Loaner Menu----");
            System.out.println("1)Login");
            System.out.println("2)Exit");
            int choice = Processing.getInt("Enter your choice(1/2)");
            switch (choice) {
                case 1:
                    if (loanerLogin()) {
                        showLoanerMenu2();
                    }
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Invalid choice");

            }
        }
    }

    public void showLoanerMenu2() {
        while (true) {
            System.out.println("----Loaner Menu2----");
            System.out.println("1)Edit information");
            System.out.println("2)Add new book");
            System.out.println("3)Accept Requests");
            System.out.println("4)Accept returned Books");
            System.out.println("5)Exit");
            int choice = Processing.getInt("Enter your choice (1-5):");
            switch (choice) {
                case 1:
                    editLoanerInfo();
                    break;
                case 2:
                    addNewBook();
                    break;
                case 3:
                    Accept();
                    break;
                case 4:
                    acceptReturnBook();
                    break;
                case 5:
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

    public boolean stuLogin() {
        //Assumption: Login without password and only with ID
        boolean state = false;
        int id = Processing.getInt("Enter ID: ");
        Student student = library.getFindStu(id);
        if (student == null) {
            System.out.println("Student not found");
        } else {
            System.out.println("Login successful!");
            state = true;
            library.setStulogin(state);
        }
        return state;
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

    public boolean loanerLogin() {
        //Assumption: Login without password and only with ID
        boolean state = false;
        int id = Processing.getInt("Enter ID: ");
        Loaner loaner = library.getFindLoaner(id);
        if (loaner == null) {
            System.out.println("Loaner not found");
        } else {
            System.out.println("Login successful!");
            state = true;
            library.setLoanerlogin(state);
        }
        return state;
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

    public void RequestBook() {
        String title = Processing.getString("Enter Title: ");
        String author = Processing.getString("Enter Author: ");
        int stuId = Processing.getInt("Enter your ID: ");
        Book book = library.getFindBook(title, author);
        Student student = library.getFindStu(stuId);

        if (book == null || student == null) {
            System.out.println("Book or Student not found.");
            return;
        } else {
            Request++;
            selectedLoan = new Loan(book, student, null, null, null, null);
        }

    }

    public void Accept() {
        if (Request == 0) {
            System.out.println("Requests not found");
            System.exit(0);
        } else {
            System.out.println("Accept state (1=True/2=False)");
            int select = Processing.getInt("Enter Choice: ");
            if (select == 1) {
                loans.add(selectedLoan);
                Request = 0;
                selectedLoan = null;
                System.out.println("Requests accepted");
                SaveLoad saveLoad = new SaveLoad();
                saveLoad.SaveLoansTofile(loans);
                System.exit(0);
            } else if (select == 2) {
                System.exit(0);
            } else {

                System.out.println("Invalid choice");
                System.exit(0);
            }
        }
    }

    public void ShowUnreturnedBooks() {
        int id = Processing.getInt("Enter your ID");
        SaveLoad saveLoad = new SaveLoad();
        boolean found = false;
        if (saveLoad.LoadLoansFromFile()) { //when file reading seccusesfuly

            for (String loanLine : saveLoad.loans) {
                String[] parts = split(loanLine);
                String returnState = parts[0];
                String studentID = parts[1];

                if (studentID.equals(String.valueOf(id)) && returnState.equals("null")) {
                    System.out.println(loanLine);
                    found = true;
                }
            }

            if (!found) {
                System.out.println("No unreturned loans found.");
                System.exit(0);
            }
        } else {
            System.out.println("error in opening file");
            System.exit(0);
        }
    }

    public String[] split(String line) {
        String[] section = line.split(",");
        String sec1 = null;
        String sec2 = null;
        if (section.length > 5) {
            sec1 = section[5];
        }
        if (section.length > 1) {
            sec2 = section[1];
        }
        return new String[]{sec1, sec2};
    }

    public void acceptReturnBook() {
        Processing input = new Processing();
        SaveLoad saveLoad = new SaveLoad();
        ArrayList<Loan> loans = new ArrayList<>();
        int studentId = input.getInt("Enter student ID:");

        if (!saveLoad.LoadLoansFromFile()) {
            System.out.println("Failed to load loans.");
            return;
        }

        for (String line : saveLoad.loans) {
            Loan loan = new Loan();
            loan.stringForm(line);
        }

        boolean found = false;

        for (Loan loan : loans) {
            if (loan.getStudent().getId() == studentId && loan.getDateRealReceive() == null) {
                found = true;
                System.out.println("Loan found: " + loan);
                int year = input.getInt("Enter return year:");
                int month = input.getInt("Enter return month:");
                int day = input.getInt("Enter return day:");
                Date returnDate = new Date(year, month, day);
                loan.setDateRealReceive(returnDate);
            }
        }

        if (!found) {
            System.out.println("not found");
            return;
        }
        saveLoad.SaveLoansTofile(loans);
    }

    public void Delay() {
        SaveLoad saveLoad = new SaveLoad();
        ArrayList<Loan> loans = saveLoad.readLoansFromFile();
        ArrayList<Loan> show = new ArrayList<>();
        boolean found = false;
        for (Loan loan : loans) {

            Date dateReceive = loan.getDateReceive();
            Date realReturn = loan.getDateRealReceive();

            if (dateReceive != null && realReturn != null) {
                if (realReturn.getYear() > dateReceive.getYear()) {
                    found = true;
                    show.add(loan);
                } else if (realReturn.getYear() == dateReceive.getYear()) {
                    if (realReturn.getMonth() > dateReceive.getMonth()) {
                        found = true;
                        show.add(loan);
                    } else if (realReturn.getMonth() == dateReceive.getMonth()) {
                        if (realReturn.getDay() > dateReceive.getDay()) {
                            found = true;
                            show.add(loan);
                        }
                    }
                }
            }
        }
        if (!found) {
            System.out.println("not found");
            return;
        } else {
            for (Loan loan : show) {
                System.out.println(loan);
            }
        }
    }

    public void mostLoan() {
        SaveLoad saveLoad = new SaveLoad();
        ArrayList<Loan> loans = saveLoad.readLoansFromFile();
        ArrayList<String> books = new ArrayList<>();
        ArrayList<Integer> count = new ArrayList<>();
        ArrayList<Integer> indx = new ArrayList<>();
        int i;
        if (saveLoad.LoadLoansFromFile()) {
            for (String line : saveLoad.loans) {
                Loan loan = new Loan();
                loan.stringForm(line);
                loans.add(loan);
            }

            for (Loan loan : loans) {
                String book = loan.getBook().getTitle() + " " + loan.getBook().getAuthor();
                if (!books.contains(book)) {
                    books.add(book);
                    count.add(1);
                } else {
                    int index = loans.indexOf(book);
                    count.set(index, count.get(index) + 1);
                }
            }
            for (i = 0; i < count.size(); i++) {
                indx.add(i);
            }

            for (i = 0; i < indx.size(); i++) {
                for (int j = i + 1; j < indx.size(); j++) {
                    if (count.get(indx.get(j)) > count.get(indx.get(i))) {
                        int temp = indx.get(i);
                        indx.set(i, indx.get(j));
                        indx.set(j, temp);
                    }
                }
            }

            System.out.println("Most Loaned Books:");
            for (i = 0; i < indx.size(); i++) {
                int idx = indx.get(i);
                System.out.println((i + 1) + "|" + books.get(idx) + "|" + count.get(idx));
            }
        } else {
            System.out.println("Error loading file.");
            return;
        }
    }

    public void NumberOfBooksDeliveredAndReceived() {
        SaveLoad saveLoad = new SaveLoad();
        ArrayList<Loan> loans = new ArrayList<>();
        ArrayList<String> loaners = new ArrayList<>();
        ArrayList<Integer> dateloanCount = new ArrayList<>();
        ArrayList<Integer> dateRealReceiveCount = new ArrayList<>();
        if (saveLoad.LoadLoansFromFile()) {
            for (String line : saveLoad.loans) {
                Loan loan = new Loan();
                loan.stringForm(line);
                loans.add(loan);
            }

        }
        for (Loan loan : loans) {
            String name = loan.getLoaner().getFirstName() + " " + loan.getLoaner().getLastName();

            if (!loaners.contains(name)) {
                loaners.add(name);
                dateloanCount.add(1);

                if (loan.getDateRealReceive() != null) {
                    dateRealReceiveCount.add(1);
                } else {
                    dateRealReceiveCount.add(0);
                }
            } else {
                int index = loaners.indexOf(name);
                dateloanCount.set(index, dateloanCount.get(index) + 1);

                if (loan.getDateRealReceive() != null) {
                    dateRealReceiveCount.set(index, dateRealReceiveCount.get(index) + 1);
                }
            }
        }
        for (int i = 0; i < loaners.size(); i++) {
            System.out.println("Loaner: " + loaners.get(i) + ", Date loan: " + dateloanCount.get(i) + " , Date Real Receive:" + dateRealReceiveCount.get(i));
        }

    }
}




