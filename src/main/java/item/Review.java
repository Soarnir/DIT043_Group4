package item;

public class Review {

    private final int REVIEW_GRADE;
    // Changed back to non-final as second constructor should be implemented
    private String reviewText;


    public Review(String reviewText, int reviewGrade) {
        this.reviewText = reviewText;
        this.REVIEW_GRADE = reviewGrade;
    }

    public Review(int reviewGrade){
        this.REVIEW_GRADE = reviewGrade;
    }

    public int getReviewGrade() {
        return REVIEW_GRADE;
    }

    public String getReviewText() {
        return reviewText;
    }

    /*
        This needs to be done as methods in ReviewController call toString for printing
        and tests require nothing (not 'null') -K
     */
//    @Override
//    public String toString() {
//        return "Grade: " + REVIEW_GRADE + "." + reviewText;
//    }

    @Override
    public String toString() {
        if (reviewText == null){
            return "Grade: " + REVIEW_GRADE + ".";
        } else {
            return "Grade: " + REVIEW_GRADE + "." + reviewText;
        }
    }
}
