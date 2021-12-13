package Parsing;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class HtmlLoader {
    String url;

    public HtmlLoader(String url) {
        this.url = url;
    }

    public Document GetSourceByPage() throws IOException {
        return Jsoup.connect(url).get();
    }

    public Document GetSourceByPageId(int id) throws IOException {
        String currentUrl = url.replace("{CurrentId}", Integer.toString(id));
        return Jsoup.connect(currentUrl).get();
    }
}