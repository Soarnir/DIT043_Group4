package Item;

import java.math.BigDecimal;

public class Review {

    private String reviewText;
    private final BigDecimal reviewGrade;

    public Review(String reviewText, int reviewGrade) {
        if (!reviewText.equals("")) {
            this.reviewText = reviewText;
        }
        this.reviewGrade = BigDecimal.valueOf(reviewGrade);
    }

    public Review(int reviewGrade) {
        this.reviewGrade = BigDecimal.valueOf(reviewGrade);
    }

    public BigDecimal getReviewGrade() {
        return reviewGrade;
    }

    public String getReviewText() {
        return reviewText;
    }
}
