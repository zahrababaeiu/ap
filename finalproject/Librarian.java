package finalproject;

public class Librarian extends Information {

    public Librarian(String name, String librarianId, String username, String password) {
        super(name, librarianId, username, password);
    }

    public String getLibrarianName() {
        return super.getName();
    }

    public String getLibrarianId() {
        return super.getId();
    }

    public String getLibrarianUsername() {
        return super.getUserName();
    }

    public String getLibrarainPassword() {
        return super.getPassword();
    }

    @Override
    public String toString() {
        return "Name:" + getLibrarianName() + ","
                + "Id:" + getLibrarianId() + ","
                + "Username:" + getLibrarianUsername()+","
                + "Password:" + getLibrarainPassword();
    }

    public static Librarian fromString(String line) {
        String[] parts = line.split(",");
        if (parts.length != 4) {
            return null;
        }
        return new Librarian(parts[0], parts[1], parts[2], parts[3]);
    }

    public String toFileString() {
        return getLibrarianName() + ","
                + getLibrarianId() + ","
                + getLibrarianUsername() + ","
                + getLibrarainPassword();
    }
}
