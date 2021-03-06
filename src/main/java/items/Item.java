package items;

import utility.MenuUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Item {

    private final String ITEM_ID;
    private String itemName;
    private double itemPrice;
    private final List<Review> reviewList;
    private int numOfReviews;

    public Item(String ITEM_ID, String itemName, double itemPrice) {
        this.ITEM_ID = ITEM_ID;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.reviewList = new ArrayList<>();
    }

    public String getItemID() { return this.ITEM_ID; }

    public String getItemName() {
        return this.itemName;
    }

    public double getItemPrice() {
        return this.itemPrice;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public int getNumOfReviews() {
        return numOfReviews;
    }

    public void updateItemName(String newName) {
        this.itemName = newName;
    }

    public void updateItemPrice(double newPrice) {
        this.itemPrice = newPrice;
    }

    public void increaseNumOfReviews() {
        this.numOfReviews += 1;
    }

    @Override
    public String toString() {
        return ITEM_ID + ": " + itemName + ". " + MenuUtility.doubleFormat(itemPrice) + " SEK";
    }

    @Override
    public boolean equals(Object item) {
        if (this == item) {
            return true;
        }
        if (item == null || getClass() != item.getClass()) {
            return false;
        }
        Item itemChecked = (Item) item;
        return ITEM_ID.equals(itemChecked.ITEM_ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ITEM_ID);
    }
}