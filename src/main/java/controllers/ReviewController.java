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
            return "Item " + itemID + " not found.";
        } else if (reviewGrade < LOWEST_REVIEWED_GRADE || reviewGrade > HIGHEST_REVIEWED_GRADE) {
            return "Grade values must be between 1 and 5.";
        } else if (reviewText.isEmpty()) {
            getReviewList(itemID).add(new Review(reviewGrade));
            storage.getItem(itemID).increaseNumOfReviews();
            return "Your item review was registered successfully."; // So it doesn't count as comment
        } else {
            getReviewList(itemID).add(new Review(reviewText, reviewGrade));
            storage.getItem(itemID).increaseNumOfReviews();
            return "Your item review was registered successfully.";
        }
    }

    public String createReview(String itemID, int reviewGrade) {
        //return createReview(itemID, "", reviewGrade);
        // Code repetition, used to check if test works.
        if (!storage.checkForUsedID(itemID)) {
            // Epic3Alternate first test requires something not in the specs
            // return "item " + itemID + " was not registered yet.";
            return "Item " + itemID + " not found.";
        } else if (reviewGrade < LOWEST_REVIEWED_GRADE || reviewGrade > HIGHEST_REVIEWED_GRADE) {
            return "Grade values must be between 1 and 5.";
        } else {
            getReviewList(itemID).add(new Review(reviewGrade));
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

    public int getNumberOfReviews(String itemID){
        return storage.getItem(itemID).getNumOfReviews();
    }

    // Error handling and making mean grade visible to the user is yet to be implemented.
    public double getItemMeanGrade(String itemID) {
        double sum = 0;
        if (!getReviewList(itemID).isEmpty()) {
            for (Review review : getReviewList(itemID)) {
                sum += review.getReviewGrade();
            }
            double meanGradeBeforeTruncation = sum / getReviewList(itemID).size();
            return MenuUtility.doubleTruncate(meanGradeBeforeTruncation, 1);
        } else {
            return 0;
        }
    }

    public Review getReview(String itemID, int reviewIndex) {
        return storage.getItem(itemID).getReviewList().get(reviewIndex);
    }

    public String printReview(String itemID, int reviewIndex) {
        int reviewListSize = getReviewList(itemID).size();
        if (!storage.checkForUsedID(itemID)) {
            return "Item " + itemID + " was not registered yet.";
        } else if (reviewListSize == 0) {
            return "Item " + storage.getItem(itemID).getItemName() + " has not been reviewed yet.";
        } else if (reviewIndex < LOWEST_REVIEW_INDEX || reviewIndex > reviewListSize) {
            return "Invalid review number. Choose between 1 and " + reviewListSize + ".";
        } else {
            Review review = getReview(itemID, (reviewIndex - LOWEST_REVIEW_INDEX));
            if (review.getReviewText() == null) {
                return "Grade: " + review.getReviewGrade() + ".";
            } else {
                return "Grade: " + review.getReviewGrade() + "." + review.getReviewText();
            }
        }
    }

    public String printAllItemReviews(String itemID) {
        StringBuilder sb = new StringBuilder();
        Item item;
        if (!storage.checkForUsedID(itemID)) {
            sb.append("Item ").append(itemID).append(" was not registered yet.");
        } else {
            item = storage.getItem(itemID);
            sb.append("Review(s) for ").append(item.toString()).append(MenuUtility.EOL);
            if (getReviewList(itemID).size() == 0) {
                sb.append("The item ").append(item.getItemName()).append(" has not been reviewed yet.");
            } else {
                for (Review review : getReviewList(itemID)) {
                    sb.append(review.toString()).append(MenuUtility.EOL);
                }
            }
        }
        return sb.toString();
    }

    public boolean hasAReviewBeenRegistered() {
        boolean aReviewHasBeenRegistered = false;
        for(Item item : storage.getItemMap().values()){
            if (item.getNumOfReviews() > 0){
                aReviewHasBeenRegistered = true;
                break;
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
            sb.append("------------------------------------").append(MenuUtility.EOL);
            for (Item item : storage.getItemMap().values()){
                if (item.getNumOfReviews() > 0){
                    // Repetition with printAllItemReviews method, need to make it more modular.
                    sb.append("Review(s) for ").append(item).append(MenuUtility.EOL);
                    for (Review review : getReviewList(item.getItemID())) {
                        sb.append(review.toString()).append(MenuUtility.EOL);
                    }
                    sb.append("------------------------------------").append(MenuUtility.EOL);
                }
            }
        }
        return sb.toString();
    }

    // TODO Need to consider renaming variables -K
    public List<String> getReviewedItems(boolean mostReviewed) {
        List<String> reviewedItems = new ArrayList<>();
        for (String itemID : storage.getItemMap().keySet()) {
            if (storage.getItem(itemID).getNumOfReviews() > 0){
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
                        } else if (currentItemReviews == storedItemReviews) {
                            reviewedItems.add(itemID);
                        }
                    // Can change to else, else-if for debugging purposes
                    } else if (!mostReviewed) {
                        if (currentItemReviews < storedItemReviews) {
                            reviewedItems.clear();
                            reviewedItems.add(itemID);
                        } else if (currentItemReviews == storedItemReviews) {
                            reviewedItems.add(itemID);
                        }
                    }
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
            sb.append(MenuUtility.EOL);
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
        } else if (!hasAReviewBeenRegistered()) {
            sb.append("No items were reviewed yet.");
        } else {
            List<String> lowestReviewedItems = getLeastReviewedItems();
            String tempItemID = lowestReviewedItems.get(0);
            sb.append("Least reviews: ").append(storage.getItem(tempItemID).getNumOfReviews()).append(" review(s) each.");
            sb.append(MenuUtility.EOL);
            for (String itemID : lowestReviewedItems) {
                //
                sb.append(storage.getItem(itemID).toString()).append(MenuUtility.EOL);
            }
        }
        return sb.toString();
    }

    // Same problem with repetition as before

    public List<String> getBestReviewedItems() {
        List<String> bestReviewedItems = new ArrayList<>();

        for (String itemID : storage.getItemMap().keySet()) {
            if (getItemMeanGrade(itemID) > 0) {
                if (bestReviewedItems.isEmpty()) {
                    bestReviewedItems.add(itemID);
                } else {
                    String currentBestReviewedItem = bestReviewedItems.get(0);
                    double currentItemMeanGrade = getItemMeanGrade(itemID);
                    double storedItemMeanGrade = getItemMeanGrade(currentBestReviewedItem);

                    if (currentItemMeanGrade > storedItemMeanGrade) {
                        bestReviewedItems.clear();
                        bestReviewedItems.add(itemID);
                    } else if (currentItemMeanGrade == storedItemMeanGrade) {
                        bestReviewedItems.add(itemID);
                    }
                }
            }
        }
        return bestReviewedItems;
    }

    public List<String> getWorseReviewedItems() {
        List<String> worstReviewedItems = new ArrayList<>();

        for (String itemID : storage.getItemMap().keySet()) {
            if (getItemMeanGrade(itemID) > 0) {
                if (worstReviewedItems.isEmpty()) {
                    worstReviewedItems.add(itemID);
                } else {
                    String currentWorstReviewedItem = worstReviewedItems.get(0);
                    double currentItemMeanGrade = getItemMeanGrade(itemID);
                    double storedItemMeanGrade = getItemMeanGrade(currentWorstReviewedItem);

                    if (currentItemMeanGrade < storedItemMeanGrade) {
                        worstReviewedItems.clear();
                        worstReviewedItems.add(itemID);
                    } else if (currentItemMeanGrade == storedItemMeanGrade) {
                        worstReviewedItems.add(itemID);
                    }
                }
            }
        }
        return worstReviewedItems;
    }

    public String printBestReviewedItems(){
        StringBuilder sb = new StringBuilder();

        if (storage.getItemMap().isEmpty()) {
            sb.append("No items registered yet.");
        } else if (!hasAReviewBeenRegistered()) {
            sb.append("No items were reviewed yet.");
        } else {
            List<String> bestReviewedItems = getBestReviewedItems();
            String tempItemID = bestReviewedItems.get(0);
            sb.append("Items with best mean reviews:");
            sb.append(MenuUtility.EOL);
            sb.append("Grade: ").append(getItemMeanGrade(tempItemID));
            sb.append(MenuUtility.EOL);
            for (String itemID : bestReviewedItems) {
                //
                sb.append(storage.getItem(itemID).toString()).append(MenuUtility.EOL);
            }
        }

        return sb.toString();
    }

    public String printWorseReviewedItems(){
        StringBuilder sb = new StringBuilder();

        if (storage.getItemMap().isEmpty()) {
            sb.append("No items registered yet.");
        } else if (!hasAReviewBeenRegistered()) {
            sb.append("No items were reviewed yet.");
        } else {
            List<String> worstReviewedItems = getWorseReviewedItems();
            String tempItemID = worstReviewedItems.get(0);
            sb.append("Items with worst mean reviews:");
            sb.append(MenuUtility.EOL);
            sb.append("Grade: ").append(getItemMeanGrade(tempItemID));
            sb.append(MenuUtility.EOL);
            for (String itemID : worstReviewedItems) {
                //
                sb.append(storage.getItem(itemID).toString()).append(MenuUtility.EOL);
            }
        }

        return sb.toString();
    }
}
