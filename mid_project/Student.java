package ap.mid_project;

public class Student extends Person {
    private final int id;
    private String major;
    private Date dateOfMembership;

    public Student(String firstName, String lastName, int id, String major,Date dateOfMembership) {
        super(firstName, lastName);
        this.id = id;
        this.major = major;
    }

    public int getId() {
        return id;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMajor() {
        return major;
    }

    public void setDate(Date date) {
        this.dateOfMembership = date;
    }

    public Date getDate() {
        return dateOfMembership;
    }
    @Override
    public String toString() {
        return "First Name: " + getFirstName() + ", Last Name: " + getLastName() + ", ID: " + getId() + ", Major: " + getMajor() + ", Date of membership: " + getDate();
    }
}

