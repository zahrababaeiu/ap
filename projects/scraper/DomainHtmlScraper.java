package ap.projects.scraper;

import ap.projects.scraper.fetcher.HtmlFetcher;
import ap.projects.scraper.parser.HtmlParser;
import ap.projects.scraper.store.HtmlFileManager;

import java.io.IOException;
import java.util.*;

public class DomainHtmlScraper {

    private final String domainAddress;
    private final String domainHost;
    private final Queue<String> queue;
    private final Set<String> visited;

    private final HtmlFileManager htmlFileManager;
    private final Downloader downloader;

    public DomainHtmlScraper(String domainAddress, String savePath) throws IOException {
        this.domainAddress = domainAddress;
        this.domainHost = domainAddress.replaceFirst("^https?://", "");
        this.queue = new LinkedList<>();
        this.visited = new HashSet<>();
        this.htmlFileManager = new HtmlFileManager(savePath);
        this.downloader = new Downloader(domainAddress, savePath);
    }

    public void start() throws IOException, InterruptedException {
        queue.add(domainAddress);
        int counter = 1;

        while (!queue.isEmpty()) {
            String url = queue.remove();
            if (visited.contains(url)) continue;
            visited.add(url);

            try {
                List<String> htmlLines = HtmlFetcher.fetchHtml(url);
                htmlFileManager.save(htmlLines);
                downloader.downloadPage(url);
                List<String> newUrls = HtmlParser.getAllUrlsFromList(htmlLines);
                for (String u : newUrls) {
                    if (u.contains(domainHost) && !visited.contains(u)) {
                        queue.add(u);
                    }
                }

                System.out.println("[" + counter++ + "] " + url + " processed (queue size: " + queue.size() + ").");

                Thread.sleep(Conf.DELAY_SECONDS * 1000L);
            } catch (Exception e) {
                System.err.println("ERROR " + url + " -> " + e.getMessage());
            }
        }

        System.out.println("Operation complete");
    }
}
