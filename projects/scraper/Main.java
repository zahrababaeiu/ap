package ap.projects.scraper;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        String domainAddress = Conf.DOMAIN_ADDRESS;
        String savePath = Conf.SAVE_DIRECTORY;

        Path configPath = Paths.get("config.txt");
        ConfigReader config = new ConfigReader(configPath);
        int threadCount = config.getThreadCount();

        DomainHtmlScraper domainHtmlScraper = new DomainHtmlScraper(domainAddress, savePath, threadCount);
        domainHtmlScraper.start();

        Path inputPath = Paths.get("pages");
        Path outputFile = Paths.get("images.txt");
        ExtractImagesLinks extractor = new ExtractImagesLinks();
        if (inputPath.toFile().isDirectory()) {
            extractor.extractFromDirectory(inputPath);
        } else {
            extractor.extractFromFile(inputPath);
        }
        extractor.saveLinksToFile(outputFile);
        System.out.println("Count of links:" + extractor.getLinkCount());
        Downloader downloader = new Downloader("znu.ac.ir", "_scraper", threadCount);

        downloader.downloadIfValid("https://www.znu.ac.ir/znujrnls/test.html");
        downloader.downloadIfValid("https://mail.znu.ac.ir/login/test.html");
        downloader.downloadIfValid("https://google.com");

        downloader.shutdown();
    }
}
