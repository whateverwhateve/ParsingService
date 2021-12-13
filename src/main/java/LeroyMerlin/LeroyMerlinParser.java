package LeroyMerlin;

import Parsing.HtmlLoader;
import Parsing.Parser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class LeroyMerlinParser implements Parser<ArrayList<Product>> {
    @Override
    public ArrayList<Product> Parse(Document document) throws IOException {
        return GetProductInfo(document);
    }

    public static ArrayList<Product> GetProductInfo(Document document) throws IOException {
        ArrayList<Product> productsList = new ArrayList<>();
        Elements products = document.getElementsByClass("phytpj4_plp largeCard");
        for (Element productE : products) {
            Product product = new Product();
            product.setName(productE.getElementsByClass("t9jup0e_plp p1h8lbu4_plp").text());
            String productLink = productE.getElementsByClass("bex6mjh_plp b1f5t594_plp iypgduq_plp nf842wf_plp").attr("href");
            product.setFeedbacks(GetFeedbacks(productLink));
            productsList.add(product);
        }
        return productsList;
    }

    static ArrayList<LeroyMerlinFeedback> GetFeedbacks(String productPath) throws IOException {
        ArrayList<LeroyMerlinFeedback> allFeedbacks = new ArrayList<>();
        HtmlLoader loader = new HtmlLoader(LeroyMerlinSettings.BASE_URL.substring(0, 22) + productPath + "/#nav-reviews");
        Document document = loader.GetSourceByPage();
        Elements feedbacks = document.getElementsByAttributeValue("itemprop", "review");
        for (Element feedbackE : feedbacks) {
            LeroyMerlinFeedback feedback = new LeroyMerlinFeedback();
            feedback.setAuthor(feedbackE.getElementsByAttributeValue("itemprop", "name").text());
            feedback.setRating(feedbackE.getElementsByAttributeValue("itemprop", "ratingValue").attr("content").substring(0, 1));
            Elements about = feedbackE.getElementsByClass("term-group");
            if (about.size() == 2)
                feedback.setAbout(about.get(0).text() + "\n" + about.get(1).text());
            else
                feedback.setAbout(about.text());
            feedback.setText(feedbackE.getElementsByAttributeValue("itemprop", "description").text());
            allFeedbacks.add(feedback);
        }
        return allFeedbacks;
    }
}