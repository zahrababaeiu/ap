package ap.exercises.ex3;

import java.util.Scanner;

class Books {
    String name;
    String author;
    int pages;
    int year;

    public Books(String name, String author, int pages, int year) {
        this.name = name;
        this.author = author;
        this.pages = pages;
        this.year = year;
    }
}

class Students {
    String firstName;
    String lastName;
    int id;
    String major;

    public Students(String firstName, String lastName, int id, String major) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.major = major;
    }
}

public class Main_EX3_LM_1_1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Books[] books = {
                new Books("Riazi", "Ali Mohammadi", 125, 1378),
                new Books("Arabi", "Maryam Abbasi", 86, 1395)
        };

        Students[] students = {
                new Students("Zahra", "Babaei", 1231, "Computer"),
                new Students("Mahdi", "Alavi", 1232, "Architecture")
        };

        while (true) {
            System.out.println("Do you want to change the information? (Y/N)");
            char select = input.next().charAt(0);
            input.nextLine();
            if (select == 'Y' || select == 'y') {
                System.out.println("Books or Students? (B/S)");
                char choice = input.next().charAt(0);
                input.nextLine();
                if (choice == 'B' || choice == 'b') {
                    bookInfo(books, input);
                } else if (choice == 'S' || choice == 's') {
                    stuInfo(students, input);
                } else {
                    System.out.println("Invalid choice!");
                }
            } else {
                System.out.println("Exit");
                break;
            }
        }

        input.close();
    }

    public static void stuInfo(Students[] students, Scanner input) {
        System.out.println("Enter student ID:");
        int id = input.nextInt();
        input.nextLine();

        for (int i = 0; i < students.length; i++) {
            if (students[i].id == id) {
                System.out.println("1) First Name\n2) Last Name\n3) Major");
                System.out.println("Enter your choice:");
                int choice = input.nextInt();
                input.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("Enter new first name:");
                        students[i].firstName = input.nextLine();
                        break;
                    case 2:
                        System.out.println("Enter new last name:");
                        students[i].lastName = input.nextLine();
                        break;
                    case 3:
                        System.out.println("Enter new major:");
                        students[i].major = input.nextLine();
                        break;
                    default:
                        System.out.println("Invalid choice");
                        return;
                }

                System.out.println("Updated successfully!");
                return;
            }
        }

        System.out.println("Student not found!");
    }

    public static void bookInfo(Books[] books, Scanner input) {
        System.out.println("Enter book's name:");
        String name = input.nextLine();

        for (int i = 0; i < books.length; i++) {
            if (books[i].name.equals(name)) {
                System.out.println("1) Name\n2) Author\n3) Pages\n4) Year");
                System.out.println("Enter your choice:");
                int choice = input.nextInt();
                input.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("Enter new name:");
                        books[i].name = input.nextLine();
                        break;
                    case 2:
                        System.out.println("Enter new author:");
                        books[i].author = input.nextLine();
                        break;
                    case 3:
                        System.out.println("Enter new pages count:");
                        books[i].pages = input.nextInt();
                        input.nextLine();
                        break;
                    case 4:
                        System.out.println("Enter new year:");
                        books[i].year = input.nextInt();
                        input.nextLine();
                        break;
                    default:
                        System.out.println("Invalid choice");
                        return;
                }

                System.out.println("Updated successfully!");
                return;
            }
        }

        System.out.println("Book not found!");
    }
}
