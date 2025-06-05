package ap.projects.scraper;

import java.io.*;
import java.nio.file.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Downloader {
    private final String baseDomain;
    private final String saveFolder;

    public Downloader(String baseDomain, String saveFolder) {
        this.baseDomain = baseDomain;
        this.saveFolder = saveFolder;
    }

    public void downloadIfValid(String urlString) {
        try {
            URL url = new URL(urlString);
            String host = url.getHost();

            if (!host.endsWith(baseDomain)) {
                System.out.println("Skipping (external domain): " + urlString);
                return;
            }

            String folderPath = getFolderPath(url);
            Files.createDirectories(Paths.get(folderPath));

            String fileName = Paths.get(url.getPath()).getFileName().toString();
            if (fileName.isEmpty()) fileName = "index.html";

            try (InputStream in = url.openStream()) {
                Files.copy(in, Paths.get(folderPath, fileName), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Saved to: " + folderPath + "/" + fileName);
            } catch (IOException e) {
                System.out.println("Error saving " + urlString + ": " + e.getMessage());
            }

        } catch (MalformedURLException e) {
            System.out.println("Invalid URL: " + urlString);
        } catch (IOException e) {
            System.out.println("I/O Error: " + e.getMessage());
        }
    }

    private String getFolderPath(URL url) {
        String host = url.getHost();
        String subdomain = host.replace("." + baseDomain, "");

        StringBuilder path = new StringBuilder(saveFolder);
        if (!subdomain.isEmpty() && !subdomain.equals("www")) {
            path.append("/_").append(subdomain);
        }

        String[] parts = url.getPath().split("/");
        for (int i = 1; i < parts.length - 1; i++) {
            if (!parts[i].isEmpty()) {
                path.append("/").append(parts[i]);
            }
        }

        return path.toString();
    }
}
