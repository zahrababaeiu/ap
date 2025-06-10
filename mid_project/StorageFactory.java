package ap.mid_project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class StorageFactory {
    public static Storage createStorage() {
        try (BufferedReader reader = new BufferedReader(new FileReader("config.txt"))) {
            String line = reader.readLine();
            if (line == null) return null;

            String[] parts = line.split("=");
            if (parts.length != 2) return null;

            String type = parts[1].toLowerCase();
            return switch (type) {
                case "tabsplit" -> new TabSplit("library_data.txt");
                case "json" -> new Json("library.json");
                case "sqlite" -> new SQlite("library.db");
                default -> null;
            };
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
