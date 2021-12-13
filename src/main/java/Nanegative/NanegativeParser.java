package Nanegative;

import Parsing.HtmlLoader;
import Parsing.Parser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class NanegativeParser implements Parser<ArrayList<Company>> {
    @Override
    public ArrayList<Company> Parse(Document document) throws IOException {
        return GetCompanyInfo(document);
    }

    public static ArrayList<Company> GetCompanyInfo(Document document) throws IOException {
        ArrayList<Company> companiesList = new ArrayList<>();
        Elements companies = document.getElementsByClass("find-list-box");
        for (Element companyElement : companies) {
            Company company = new Company();
            company.setName(companyElement.getElementsByClass("ss").text().substring(9));
            String companyLink = companyElement.getElementsByClass("ss").attr("href");
            company.setFeedbacks(GetFeedbacks(companyLink));
            companiesList.add(company);
        }
        return companiesList;
    }

    static ArrayList<NanegativeFeedback> GetFeedbacks(String companyPath) throws IOException {
        ArrayList<NanegativeFeedback> allFeedbacks = new ArrayList<>();
        HtmlLoader loader = new HtmlLoader(NanegativeSettings.BASE_URL.substring(0, 21) + companyPath);
        Document document = loader.GetSourceByPage();
        Elements feedbacks = document.getElementsByClass("reviewers-box");
        for (Element feedbackE : feedbacks) {
            NanegativeFeedback feedback = new NanegativeFeedback();
            feedback.setAuthor(feedbackE.getElementsByAttributeValue("itemprop", "author").text());
            feedback.setRating(feedbackE.getElementsByAttributeValue("itemprop", "ratingValue").text());
            feedback.setPositives(feedbackE.getElementsByAttributeValue("itemprop", "pro").text());
            feedback.setNegatives(feedbackE.getElementsByAttributeValue("itemprop", "contra").text());
            feedback.setText(feedbackE.getElementsByAttributeValue("itemprop", "reviewBody").text());
            allFeedbacks.add(feedback);
        }
        return allFeedbacks;
    }
}