package ap.mid_project;

public class Student extends Person {
    private int id;
    private String major;
    private Date dateOfMembership;

    public Student(String firstName, String lastName, int id, String major, Date dateOfMembership) {
        super(firstName, lastName);
        this.id = id;
        this.major = major;
        this.dateOfMembership = dateOfMembership;
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
        return getFirstName() + "," + getLastName() + "," + getId() + "," + getMajor() + "," +(getDate() == null ? "null" : getDate().toString());
    }
}

