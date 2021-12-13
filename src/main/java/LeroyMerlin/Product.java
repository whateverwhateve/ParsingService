package LeroyMerlin;

import java.util.ArrayList;

public class Product {
    String name;
    ArrayList<LeroyMerlinFeedback> feedbacks;

    public void setName(String name) {
        this.name = name;
    }

    public void setFeedbacks(ArrayList<LeroyMerlinFeedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder(name);
        if (feedbacks.isEmpty())
            out.append(". There are no feedbacks about this product.\n\n");
        else {
            out.append(".\nFeedbacks about this product:\n");
            for (LeroyMerlinFeedback feedback : feedbacks)
                out.append(feedback.toString()).append("\n");
        }
        return out + "\n";
    }
}