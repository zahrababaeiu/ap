package finalproject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Admin {

    private List<Librarian> librarians;
    private final String LIBRARIANS_FILE = "E:\\librarians.txt";

    public Admin() {
        librarians = new ArrayList<>();
        loadLibrarians();
    }

    public List<Librarian> loadLibrarians() {
        List<Librarian> librarians = new ArrayList<>();
        File file = new File(LIBRARIANS_FILE);
        if (!file.exists()) {
            return librarians;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Librarian librarian = Librarian.fromString(line);
                if (librarian != null) librarians.add(librarian);
            }
        } catch (IOException e) {
            System.out.println("Error loading librarians: " + e.getMessage());
        }
        return librarians;
    }


    public void saveLibrariantoFile(Librarian librarian) {
        try (FileWriter writer = new FileWriter(LIBRARIANS_FILE, true)) {
            writer.write(librarian.toFileString() + "\n");
        } catch (IOException e) {
            System.out.println("Error saving librarian: " + e.getMessage());
        }
    }

}
