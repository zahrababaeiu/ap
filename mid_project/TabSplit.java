package ap.mid_project;

import java.io.*;

public class TabSplit implements Storage {
    private String filename;

    public TabSplit(String filename) {
        this.filename = filename;
    }

    @Override
    public void save(Library system) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {

            // STUDENTS
            writer.write("---Students---");
            writer.newLine();
            for (Student student : system.getStudents()) {
                writer.write(student.toString());
                writer.newLine();
            }

            // LOANERS
            writer.write("---Loaners---");
            writer.newLine();
            for (Loaner loaner : system.getLoaners()) {
                writer.write(loaner.toString());
                writer.newLine();
            }

            // BOOKS
            writer.write("---Books---");
            writer.newLine();
            for (Book book : system.getBooks()) {
                writer.write(book.toString());
                writer.newLine();
            }

            // LOANS
            writer.write("---Loans---");
            writer.newLine();
            for (Loan loan : system.getLoans()) {
                writer.write(loan.toString());
                writer.newLine();
            }
        }
    }

    @Override
    public Library load() throws IOException {
        Library system = new Library(false, false);

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            String section = "";

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("---")) {
                    section = line;
                    continue;
                }

                switch (section) {
                    case "---Students---":
                        Student student = new Student();
                        student.stringForm(line);
                        system.addStudent(student);
                        break;

                    case "---Loaners---":
                        Loaner loaner = new Loaner();
                        loaner.stringForm(line);
                        system.addLoaner(loaner);
                        break;

                    case "---Books---":
                        Book book = new Book();
                        book.stringForm(line);
                        system.addBook(book);
                        break;

                    case "---Loans---":
                        Loan loan = new Loan();
                        loan = loan.stringForm(line);

                        if (loan != null) {

                            Student st = system.getFindStu(loan.getStudent().getId());
                            if (st == null) {
                                st = loan.getStudent();
                                system.addStudent(st);
                            }

                            Loaner ln = system.getFindLoaner(loan.getLoaner().getId());
                            if (ln == null) {
                                ln = (Loaner) loan.getLoaner();
                                system.addLoaner(ln);
                            }

                            Book bk = system.getFindBook(loan.getBook().getTitle(), loan.getBook().getAuthor());
                            if (bk == null) {
                                bk = loan.getBook();
                                system.addBook(bk);
                            }

                            loan.setStudent(st);
                            loan.setLoaner(ln);
                            loan.getBook().setTitle(bk.getTitle());
                            loan.getBook().setAuthor(bk.getAuthor());
                            loan.getBook().setYear(bk.getYear());
                            loan.getBook().setPages(bk.getPages());

                            system.getLoans().add(loan);
                        }
                        break;
                }
            }
        }

        return system;
    }
}
