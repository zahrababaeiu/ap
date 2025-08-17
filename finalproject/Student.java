package finalproject;

public class Student {
    private String name;
    private String studentId;
    private String username;
    private String password;

    public Student(String name, String studentId, String username, String password) {
        this.name = name;
        this.studentId = studentId;
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Name: " + name +
                " | Student ID: " + studentId +
                " | Username: " + username;
    }

    public static Student fromString(String line) {
        String[] parts = line.split(",");

        if (parts.length != 4) {
            return null;
        }
        String name = parts[0];
        String studentId = parts[1];
        String username = parts[2];
        String password = parts[3];

        return new Student(name, studentId, username, password);
    }

    public String toFileString() {
        return name + "," + studentId + "," + username + "," + password;
    }


}
