package Item;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Item {

    private final String itemID;
    private String itemName;
    private BigDecimal itemPrice;
    private List<Review> reviewList;
    private int numOfReviews;
    private double meanGrade; // For User Story 3.8
    private List<Transaction> transactionList;

    public Item(String itemID, String itemName, BigDecimal itemPrice) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemPrice = itemPrice.setScale(2, RoundingMode.FLOOR);
        this.reviewList = new ArrayList<>();
        this.transactionList = new ArrayList<>();
    }

    public String getItemID(){ return this.itemID; }

    public String getItemName(){
        return this.itemName;
    }

    public BigDecimal getItemPrice(){
        return this.itemPrice;
    }

    public void updateItemName(String newName) {
        this.itemName = newName;
    }

    public void updateItemPrice(BigDecimal newPrice) {
        this.itemPrice = newPrice;
    }

    public String printItem() {
        return itemID + ": " + itemName + ". " + itemPrice + " SEK";
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    public int getNumOfReviews() {
        return numOfReviews;
    }

    public void increaseNumOfReviews(){
        this.numOfReviews += 1;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }
}
