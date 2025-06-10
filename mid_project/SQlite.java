package ap.mid_project;

import java.sql.*;


public class SQlite implements Storage {
    private final String dbUrl = "jdbc:sqlite:library.db";

    public SQlite(String s) {
        createTables();
    }

    private void createTables() {
        try (Connection conn = DriverManager.getConnection(dbUrl);
             Statement stmt = conn.createStatement()) {
            // Create Students table
            stmt.execute("CREATE TABLE IF NOT EXISTS students (" +
                    "id INTEGER PRIMARY KEY, firstName TEXT, lastName TEXT, major TEXT, membershipDate TEXT)");

            // Create Loaners table
            stmt.execute("CREATE TABLE IF NOT EXISTS loaners (" +
                    "id INTEGER PRIMARY KEY, firstName TEXT, lastName TEXT)");

            // Create Books table
            stmt.execute("CREATE TABLE IF NOT EXISTS books (" +
                    "title TEXT, author TEXT, year INTEGER, pages INTEGER)");

            // Create Loans table
            stmt.execute("CREATE TABLE IF NOT EXISTS loans (" +
                    "bookTitle TEXT, studentId INTEGER, loanerId INTEGER," +
                    "dateLoan TEXT, dateReceive TEXT, dateRealReceive TEXT)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Library system) {
        try (Connection conn = DriverManager.getConnection(dbUrl)) {
            conn.setAutoCommit(false);

            // Clear old data
            conn.createStatement().execute("DELETE FROM students");
            conn.createStatement().execute("DELETE FROM loaners");
            conn.createStatement().execute("DELETE FROM books");
            conn.createStatement().execute("DELETE FROM loans");

            // Insert Students
            for (Student s : system.getStudents()) {
                String sql = "INSERT INTO students VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, s.getId());
                    ps.setString(2, s.getFirstName());
                    ps.setString(3, s.getLastName());
                    ps.setString(4, s.getMajor());
                    ps.setString(5, s.getDate() == null ? null : s.getDate().toString());
                    ps.executeUpdate();
                }
            }

            // Insert Loaners
            for (Loaner l : system.getLoaners()) {
                String sql = "INSERT INTO loaners VALUES (?, ?, ?)";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, l.getId());
                    ps.setString(2, l.getFirstName());
                    ps.setString(3, l.getLastName());
                    ps.executeUpdate();
                }
            }

            // Insert Books
            for (Book b : system.getBooks()) {
                String sql = "INSERT INTO books VALUES (?, ?, ?, ?)";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, b.getTitle());
                    ps.setString(2, b.getAuthor());
                    ps.setInt(3, b.getYear());
                    ps.setInt(4, b.getPages());
                    ps.executeUpdate();
                }
            }

            // Insert Loans
            for (Loan l : system.getLoans()) {
                String sql = "INSERT INTO loans VALUES (?, ?, ?, ?, ?, ?)";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, l.getBook().getTitle());
                    ps.setInt(2, l.getStudent().getId());
                    ps.setInt(3, l.getLoaner().getId());
                    ps.setString(4, l.getDateLoan() == null ? null : l.getDateLoan().toString());
                    ps.setString(5, l.getDateReceive() == null ? null : l.getDateReceive().toString());
                    ps.setString(6, l.getDateRealReceive() == null ? null : l.getDateRealReceive().toString());
                    ps.executeUpdate();
                }
            }

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Library load() {
        Library system = new Library();

        try (Connection conn = DriverManager.getConnection(dbUrl)) {

            // Load Students
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM students");
            while (rs.next()) {
                Student s = new Student();
                s.setFirstName(rs.getString("firstName"));
                s.setLastName(rs.getString("lastName"));
                s.setMajor(rs.getString("major"));
                s.stringForm(s.getFirstName() + "," + s.getLastName() + "," + rs.getInt("id") + "," + s.getMajor() + "," + rs.getString("membershipDate"));
                system.addStudent(s);
            }

            // Load Loaners
            rs = conn.createStatement().executeQuery("SELECT * FROM loaners");
            while (rs.next()) {
                Loaner l = new Loaner();
                l.stringForm(rs.getString("firstName") + "," + rs.getString("lastName") + "," + rs.getInt("id"));
                system.addLoaner(l);
            }

            // Load Books
            rs = conn.createStatement().executeQuery("SELECT * FROM books");
            while (rs.next()) {
                Book b = new Book();
                b.stringForm(rs.getString("title") + "," + rs.getString("author") + "," + rs.getInt("year") + ", pages: " + rs.getInt("pages"));
                system.addBook(b);
            }

            // Load Loans
            rs = conn.createStatement().executeQuery("SELECT * FROM loans");
            while (rs.next()) {
                String loanStr = rs.getString("bookTitle") + "," +
                        "unknown," + // author not saved, add book separately
                        rs.getInt("studentId") + "," +
                        rs.getString("dateLoan") + "," +
                        rs.getString("dateReceive") + "," +
                        rs.getString("dateRealReceive");
                Loan l = new Loan().stringForm(loanStr);
                if (l != null) system.addLoan(l);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return system;
    }
}
