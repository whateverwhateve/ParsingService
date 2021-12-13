package Nanegative;

public class NanegativeFeedback {
    String author, rating, positives, negatives, text;

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setPositives(String positives) {
        this.positives = positives;
    }

    public void setNegatives(String negatives) {
        this.negatives = negatives;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return author + ", rating " + rating + "\nPositives: " + positives + "\nNegatives: " + negatives + "\nReport: " + text + "\n";
    }
}
