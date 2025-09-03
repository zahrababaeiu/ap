package finalproject;

public class Student extends Information {
    private boolean active = true; //default

    public Student(String name, String studentId, String username, String password) {
        super(name, studentId, username, password);
    }

    public String getName() {
        return super.getName();
    }

    public String getStudentId() {
        return super.getId();
    }

    public String getUsername() {
        return super.getUserName();
    }

    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String toString() {
        return "Name: " + getName() +
                " | Student ID: " + getStudentId() +
                " | Username: " + getUsername() +
                " | Active: " + (active ? "Yes" : "No");
    }

    public static Student fromString(String line) {
        String[] parts = line.split(",");
        if (parts.length != 5) {
            return null;
        }
        Student student = new Student(parts[0], parts[1], parts[2], parts[3]);
        student.setActive(Boolean.parseBoolean(parts[4]));
        return student;
    }

    public String toFileString() {
        return getName() + "," + getStudentId() + "," + getUsername() + "," + getPassword() + "," + isActive();
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
