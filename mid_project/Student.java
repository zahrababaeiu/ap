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

    public Student() {
    }

    public Student(String part, String part1, String part2) {
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
        return getFirstName() + "," + getLastName() + "," + getId() + "," + getMajor() + "," + (getDate() == null ? "null" : getDate().toString());
    }

    public void stringForm(String line) {
        String[] parts = line.split(",");
        this.setFirstName(parts[0]);
        this.setLastName(parts[1]);
        this.id = Integer.parseInt(parts[2]);
        this.setMajor(parts[3]);
        String[] dateParts = parts[4].split("/");
        int year = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int day = Integer.parseInt(dateParts[2]);

        this.dateOfMembership = new Date(year, month, day);


    }
}

