package ap.projects.scraper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ConfigReader {
    private int threadCount = 0;

    public ConfigReader(Path path) throws IOException {
        List<String> lines = Files.readAllLines(path);
        for (String line : lines) {
            line = line.trim();
            if (line.startsWith("thread-count=")) {
                String val = line.substring("thread-count=".length());
                threadCount = Integer.parseInt(val);
            }
        }
    }

    public int getThreadCount() {
        return threadCount;
    }
}
