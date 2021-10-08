package Item;

public class Review {

    private String reviewText;
    private final int reviewGrade;

    public Review(String reviewText, int reviewGrade) {
        if (!reviewText.equals("")) {
            this.reviewText = reviewText;
        }
        this.reviewGrade = reviewGrade;
    }

    public Review(int reviewGrade) {
        this.reviewGrade = reviewGrade;
    }

    public int getReviewGrade() {
        return reviewGrade;
    }

    public String getReviewText() {
        return reviewText;
    }

    @Override
    public String toString() {
        return "Grade: " + reviewGrade + "." + reviewText;
    }
}
