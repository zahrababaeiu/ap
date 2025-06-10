package ap.mid_project;

public interface Storage {
    void save(Library system) throws Exception;

    Library load() throws Exception;
}


