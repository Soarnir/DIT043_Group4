package controllers;

import items.Item;
import items.Review;
import utility.Storage;
import utility.MenuUtility;

import java.util.ArrayList;
import java.util.List;

public class ReviewController {

    private final Storage storage;
    private static final int LOWEST_REVIEWED_GRADE = 1;
    private static final int HIGHEST_REVIEWED_GRADE = 5;
    private static final int LOWEST_REVIEW_INDEX = 1;

    /*
     * The controller constructor passes through the same Storage reference from the Facade
     * to be used by the controller's methods.
     */
    public ReviewController(Storage storage) {
        this.storage = storage;
    }

    /*
     * Method handles input validation and creation of reviews,
     * giving feedback to the end user regardless of whether a review was created or not.
     */
    public String createReview(String itemID, String reviewText, int reviewGrade) {
        String returnString;
        if (!storage.checkForUsedID(itemID)) {
            returnString = "Item " + itemID + " was not registered yet.";
        } else if (reviewGrade < LOWEST_REVIEWED_GRADE || reviewGrade > HIGHEST_REVIEWED_GRADE) {
            returnString = "Grade values must be between 1 and 5.";
        } else if (reviewText.isEmpty()) {
            getReviewList(itemID).add(new Review(reviewGrade));
            storage.getItem(itemID).increaseNumOfReviews();
            returnString = "Your item review was registered successfully."; // So it doesn't count as comment
        } else {
            getReviewList(itemID).add(new Review(reviewText, reviewGrade));
            storage.getItem(itemID).increaseNumOfReviews();
            returnString = "Your item review was registered successfully.";
        }
        return returnString;
    }

    // Method handles review creation in the case that the end user does not have a comment.
    public String createReview(String itemID, int reviewGrade) {
        return createReview(itemID, "", reviewGrade);
    }

    public List<Review> getReviewList(String itemID) {
        return storage.getItem(itemID).getReviewList();
    }

    // Returns an ArrayList of comments submitted by users regarding a specific item.
    public List<String> getItemComments(String itemID) {
        List<String> itemComments = new ArrayList<>();
        for (Review review : getReviewList(itemID)) {
            if (review.getReviewText() != null) {
                itemComments.add(review.getReviewText());
            }
        }
        return itemComments;
    }

    // TODO The implementation of this isn't clear in Specs (Not needed to pass tests either) -K
    // Prints the comments submitted by users regarding a specific item.
    public String getItemCommentsPrinted(String itemID) {
        List<String> itemComments = getItemComments(itemID);
        StringBuilder sb = new StringBuilder();
        for (String comment : itemComments) {
            sb.append(comment).append(MenuUtility.EOL);
        }
        return sb.toString();
    }

    public int getNumberOfReviews(String itemID) {
        return storage.getItem(itemID).getNumOfReviews();
    }

    // Method calculates and returns the mean grade (from reviews) of an item, truncating to 1 decimal point.
    public double getItemMeanGrade(String itemID) {
        double sum = 0;
        double returnDouble;
        if (!getReviewList(itemID).isEmpty()) {
            for (Review review : getReviewList(itemID)) {
                sum += review.getReviewGrade();
            }
            returnDouble = MenuUtility.doubleTruncate(sum / getReviewList(itemID).size(), 1);
        } else {
            returnDouble = 0;
        }
        return returnDouble;
    }

    public Review getReview(String itemID, int reviewIndex) {
        return storage.getItem(itemID).getReviewList().get(reviewIndex);
    }

    /*
     * The method below is an abstraction of the getBestReviewedItems and getWorstReviewedItems methods.
     * It's called by getBestReviewedItems & getWorstReviewedItems and the boolean bestReviewed allows for distinguition.
     * The conditional logic is complex but the decision to abstract the methods was made to maintain DRY code as
     * otherwise,there would be a lot of repetition between the getBestReviewedItems and getWorstReviewedItems methods.
     * To help maintain readability, in-line comments have been added to explain sections of the method.
     */
    public List<String> getReviewedItemsBasedOnGrade(boolean bestReviewed) {
        List<String> reviewedItems = new ArrayList<>();

        //Loops through all registered item IDs.
        for (String itemID : storage.getItemMap().keySet()) {
            if (getItemMeanGrade(itemID) > 0) {
                // The first item with a mean grade > 0 is added to the list to have a starting point to compare.
                if (reviewedItems.isEmpty()) {
                    reviewedItems.add(itemID);
                } else {
                    String itemInReviewedItems = reviewedItems.get(0);
                    double storedItemMeanGrade = getItemMeanGrade(itemInReviewedItems);
                    // TODO do I need to mention itemID in the below comment since itemID is technically being looped? -K
                    // Current item refers to the item that is being considered in the current loop iteration.
                    double currentItemMeanGrade = getItemMeanGrade(itemID);

                    /*
                     * If currentItemMeanGrade == storedItemMeanGrade, the itemID is always added regardless
                     * of whether getBestReviewedItems or getWorstReviewedItems is called.
                     */
                    if (currentItemMeanGrade == storedItemMeanGrade) {
                        reviewedItems.add(itemID);
                    // The else-if below is executed only if getBestReviewedItems is called.
                    } else if (bestReviewed) {
                        if (currentItemMeanGrade > storedItemMeanGrade) {
                            reviewedItems.clear();
                            reviewedItems.add(itemID);
                        }
                    // The else is executed only if getWorseReviewedItems is called.
                    } else {
                        if (currentItemMeanGrade < storedItemMeanGrade) {
                            reviewedItems.clear();
                            reviewedItems.add(itemID);
                        }
                    }
                }
            }
        }
        return reviewedItems;
    }

    public List<String> getBestReviewedItems() {
        return getReviewedItemsBasedOnGrade(true);
    }

    public List<String> getWorseReviewedItems() {
        return getReviewedItemsBasedOnGrade(false);
    }

    // TODO Need to consider renaming variables -K
    public List<String> getReviewedItems(boolean mostReviewed) {
        List<String> reviewedItems = new ArrayList<>();

        // Loops through all registered item IDs.
        for (String itemID : storage.getItemMap().keySet()) {
            if (getNumberOfReviews(itemID) > 0) {
                // The first item with at least one review is added to the list to have a starting point to compare.
                if (reviewedItems.isEmpty()) {
                    reviewedItems.add(itemID);
                } else {
                    String itemInReviewedItems = reviewedItems.get(0);
                    int numOfReviewsOfStoredItem = storage.getItem(itemInReviewedItems).getNumOfReviews();
                    // TODO do I need to mention itemID in the below comment since itemID is technically being looped? -K
                    // numOfReviewsOfCurrentItem refers to the number of reviews of the item in the current loop iteration.
                    int numOfReviewsOfCurrentItem = getNumberOfReviews(itemID);

                    /*
                     * The conditionals below are very similar to those in getReviewedItemsBasedOnGrade but since
                     * these methods were already abstractions, add another abstraction for the abstractions would
                     * simply result in over-complex code and reduce readability significantly.
                     *
                     * If numOfReviewsOfCurrentItem == numOfReviewsOfStoredItem, the itemID is always added regardless
                     * of whether getMostReviewedItems or getWorstReviewedItems is called.
                     */
                    if (numOfReviewsOfCurrentItem == numOfReviewsOfStoredItem){
                        reviewedItems.add(itemID);
                    // The else-if below is executed only if getMostReviewedItems is called.
                    } else if (mostReviewed) {
                        if (numOfReviewsOfCurrentItem > numOfReviewsOfStoredItem) {
                            reviewedItems.clear();
                            reviewedItems.add(itemID);
                        }
                    // The else is executed only if getLeastReviewedItems is called.
                    } else {
                        if (numOfReviewsOfCurrentItem < numOfReviewsOfStoredItem) {
                            reviewedItems.clear();
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
     * Method prints a specific review and the corresponding grade and comment (if present) of a specific item.
     * If the item is not registered or the review is specified wrongly,
     * it prints appropriate messages to inform the end user.
     */
    public String printReview(String itemID, int reviewIndex) {
        int reviewListSize = getReviewList(itemID).size();
        String returnString;
        if (!storage.checkForUsedID(itemID)) {
            returnString = "Item " + itemID + " was not registered yet.";
        } else if (reviewListSize == 0) {
            returnString = "Item " + storage.getItem(itemID).getItemName() + " has not been reviewed yet.";
        } else if (reviewIndex < LOWEST_REVIEW_INDEX || reviewIndex > reviewListSize) {
            returnString = "Invalid review number. Choose between 1 and " + reviewListSize + ".";
        } else {
            /*
             * LOWEST_REVIEW_INDEX is being subtracted from reviewIndex as
             * review indices start from 1 (according to specs) but Java arrayList indices start from 0.
             */
            Review review = getReview(itemID, (reviewIndex - LOWEST_REVIEW_INDEX));
            if (review.getReviewText() == null) {
                returnString = "Grade: " + review.getReviewGrade() + ".";
            } else {
                returnString = "Grade: " + review.getReviewGrade() + "." + review.getReviewText();
            }
        }
        return returnString;
    }

    /*
     * Method prints all reviews and the corresponding grades and comments (if present) of a specific item.
     * If the item or at least one review is not registered, it prints appropriate messages to inform the end user.
     */
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

    /*
     * Method prints all registered reviews with their corresponding grades and comments (if present).
     * If no items or no reviews are registered, appropriate messages are printed to inform the end user.
     */
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
            for (Item item : storage.getItemMap().values()) {
                if (item.getNumOfReviews() > 0) {
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

    public String printReviewedItems(boolean mostReviewed) {
        StringBuilder sb = new StringBuilder();
        if (storage.getItemMap().isEmpty()) {
            sb.append("No items registered yet.");
        } else if (!hasAReviewBeenRegistered()) {
            sb.append("No items were reviewed yet.");
        } else {
            if(mostReviewed) {
                List<String> highestReviewedItems = getMostReviewedItems();
                String tempItemID = highestReviewedItems.get(0);
                sb.append("Most reviews: ").append(storage.getItem(tempItemID).getNumOfReviews()).append(" review(s) each.");
                sb.append(MenuUtility.EOL);
                for (String itemID : highestReviewedItems) {
                    //
                    sb.append(storage.getItem(itemID).toString()).append(MenuUtility.EOL);
                }
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
        }
        return sb.toString();
    }

    public String printMostReviewedItems() {
        return printReviewedItems(true);
    }

    public String printLeastReviewedItems() {
        return printReviewedItems(false);
    }

    public String printReviewedItemsBasedOnGrade(boolean bestReviewed) {
        StringBuilder sb = new StringBuilder();

        if (storage.getItemMap().isEmpty()) {
            sb.append("No items registered yet.");
        } else if (!hasAReviewBeenRegistered()) {
            sb.append("No items were reviewed yet.");
        } else {
            if (bestReviewed) {
                List<String> bestReviewedItems = getBestReviewedItems();
                String tempItemID = bestReviewedItems.get(0);
                sb.append("Items with best mean reviews:").append(MenuUtility.EOL);
                sb.append("Grade: ").append(getItemMeanGrade(tempItemID)).append(MenuUtility.EOL);
                for (String itemID : bestReviewedItems) {
                    //
                    sb.append(storage.getItem(itemID).toString()).append(MenuUtility.EOL);
                }
            } else {
                List<String> worstReviewedItems = getWorseReviewedItems();
                String tempItemID = worstReviewedItems.get(0);
                sb.append("Items with worst mean reviews:").append(MenuUtility.EOL);
                sb.append("Grade: ").append(getItemMeanGrade(tempItemID)).append(MenuUtility.EOL);
                for (String itemID : worstReviewedItems) {
                    //
                    sb.append(storage.getItem(itemID).toString()).append(MenuUtility.EOL);
                }
            }
        }

        return sb.toString();
    }

    public String printBestReviewedItems() {
        return printReviewedItemsBasedOnGrade(true);
    }

    public String printWorseReviewedItems() {
        return printReviewedItemsBasedOnGrade(false);
    }

    public boolean hasAReviewBeenRegistered() {
        boolean aReviewHasBeenRegistered = false;
        for(Item item : storage.getItemMap().values()) {
            if (item.getNumOfReviews() > 0) {
                aReviewHasBeenRegistered = true;
                break;
            }
        }
        return aReviewHasBeenRegistered;
    }
}