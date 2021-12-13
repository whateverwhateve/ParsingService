package Yandex;

import Parsing.Parser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class YandexParser implements Parser<ArrayList<String>> {
    @Override
    public ArrayList<String> Parse(Document document) {
        return GetImagesLinks(document);
    }

    public static ArrayList<String> GetImagesLinks(Document document) {
        ArrayList<String> imagesLinks = new ArrayList<>();
        Elements images = document.getElementsByClass("serp-item");
        for (Element image : images){
            String data = image.attr("data-bem");
            String origin = data.substring(data.indexOf("origin"));
            String url = origin.substring(origin.indexOf("url") + 6);
            String pathImage = url.substring(0, url.indexOf("\""));
            imagesLinks.add(pathImage);
        }
        return imagesLinks;
    }
}