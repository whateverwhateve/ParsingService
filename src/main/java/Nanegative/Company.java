package Nanegative;

import java.util.ArrayList;

public class Company {
    String name;
    ArrayList<NanegativeFeedback> feedbacks;

    public void setName(String name) {
        this.name = name;
    }

    public void setFeedbacks(ArrayList<NanegativeFeedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder(name);
        if (feedbacks.isEmpty())
            out.append(". There are no feedbacks about this company.\n\n");
        else {
            out.append(". Feedbacks about this company:\n");
            for (NanegativeFeedback feedback : feedbacks)
                out.append(feedback.toString()).append("\n");
        }
        return out + "\n";
    }
}
