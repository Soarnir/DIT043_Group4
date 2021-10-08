package Item;

import utility.MenuUtility;

import java.util.ArrayList;
import java.util.List;

public class Item {

    private final String ITEM_ID;
    private String itemName;
    private double itemPrice;
    private List<Review> reviewList;
    private int numOfReviews; //This implementation needs to be discussed
    private double meanGrade; // For User Story 3.8
    private List<Transaction> transactionList;

    public Item(String ITEM_ID, String itemName, double itemPrice) {
        this.ITEM_ID = ITEM_ID;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.reviewList = new ArrayList<>();
        this.transactionList = new ArrayList<>();
    }

    public String getItemID(){ return this.ITEM_ID; }

    public String getItemName(){
        return this.itemName;
    }

    public double getItemPrice(){
        return this.itemPrice;
    }

    public void updateItemName(String newName) {
        this.itemName = newName;
    }

    public void updateItemPrice(double newPrice) {
        this.itemPrice = newPrice;
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

    @Override
    public String toString() {
        return ITEM_ID + ": " + itemName + ". " + MenuUtility.doubleFormat(itemPrice) + " SEK";
    }
}