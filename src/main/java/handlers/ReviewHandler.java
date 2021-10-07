package handlers;

import Item.Item;
import Item.Review;
import Item.Storage;
import utility.MenuUtility;

import java.util.ArrayList;
import java.util.List;

public class ReviewHandler {

    Storage storage;

    public ReviewHandler(Storage storage) {
        this.storage = storage;
    }

    public String createReview(String itemID, String reviewText, int reviewGrade) {
        if (!storage.checkForUsedID(itemID)) {
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

    public String createReview(String itemID, int reviewGrade) {
        if (!storage.getUsedIDs().contains(itemID)) {
            return "Item <ID> was not registered yet.";
        } else if (reviewGrade < 1 || reviewGrade > 5) {
            return "Grade values must be between 1 and 5.";
        } else {
            getReviewList(itemID).add(new Review(reviewGrade));
            storage.getItem(itemID).increaseNumOfReviews();
            return "Your item review was registered successfully.";
        }
    }

    public List<Review> getReviewList(String itemID) {
        return storage.getItem(itemID).getReviewList();
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

    // Error handling and making mean grade visible to the user is yet to be implemented.
    public double getItemMeanGrade(String itemID) {
        double sum = 0;
        for (Review review : getReviewList(itemID)) {
            sum += review.getReviewGrade();
        }
        return (sum / getReviewList(itemID).size());
    }

    public Review getReview(String itemID, int reviewIndex) {
        return storage.getItem(itemID).getReviewList().get(reviewIndex);
    }

    public String printReview(String itemID, int reviewIndex) {
        int reviewListSize = getReviewList(itemID).size();
        if (!storage.checkForUsedID(itemID)) {
            return "Item " + itemID + " was not registered yet.";
        } else if (reviewListSize == 0) {
            // Line below was commented out as specs required another print statement.
            // return "Item " + getItem(itemID).getItemName() + "";
            return "Item " + storage.getItem(itemID).getItemName() + " has not been reviewed yet.";
        } else if (reviewIndex < 1 || reviewIndex > reviewListSize) {
            return "Invalid review number. Choose between 1 and " + reviewListSize + ".";
        } else {
            // Should it be Review review = getReview(itemID, (reviewIndex - 1));
            Review review = getReview(itemID, (reviewIndex - 1));
            return "Grade: " + review.getReviewGrade() + "." + review.getReviewText();
        }
    }

    public String printAllItemReviews(String itemID) {
        StringBuilder sb = new StringBuilder();
        int reviewListSize = getReviewList(itemID).size();
        if (!storage.checkForUsedID(itemID)) {
            return "Item " + itemID + " was not registered yet.";
        } else if (reviewListSize == 0) {
            Item item = storage.getItem(itemID);
            sb.append("Review(s) for " + item.getItemID() + ": " + item.getItemName() + ". " + item.getItemPrice() + " SEK." + MenuUtility.EOL);
            sb.append("Item " + storage.getItem(itemID).getItemName() + " has not been reviewed yet.");
            return sb.toString();
        } else {
            Item item = storage.getItem(itemID);
            sb.append("Review(s) for " + item.getItemID() + ": " + item.getItemName() + ". " + item.getItemPrice() + " SEK." + MenuUtility.EOL);
            for (Review review : getReviewList(itemID)) {
                sb.append("Grade: " + review.getReviewGrade() + "." + review.getReviewText() + MenuUtility.EOL);
            }
            return sb.toString();
        }
    }

    public String printAllReviews() {
        StringBuilder sb = new StringBuilder();
        if (storage.getItemMap().isEmpty()){
            sb.append("No items registered yet.");
        /*
        For the else if condition, we can create a reviewCounter and whenever a Review has been created,
        it increases by 1. Then check if reviewCounter = 0. A reviewList seems unnecessary but discussion
        needed before implementation.
         */
        } else if (false){
            sb.append("No items were reviewed yet.");
        } else {
            sb.append("All registered reviews:" + MenuUtility.EOL);
            sb.append("------------------------------------");
            for (String itemID : storage.getItemMap().keySet()){
                // Repetition with printAllItemReviews method, need to make it more modular.
                Item item = storage.getItem(itemID);
                sb.append("Review(s) for " + item.getItemID() + ": " + item.getItemName() + ". " + item.getItemPrice() + " SEK." + MenuUtility.EOL);
                for (Review review : getReviewList(itemID)) {
                    sb.append("Grade: " + review.getReviewGrade() + "." + review.getReviewText() + MenuUtility.EOL);
                }
                sb.append("------------------------------------");
            }
        }
        return sb.toString();
    }

    public List<String> getMostReviewedItems() {
        // Method is not functional as it does not work when no itemIDs are in highestReviewedItems.
        List<String> highestReviewedItems = new ArrayList<>();
        for (String itemID : storage.getItemMap().keySet()) {
            if (highestReviewedItems.isEmpty()){
                highestReviewedItems.add(itemID);
            } else {
                String currentHighestReviewedItem = highestReviewedItems.get(0);
                if (storage.getItem(itemID).getNumOfReviews() > storage.getItem(currentHighestReviewedItem).getNumOfReviews()) {
                    highestReviewedItems.clear();
                    highestReviewedItems.add(itemID);
                } else if (storage.getItem(itemID).getNumOfReviews() == storage.getItem(currentHighestReviewedItem).getNumOfReviews()) {
                    highestReviewedItems.add(itemID);
                }
            }

        }
        return highestReviewedItems;
    }

    // Method is not functional as it does not work when no itemIDs are in lowestReviewedItems.
    public List<String> getLeastReviewedItems() {
        List<String> lowestReviewedItems = new ArrayList<>();
        for (String itemID : storage.getItemMap().keySet()) {
            if (lowestReviewedItems.isEmpty()){
                lowestReviewedItems.add(itemID);
            } else {
                String currentHighestReviewedItem = lowestReviewedItems.get(0);
                if (storage.getItem(itemID).getNumOfReviews() < storage.getItem(currentHighestReviewedItem).getNumOfReviews()) {
                    lowestReviewedItems.clear();
                    lowestReviewedItems.add(itemID);
                } else if (storage.getItem(itemID).getNumOfReviews() == storage.getItem(currentHighestReviewedItem).getNumOfReviews()) {
                    lowestReviewedItems.add(itemID);
                }
            }
        }
        return lowestReviewedItems;
    }

    public String printMostReviewedItems() {
        StringBuilder sb = new StringBuilder();
        if (storage.getItemMap().isEmpty()) {
            sb.append("No items registered yet.");
            // Same problem as before, that's why it's "false" at the moment.
        } else if (false){
            sb.append("No items were reviewed yet.");
        } else {
            List<String> highestReviewedItems = getMostReviewedItems();
            String tempItemID = highestReviewedItems.get(0);
            sb.append("Most reviews: " + storage.getItem(tempItemID).getNumOfReviews() + " review(s) each.");
            for (String itemID : highestReviewedItems) {
                sb.append(itemID + ": " + storage.getItem(itemID).getItemName() + ". " + storage.getItem(itemID).getItemPrice() + " SEK." + MenuUtility.EOL);
            }
        }
        return sb.toString();
    }

    public String printLeastReviewedItems() {
        StringBuilder sb = new StringBuilder();
        if (storage.getItemMap().isEmpty()) {
            sb.append("No items registered yet.");
            // Same problem as before, that's why it's "false" at the moment.
        } else if (false){
            sb.append("No items were reviewed yet.");
        } else {
            List<String> lowestReviewedItems = getLeastReviewedItems();
            String tempItemID = lowestReviewedItems.get(0);
            sb.append("Least reviews: " + storage.getItem(tempItemID).getNumOfReviews() + " review(s) each.");
            for (String itemID : lowestReviewedItems) {
                sb.append(itemID + ": " + storage.getItem(itemID).getItemName() + ". " + storage.getItem(itemID).getItemPrice() + " SEK." + MenuUtility.EOL);
            }
        }
        return sb.toString();
    }
}
