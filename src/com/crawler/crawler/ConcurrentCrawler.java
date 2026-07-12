package com.crawler.crawler;

import com.crawler.dao.URLDAO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentCrawler implements Runnable {

    public interface CrawlListener {
        void onUrlFound(String url);
        void onStatus(String status);
        void onCompleted(int totalUrls);
    }

    private final String startUrl;
    private final int maxDepth;
    private final CrawlListener listener;

    private final URLDAO dao = new URLDAO();

    private final Set<String> visited = ConcurrentHashMap.newKeySet();

    private int totalUrls = 0;

    private static final int MAX_URLS = 10;

    public ConcurrentCrawler(String startUrl, int maxDepth,
                             CrawlListener listener) {

        this.startUrl = startUrl;
        this.maxDepth = maxDepth;
        this.listener = listener;
    }

    @Override
    public void run() {

        if (listener != null)
            listener.onStatus("Crawling...");

        crawl(startUrl, maxDepth);

        if (listener != null)
            listener.onCompleted(totalUrls);
    }

    private void crawl(String url, int depth) {

        if (depth <= 0 || totalUrls >= MAX_URLS)
            return;

        try {

            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0")
                    .timeout(10000)
                    .ignoreHttpErrors(true)
                    .followRedirects(true)
                    .get();

            if (listener != null) {
                listener.onUrlFound("--------------------------------");
                listener.onUrlFound("Page Title : " + doc.title());
                listener.onUrlFound("--------------------------------");
            }

            Elements links = doc.select("a[href]");

            for (Element e : links) {

                if (totalUrls >= MAX_URLS)
                    break;

                String link = e.absUrl("href").trim();

                if (link.isEmpty())
                    continue;

                if (!link.startsWith("http"))
                    continue;

                if (visited.contains(link))
                    continue;

                visited.add(link);

                try {
                    dao.saveURL(link);
                } catch (Exception ignored) {
                }

                totalUrls++;

                if (listener != null) {
                    listener.onUrlFound(totalUrls + ". " + link);
                }

            }

            if (listener != null) {
                listener.onUrlFound("");
                listener.onUrlFound("Total URLs Found : " + totalUrls);
                listener.onUrlFound("Crawler Finished Successfully.");
            }

        } catch (Exception ex) {

            if (listener != null) {
                listener.onUrlFound("Error : " + ex.getMessage());
            }
        }
    }
}