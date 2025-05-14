package ap.mid_project;

public class LibraryManager extends Person {
    private String educationLevel;

    public LibraryManager(String firstName, String lastName, String educationLevel) {
        super(firstName, lastName);
        this.educationLevel = educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public String getEducationLevel() {
        return educationLevel;
    }
}

