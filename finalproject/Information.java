package finalproject;

public class Information {
    private String name;
    private String id;
    private String userName;
    private String password;

    public Information(String name, String id, String userName, String password) {
        this.name = name;
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
