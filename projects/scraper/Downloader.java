package ap.projects.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

public class Downloader {

    private static final Pattern IMG_EXT = Pattern.compile("(?i)\\.(jpg|jpeg|png|gif|bmp|webp)$");
    private static final Pattern SONG_EXT = Pattern.compile("(?i)\\.(mp3|wav|ogg|m4a)$");

    private final String domainHost;
    private final Path htmlDir;
    private final Path imageDir;
    private final Path songDir;
    private final ExecutorService executor;

    public Downloader(String domain, String outputRoot, int threadCount) throws IOException {
        this.domainHost = domain.replaceFirst("^https?://", "");
        Path root = Paths.get(outputRoot).toAbsolutePath();
        this.htmlDir = root.resolve(Conf.HTML_DIR);
        this.imageDir = root.resolve(Conf.IMAGE_DIR);
        this.songDir = root.resolve(Conf.SONG_DIR);
        createDirs();

        if (threadCount > 0) {
            executor = Executors.newFixedThreadPool(threadCount);
        } else {
            executor = null;
        }
    }

    public void downloadPage(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        saveHtml(url, doc.outerHtml());
        extractAndDownloadAssets(doc);
    }

    private void createDirs() throws IOException {
        Files.createDirectories(htmlDir);
        Files.createDirectories(imageDir);
        Files.createDirectories(songDir);
    }

    private void saveHtml(String url, String html) throws IOException {
        String safeName = sanitize(url) + ".html";
        Files.write(htmlDir.resolve(safeName), html.getBytes());
    }

    private void extractAndDownloadAssets(Document doc) {
        Elements imgs = doc.select("img[src]");
        for (Element img : imgs) {
            String src = img.absUrl("src");
            if (!src.isEmpty()) scheduleDownload(src, imageDir);
        }

        Elements audios = doc.select("audio[src], audio source[src]");
        for (Element audio : audios) {
            String src = audio.absUrl("src");
            if (!src.isEmpty()) scheduleDownload(src, songDir);
        }

        Elements anchors = doc.select("a[href]");
        for (Element a : anchors) {
            String href = a.absUrl("href");
            if (IMG_EXT.matcher(href).find()) {
                scheduleDownload(href, imageDir);
            } else if (SONG_EXT.matcher(href).find()) {
                scheduleDownload(href, songDir);
            }
        }
    }

    private void scheduleDownload(String fileUrl, Path targetDir) {
        Runnable task = () -> downloadBinary(fileUrl, targetDir);
        if (executor != null) {
            executor.submit(task);
        } else {
            task.run();
        }
    }

    private void downloadBinary(String fileUrl, Path targetDir) {
        try (InputStream in = new URL(fileUrl).openStream()) {
            String filename = Paths.get(new URI(fileUrl).getPath()).getFileName().toString();
            Files.copy(in, targetDir.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Downloaded: " + filename);
        } catch (Exception ex) {
            System.err.println("Error downloading: " + fileUrl + " - " + ex.getMessage());
        }
    }

    private static String sanitize(String url) {
        return url.replaceFirst("^https?://", "").replaceAll("[^a-zA-Z0-9]", "_");
    }

    public boolean isSameDomain(String url) {
        return url.contains(domainHost);
    }

    public void shutdown() {
        if (executor != null) {
            executor.shutdown();
        }
    }

    public void downloadIfValid(String url) {
        if (isSameDomain(url)) {
            try {
                downloadPage(url);
                System.out.println("Downloaded valid URL: " + url);
            } catch (IOException e) {
                System.err.println("Failed to download URL: " + url + " - " + e.getMessage());
            }
        } else {
            System.out.println("URL is not in the domain, skipped: " + url);
        }
    }

}
