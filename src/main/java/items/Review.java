package items;

public class Review {

    private final int REVIEW_GRADE;
    // Changed back to non-final as second constructor should be implemented
    private String reviewText;


    public Review(String reviewText, int reviewGrade) {
        this.reviewText = reviewText;
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
        if (reviewText == null) {
            return "Grade: " + REVIEW_GRADE + ".";
        } else {
            return "Grade: " + REVIEW_GRADE + "." + reviewText;
        }
    }
}
