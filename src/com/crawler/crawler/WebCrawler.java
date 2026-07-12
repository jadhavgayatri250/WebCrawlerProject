package com.crawler.crawler;

import com.crawler.dao.URLDAO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashSet;
import java.util.Set;

public class WebCrawler {

    private final URLDAO dao = new URLDAO();

    private final Set<String> visited = new HashSet<>();

    private static final int MAX_URLS = 10;

    private int totalUrls = 0;

    public void crawl(String url, int depth) {

        if (depth <= 0)
            return;

        if (totalUrls >= MAX_URLS)
            return;

        if (!visited.add(url))
            return;

        try {

            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0")
                    .timeout(10000)
                    .ignoreHttpErrors(true)
                    .followRedirects(true)
                    .get();

            System.out.println("--------------------------------");
            System.out.println("Page Title : " + doc.title());
            System.out.println("Website : " + url);
            System.out.println("--------------------------------");

            Elements links = doc.select("a[href]");

            for (Element element : links) {

                if (totalUrls >= MAX_URLS)
                    break;

                String foundUrl = element.absUrl("href").trim();

                if (foundUrl.isEmpty())
                    continue;

                if (!(foundUrl.startsWith("http://") ||
                        foundUrl.startsWith("https://")))
                    continue;

                if (foundUrl.contains("javascript") ||
                        foundUrl.contains("mailto:") ||
                        foundUrl.contains("tel:") ||
                        foundUrl.contains("#"))
                    continue;

                if (!visited.add(foundUrl))
                    continue;

                try {
                    dao.saveURL(foundUrl);
                } catch (Exception ignored) {
                }

                totalUrls++;

                System.out.println(totalUrls + ". " + foundUrl);

                // Recursive Crawling
                crawl(foundUrl, depth - 1);
            }

            System.out.println();
            System.out.println("Total URLs Saved : " + totalUrls);

        } catch (Exception e) {

            System.out.println("Error : " + e.getMessage());
        }
    }
}