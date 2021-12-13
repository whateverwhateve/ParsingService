package LeroyMerlin;

public class LeroyMerlinFeedback {
    String author, rating, about, text;

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        String out = author + ", rating " + rating + "\n" + "Feedback: " + text + "\n";
        if (!about.isEmpty())
            out += about + "\n";
        return out;
    }
}