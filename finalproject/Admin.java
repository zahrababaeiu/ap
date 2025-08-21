package finalproject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Admin {

    private List<Librarian> librarians;
    private final String LIBRARIANS_FILE = "librarians.txt";

    public Admin() {
        librarians = new ArrayList<>();
        loadLibrarians();
    }

    private void loadLibrarians() {
        File file = new File(LIBRARIANS_FILE);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                Librarian librarian = Librarian.fromString(line);
                if (librarian != null) {
                    librarians.add(librarian);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading librarians: " + e.getMessage());
        }
    }

    private void saveLibrariantoFile(Librarian librarian) {
        try {
            FileWriter writer = new FileWriter(LIBRARIANS_FILE , true);
            for (Librarian lib : librarians) {
                writer.write(lib.toFileString());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving books to file: " + e.getMessage());
        }
    }
}
