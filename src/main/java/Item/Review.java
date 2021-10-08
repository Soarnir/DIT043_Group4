package Item;

public class Review {

    private String reviewText;
    private final int REVIEW_GRADE;

    public Review(String reviewText, int reviewGrade) {
        if (!reviewText.equals("")) {
            this.reviewText = reviewText;
        }
        this.REVIEW_GRADE = reviewGrade;
    }

    public Review(int reviewGrade) {
        this.REVIEW_GRADE = reviewGrade;
    }

    public int getReviewGrade() {
        return REVIEW_GRADE;
    }

    public String getReviewText() {
        return reviewText;
    }

    @Override
    public String toString() {
        return "Grade: " + REVIEW_GRADE + "." + reviewText;
    }
}
