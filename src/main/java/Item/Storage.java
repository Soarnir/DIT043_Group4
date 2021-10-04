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

    private static final HashMap<String, Item> itemMap = new HashMap<>();

    public static HashMap<String, Item> getItemMap(){
        return itemMap;
    }

    //*********************************ITEMS*********************************//
    /*
     * I (Kevin) has implemented this as discussed but do not need it for User Stories 2.1 - 2.3
     * Oliver, feel free to use if needed for your user stories in Epic feature 2. If not, we can delete it.
     */
    public static boolean checkForUsedID(String itemID) {
        return usedIDs.contains(itemID);
    }

    public static String createItem(String itemID, String itemName, double itemPrice) {
        if (itemID.equals("") || checkForUsedID(itemID) || itemName.equals("") || (itemPrice <= 0)) {
            MenuUtility.sout("Problem: ID: " + itemID + " |Name: " + itemName + " |itemPrice: " + itemPrice + " |Exists: " + checkForUsedID(itemID));
            return "Invalid data for item."; // Not sure about this part yet.
        } else {
            MenuUtility.sout("Created: ID: " + itemID + " |Name: " + itemName + " |itemPrice: " + itemPrice + " |Exists: " + checkForUsedID(itemID));
            usedIDs.add(itemID);
            itemMap.put(itemID, new Item(itemID, itemName, BigDecimal.valueOf(itemPrice)));
            return "Item " + itemID + " was registered successfully.";
        }
    }

    public static Item getItem(String itemID) {
        return itemMap.get(itemID);
    }

    public static String removeItem(String itemID) {
        if (checkForUsedID(itemID) && getItem(itemID) != null) {
            usedIDs.remove(itemID);
            itemMap.remove(itemID);
            MenuUtility.sout("Item " + itemID + " was successfully removed.");
            return "Item " + itemID + " was successfully removed.";
        }
        MenuUtility.sout("Item " + itemID + " could not be removed.");
        return "Item " + itemID + " could not be removed.";
    }

    public static String printAllItems() {
        if (itemMap.isEmpty()) {
            return "No items registered yet";
        } else {
            StringBuilder stringBuilder = new StringBuilder("All registered items:" + MenuUtility.EOL);
            for (int i = 0; i < usedIDs.size(); i++) {
                Item item = getItem(usedIDs.get(i));
                stringBuilder.append(item.getItemID() + ": " + item.getItemName() + ". " + item.getItemPrice() + " SEK" + MenuUtility.EOL);
            }
            return stringBuilder.toString();
        }
    }

    public static String updateItem(String itemID, String newName) {
        if (!checkForUsedID(itemID)) {
            return "Item " + itemID + " was not registered yet.";
        } else if (newName.equals("")) {
            return "Invalid data for item.";
        } else {
            MenuUtility.sout("Item: " + itemID + " name: " + getItem(itemID).getItemName() + " | new name: " + newName);
            getItem(itemID).updateItemName(newName);
            return "Item " + itemID + " was updated successfully.";
        }
    }

    public static String updateItem(String itemID, BigDecimal newPrice) {
        if (!checkForUsedID(itemID)) {
            return "Item " + itemID + " was not registered yet.";
        } else if (newPrice.doubleValue() <= 0) {
            return "Invalid data for item.";
        } else {
            MenuUtility.sout("Item: " + itemID + " price: " + getItem(itemID).getItemPrice() + " | new price: " + newPrice);
            getItem(itemID).updateItemPrice(newPrice);
            return "Item " + itemID + " was updated successfully.";
        }
    }

    //*********************************REVIEWS*********************************//

    public static String createReview(String itemID, String reviewText, int reviewGrade) {
        if (!checkForUsedID(itemID)) {
            return "Item " + itemID + " was not registered yet.";
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

    public static String createReview(String itemID, int reviewGrade) {
        if (!usedIDs.contains(itemID)) {
            return "Item <ID> was not registered yet.";
        } else if (reviewGrade < 1 || reviewGrade > 5) {
            return "Grade values must be between 1 and 5.";
        } else {
            getReviewList(itemID).add(new Review(reviewGrade));
            return "Your item review was registered successfully.";
        }
    }

    public static List<Review> getReviewList(String itemID) {
        return getItem(itemID).getReviewList();
    }

    public static List<String> getItemComments(String itemID) {
        List<String> itemComments = new ArrayList<>();
        for (Review review : getReviewList(itemID)) {
            if (review.getReviewText() != null) {
                itemComments.add(review.getReviewText());
            }
        }
        return itemComments;
    }

    public static double getItemMeanGrade(String itemID) {
        BigDecimal sum = new BigDecimal(0).setScale(1, RoundingMode.DOWN);
        for (Review review : getReviewList(itemID)) {
            sum = sum.add(review.getReviewGrade());
        }
        return (sum.divide(BigDecimal.valueOf(getReviewList(itemID).size()), RoundingMode.DOWN)).doubleValue();
    }

    public static Review getReview(String itemID, int reviewIndex) {
        return getItem(itemID).getReviewList().get(reviewIndex);
    }

    public static String printReview(String itemID, int reviewIndex) {
        int reviewListSize = getReviewList(itemID).size();
        if (!checkForUsedID(itemID)) {
            return "Item " + itemID + " was not registered yet.";
        } else if (reviewListSize == 0) {
            return "Item " + getItem(itemID).getItemName() + "";
        } else if (reviewIndex < 1 || reviewIndex > reviewListSize) {
            return "Invalid review number. Choose between 1 and " + reviewListSize + ".";
        } else {
            Review review = getReview(itemID, reviewIndex);
            return "Grade: " + review.getReviewGrade() + "." + review.getReviewText() + MenuUtility.EOL;
        }
    }

    public static String printAllItemReviews(String itemID) {
        StringBuilder sb = new StringBuilder();
        Item item = getItem(itemID);
        sb.append("Review(s) for " + item.getItemID() + ": " + item.getItemName() + ". " + item.getItemPrice() + " SEK." + MenuUtility.EOL);
        for (Review review : getReviewList(itemID)) {
            sb.append("Grade: " + review.getReviewGrade() + "." + review.getReviewText() + MenuUtility.EOL);
        }
        return sb.toString();
    }

    public static String printAllReviews() {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        return sb.toString();
    }

    //*********************************TRANSACTIONS*********************************//

    public static double buyItem(String itemID, int amount) {
        if (checkForUsedID(itemID)) {
            Transaction transaction = new Transaction(getItem(itemID), amount);
            getTransactionList(itemID).add(transaction);
            return transaction.getTransactionCost().doubleValue();
        } else {
            return -1.0;
        }
    }

    public static List<Transaction> getTransactionList(String itemID) {
        return getItem(itemID).getTransactionList();
    }


}
