package ap.projects.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.HashSet;
import java.util.Set;

public class ExtractImagesLinks {
    private final Set<String> uniqueUrls = new HashSet<>();
    public void extractFromFile(Path htmlFile) {
        try {
            Document doc = Jsoup.parse(htmlFile.toFile(), "UTF-8");
            Elements imgTags = doc.select("img");

            for (Element img : imgTags) {
                String src = img.attr("abs:src");
                if (src.isEmpty()) {
                    src = img.attr("src");
                }
                if (!src.isEmpty()) {
                    uniqueUrls.add(src);
                }
            }
            System.out.println(htmlFile.getFileName());
        } catch (IOException e) {
            System.err.println("Error" + htmlFile.getFileName() + ": " + e.getMessage());
        }
    }
    public void extractFromDirectory(Path dirPath) {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dirPath, "*.html")) {
            for (Path entry : stream) {
                extractFromFile(entry);
            }
        } catch (IOException e) {
            System.err.println("Error" + e.getMessage());
        }
    }
    public void saveLinksToFile(Path outputPath) {
        try (BufferedWriter writer = Files.newBufferedWriter(outputPath)) {
            for (String url : uniqueUrls) {
                writer.write(url);
                writer.newLine();
            }
            System.out.println("Save Secussesfuly" + outputPath);
        } catch (IOException e) {
            System.err.println("Error" + e.getMessage());
        }
    }
    public int getLinkCount() {
        return uniqueUrls.size();
    }
}
