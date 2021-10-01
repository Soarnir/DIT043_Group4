package Item;

import utility.MenuUtility;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class Storage {

    // No getter has been implemented because only the Storage class should be accessing usedIDs.
    private static final List<String> usedIDs = new ArrayList<>();

    private final HashMap<String, Item> itemMap = new HashMap<>();

    public HashMap<String, Item> getItemMap(){
        return itemMap;
    }

    //*********************************ITEMS*********************************//
    /*
     * I (Kevin) has implemented this as discussed but do not need it for User Stories 2.1 - 2.3
     * Oliver, feel free to use if needed for your user stories in Epic feature 2. If not, we can delete it.
     */
    public boolean checkForUsedID(String itemID) {
        return usedIDs.contains(itemID);
    }

    public String createItem(String itemID, String itemName, double itemPrice) {
        if (itemID.equals("") || checkForUsedID(itemID) || itemName.equals("") || (itemPrice <= 0)) {
            System.out.println("Problem: ID: " + itemID + " |Name: " + itemName + " |itemPrice: " + itemPrice + " |Exists: " + checkForUsedID(itemID));
            return "Invalid data for item."; // Not sure about this part yet.
        } else {
            Item item = new Item(itemID, itemName, BigDecimal.valueOf(itemPrice));
            System.out.println("Created: ID: " + itemID + " |Name: " + itemName + " |itemPrice: " + itemPrice + " |Exists: " + checkForUsedID(itemID));
            usedIDs.add(itemID);
            itemMap.put(itemID, item);
            return "Item " + itemID + " was registered successfully.";
        }
    }

    public Item getItem(String itemID) {
        return itemMap.get(itemID);
    }

    public String createReview(String itemID, String reviewText, int reviewGrade) {
        if (!checkForUsedID(itemID)) {
            return "Item <ID> was not registered yet.";
        } else if (reviewGrade < 1 || reviewGrade > 5) {
            return "Grade values must be between 1 and 5.";
        } else {
            if (reviewText.equals("")) {
                getReviewList(itemID).add(new Review(reviewGrade));
            } else {
                getReviewList(itemID).add(new Review(reviewText, reviewGrade));
            }
            return "Your item review was registered successfully.";
        }
    }

    public String removeItem(String itemID) {
        if (checkForUsedID(itemID) && getItem(itemID) != null) {
            usedIDs.remove(itemID);
            itemMap.remove(itemID);
            return "Item " + itemID + " was successfully removed.";
        }
        return "Item " + itemID + " could not be removed.";
    }


    public String printAllItems() {
        StringBuilder stringBuilder = new StringBuilder("All registered items:" + MenuUtility.EOL);
        for (int i = 0; i < usedIDs.size(); i++) {
            Item item = getItem(usedIDs.get(i));
            stringBuilder.append(item.getItemID() + ": " + item.getItemName() + ". " + item.getItemPrice() + " SEK" + MenuUtility.EOL);
        }
        return stringBuilder.toString();
    }

    //*********************************REVIEWS*********************************//

    public String createReview(String itemID, int reviewGrade) {
        if (!usedIDs.contains(itemID)) {
            return "Item <ID> was not registered yet.";
        } else if (reviewGrade < 1 || reviewGrade > 5) {
            return "Grade values must be between 1 and 5.";
        } else {
            getReviewList(itemID).add(new Review(reviewGrade));
            return "Your item review was registered successfully.";
        }
    }

    public List<Review> getReviewList(String itemID) {
        return itemMap.get(itemID).getReviewList();
    }

    public List<String> getItemComments(String itemID) {
        List<String> itemComments = new ArrayList<>();
        for (Review review : getReviewList(itemID)) {
            if (review.getReviewText() != null) {
                itemComments.add(review.getReviewText());
            }
        }
        return itemComments;
    }

    public double getItemMeanGrade(String itemID) {
        BigDecimal sum = new BigDecimal(0).setScale(1, RoundingMode.DOWN);
        for (Review review : getReviewList(itemID)) {
            sum = sum.add(review.getReviewGrade());
        }
        return (sum.divide(BigDecimal.valueOf(getReviewList(itemID).size()), RoundingMode.DOWN)).doubleValue();
    }

    public Review getReview(String itemID, int reviewIndex) {
        return itemMap.get(itemID).getReviewList().get(reviewIndex);
    }

    public String printAllItemReviews(String itemID) {
        StringBuilder sb = new StringBuilder();
        Item item = getItem(itemID);
        sb.append("Review(s) for " + item.getItemID() + ": " + item.getItemName() + ". " + item.getItemPrice() + " SEK." + MenuUtility.EOL);
        for (Review review : getReviewList(itemID)) {
            sb.append("Grade: " + review.getReviewGrade() + "." + review.getReviewText() + MenuUtility.EOL);
        }
        return sb.toString();
    }

    public String printAllReviews() {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        return sb.toString();
    }

}
