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

    public Item(String itemID, String itemName, BigDecimal itemPrice) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemPrice = itemPrice.setScale(2, RoundingMode.DOWN);
        this.reviewList = new ArrayList<>();
    }

    public String getItemID(){ return this.itemID; }

    public String getItemName(){
        return this.itemName;
    }

    public BigDecimal getItemPrice(){
        return this.itemPrice;
    }

    public void updateItem(String newName, BigDecimal newPrice) {

        if (newName.equals("") || newPrice.intValue() <= 0) {
            System.out.println("Invalid data for item.");
        } else {
            this.itemName = newName;
            this.itemPrice = newPrice;
        }

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
}
