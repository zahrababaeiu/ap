package ap.mid_project;

public class Loaner extends Person {
    private int id;

    public Loaner(String firstName, String lastName, int id) {
        super(firstName, lastName);
        this.id = id;
    }

    public void setFirstName(String firstName) {
        super.setFirstName(firstName);
    }

    public void setLastName(String lastName) {
        super.setLastName(lastName);
    }

    public String getFirstName() {
        return super.getFirstName();
    }

    public String getLastName() {
        return super.getLastName();
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return getFirstName() + "," + getLastName() + "," + getId();
    }
}

