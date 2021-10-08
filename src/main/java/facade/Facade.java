package facade;

import Item.Storage;
import handlers.ItemController;
import handlers.ReviewController;
import handlers.TransactionController;
import utility.MenuUtility;

import java.util.List;

public class Facade {

    private final Storage storage;
    private final ItemController itemController;
    private final ReviewController reviewController;
    private final TransactionController transactionController;
    // This class only has the skeleton of the methods used by the test.
    // You must fill in this class with your own code. You can (and should) create more classes
    // that implement the functionalities listed in the Facade and in the Test Cases.

    public Facade(){
        this.storage = new Storage();
        this.itemController = new ItemController(storage);
        this.reviewController = new ReviewController(storage);
        this.transactionController = new TransactionController(storage);
    }

    public String createItem(String itemID, String itemName, double unitPrice) {
        return itemController.createItem(itemID, itemName, unitPrice);
    }

    public String printItem(String itemID) {
        if (storage.checkForUsedID(itemID)) {
            return storage.getItem(itemID).toString();
        } else {
            return "Item " + itemID + " was not registered yet.";
        }
    }

    public String removeItem(String itemID) {
        return itemController.removeItem(itemID);
    }

    public boolean containsItem(String itemID) {
        return storage.getItemMap().containsKey(itemID);
    }

    public double buyItem(String itemID, int amount) {
        return transactionController.buyItem(itemID, amount);
    }

    public String reviewItem(String itemID, String reviewComment, int reviewGrade) {
        return reviewController.createReview(itemID, reviewComment, reviewGrade);
    }

    public String reviewItem(String itemID, int reviewGrade) {
        return reviewController.createReview(itemID, reviewGrade);
    }

    public String getItemCommentsPrinted(String itemID) {
        return "";
    }

    public List<String> getItemComments(String itemID) {
        return reviewController.getItemComments(itemID);
    }

    public double getItemMeanGrade(String itemID) {
        return reviewController.getItemMeanGrade(itemID);
    }

    public int getNumberOfReviews(String itemID) {
        return reviewController.getItemComments(itemID).size();
    }

    public String getPrintedItemReview(String itemID, int reviewNumber) {
        return reviewController.printReview(itemID, reviewNumber);
    }

    public String getPrintedReviews(String itemID) {
        return "";
    }

    public String printMostReviewedItems() {
        return "";
    }

    public List<String> getMostReviewedItems() {
        return null;
    }

    public List<String> getLeastReviewedItems() {
        return null;
    }

    public String printLeastReviewedItems() {
        return "";
    }

    public double getTotalProfit() {
        return transactionController.getTotalProfit();
    }

    public String printItemTransactions(String itemID) {
        return transactionController.printItemTransactions(itemID);
    }

    public int getTotalUnitsSold() {
        return transactionController.getTotalUnitsSold();
    }

    public int getTotalTransactions() {
        return transactionController.getTotalTransactions();
    }

    public double getProfit(String itemID) {
        return transactionController.getProfit(itemID);
    }

    public int getUnitsSolds(String itemID) {
        return transactionController.getUnitsSold(itemID);
    }

    public String printAllTransactions() {
        return transactionController.printAllTransactions();
    }

    public String printWorseReviewedItems() {
        return "";
    }

    public String printBestReviewedItems() {
        return "";
    }

    public List<String> getWorseReviewedItems() {
        return null;
    }

    public List<String> getBestReviewedItems() {
        return null;
    }

    public String printAllReviews() {
        return "";
    }

    public String updateItemName(String itemID, String newName) {
        return itemController.updateItem(itemID, newName);
    }

    public String updateItemPrice(String itemID, double newPrice) {
        return itemController.updateItem(itemID, newPrice);
    }

    public String printAllItems() {
        MenuUtility.print(itemController.printAllItems());
        return itemController.printAllItems();
    }

    public String printMostProfitableItems() {
        return transactionController.printMostProfitableItems();
    }
}