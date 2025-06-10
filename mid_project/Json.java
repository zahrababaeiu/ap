package ap.mid_project;

import com.google.gson.*;

import java.io.*;

public class Json implements Storage {
    private final String filename;

    public Json(String filename) {
        this.filename = filename;
    }

    @Override
    public void save(Library system) throws IOException {
        try (Writer writer = new FileWriter(filename)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(system, writer);
        }
    }

    @Override
    public Library load() throws IOException {
        try (Reader reader = new FileReader(filename)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, Library.class);
        }
    }
}
