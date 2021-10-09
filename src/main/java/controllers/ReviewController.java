package controllers;

import item.Item;
import item.Review;
import item.Storage;
import utility.MenuUtility;

import java.util.ArrayList;
import java.util.List;

public class ReviewController {

    Storage storage;
    final int LOWEST_REVIEWED_GRADE = 1;
    final int HIGHEST_REVIEWED_GRADE = 5;
    final int LOWEST_REVIEW_INDEX = 1;

    public ReviewController(Storage storage) {
        this.storage = storage;
    }

    public String createReview(String itemID, String reviewText, int reviewGrade) {
        if (!storage.checkForUsedID(itemID)) {
            return "item " + itemID + " was not registered yet.";
        } else if (reviewGrade < LOWEST_REVIEWED_GRADE || reviewGrade > HIGHEST_REVIEWED_GRADE) {
            return "Grade values must be between 1 and 5.";
        } else {
            getReviewList(itemID).add(new Review(reviewText, reviewGrade));
            return "Your item review was registered successfully.";
        }
    }

    public String createReview(String itemID, int reviewGrade) {
        return createReview(itemID, "", reviewGrade);
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
        return MenuUtility.doubleTruncate((sum / getReviewList(itemID).size()));
    }

    public Review getReview(String itemID, int reviewIndex) {
        return storage.getItem(itemID).getReviewList().get(reviewIndex);
    }

    public String printReview(String itemID, int reviewIndex) {
        int reviewListSize = getReviewList(itemID).size();
        if (!storage.checkForUsedID(itemID)) {
            return "item " + itemID + " was not registered yet.";
        } else if (reviewListSize == 0) {
            return "item " + storage.getItem(itemID).getItemName() + " has not been reviewed yet.";
        } else if (reviewIndex < LOWEST_REVIEW_INDEX || reviewIndex > reviewListSize) {
            return "Invalid review number. Choose between 1 and " + reviewListSize + ".";
        } else {
            Review review = getReview(itemID, (reviewIndex - LOWEST_REVIEW_INDEX));
            return "Grade: " + review.getReviewGrade() + "." + review.getReviewText();
        }
    }

    public String printAllItemReviews(String itemID) {
        StringBuilder sb = new StringBuilder();
        Item item;
        if (!storage.checkForUsedID(itemID)) {
            sb.append("item ").append(itemID).append(" was not registered yet.");
        } else {
            item = storage.getItem(itemID);
            sb.append("Review(s) for ").append(item.toString()).append(MenuUtility.EOL);
            if (getReviewList(itemID).size() == 0) {
                sb.append("item ").append(item.getItemName()).append(" has not been reviewed yet.");
            } else {
                for (Review review : getReviewList(itemID)) {
                    sb.append(review.toString()).append(MenuUtility.EOL);
                }
            }
        }
        return sb.toString();
    }

    public boolean hasAReviewBeenRegistered(){
        boolean aReviewHasBeenRegistered = false;
        for(Item item : storage.getItemMap().values()){
            if (item.getNumOfReviews() > 0){
                aReviewHasBeenRegistered = true;
            }
        }
        return aReviewHasBeenRegistered;
    }

    public String printAllReviews() {
        StringBuilder sb = new StringBuilder();
        if (storage.getItemMap().isEmpty()) {
            sb.append("No items registered yet.");
        // Change to use a method to loop true itemMap and check for reviews.
        } else if (!hasAReviewBeenRegistered()) {
            sb.append("No items were reviewed yet.");
        } else {
            sb.append("All registered reviews:").append(MenuUtility.EOL);
            sb.append("------------------------------------");
            for (Item item : storage.getItemMap().values()){
                // Repetition with printAllItemReviews method, need to make it more modular.
                sb.append("Review(s) for ").append(item.toString()).append(MenuUtility.EOL);
                for (Review review : getReviewList(item.getItemID())) {
                    sb.append(review.toString()).append(MenuUtility.EOL);
                }
                sb.append("------------------------------------");
            }
        }
        return sb.toString();
    }

    // TODO Need to consider renaming variables -K
    public List<String> getReviewedItems(boolean mostReviewed) {
        List<String> reviewedItems = new ArrayList<>();
        for (String itemID : storage.getItemMap().keySet()) {
            if (reviewedItems.isEmpty()){
                reviewedItems.add(itemID);
            } else {
                String currentReviewedItem = reviewedItems.get(0);
                int currentItemReviews = storage.getItem(itemID).getNumOfReviews();
                int storedItemReviews = storage.getItem(currentReviewedItem).getNumOfReviews();
                if (mostReviewed) {
                    if (currentItemReviews > storedItemReviews) {
                        reviewedItems.clear();
                        reviewedItems.add(itemID);
                    }
                } else if (!mostReviewed) {
                    if (currentItemReviews < storedItemReviews) {
                        reviewedItems.clear();
                        reviewedItems.add(itemID);
                    }
                } else if (currentItemReviews == storedItemReviews) {
                    reviewedItems.add(itemID);
                }
            }
        }
        return reviewedItems;
    }

    public List<String> getMostReviewedItems() {
        return getReviewedItems(true);
    }

    public List<String> getLeastReviewedItems() {
        return getReviewedItems(false);
    }

    /*
    printMostReviewedItems and printLeastReviewedItems needs to be refactored as above
    but this should be checked with Fredrik or during a Q&A session first.
     */

    public String printMostReviewedItems() {
        StringBuilder sb = new StringBuilder();
        if (storage.getItemMap().isEmpty()) {
            sb.append("No items registered yet.");
        // Change to use a method to loop true itemMap and check for reviews.
        } else if (!hasAReviewBeenRegistered()) {
            sb.append("No items were reviewed yet.");
        } else {
            List<String> highestReviewedItems = getMostReviewedItems();
            String tempItemID = highestReviewedItems.get(0);
            sb.append("Most reviews: ").append(storage.getItem(tempItemID).getNumOfReviews()).append(" review(s) each.");
            for (String itemID : highestReviewedItems) {
                //
                sb.append(storage.getItem(itemID).toString()).append(MenuUtility.EOL);
            }
        }
        return sb.toString();
    }

    public String printLeastReviewedItems() {
        StringBuilder sb = new StringBuilder();
        if (storage.getItemMap().isEmpty()) {
            sb.append("No items registered yet.");
        // Change to use a method to loop true itemMap and check for reviews.
        } else if (!hasAReviewBeenRegistered()) {
            sb.append("No items were reviewed yet.");
        } else {
            List<String> lowestReviewedItems = getLeastReviewedItems();
            String tempItemID = lowestReviewedItems.get(0);
            sb.append("Least reviews: ").append(storage.getItem(tempItemID).getNumOfReviews()).append(" review(s) each.");
            for (String itemID : lowestReviewedItems) {
                //
                sb.append(storage.getItem(itemID).toString()).append(MenuUtility.EOL);
            }
        }
        return sb.toString();
    }
}
