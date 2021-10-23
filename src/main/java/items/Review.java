package items;

public class Review {

    private final int REVIEW_GRADE;
    private String reviewComment;


    public Review(String reviewComment, int reviewGrade) {
        this.reviewComment = reviewComment;
        this.REVIEW_GRADE = reviewGrade;
    }

    public Review(int reviewGrade) {
        this.REVIEW_GRADE = reviewGrade;
    }

    public int getReviewGrade() {
        return REVIEW_GRADE;
    }

    public String getReviewComment() {
        return reviewComment;
    }

    @Override
    public String toString() {
        String returnString;

        if (reviewComment == null) {
            returnString = "Grade: " + REVIEW_GRADE + ".";
        } else {
            returnString = "Grade: " + REVIEW_GRADE + "." + reviewComment;
        }
        return returnString;
    }
}
