package facade;

import Item.Storage;

import java.util.List;

public class Facade {

    Storage storage = new Storage();
    // This class only has the skeleton of the methods used by the test.
    // You must fill in this class with your own code. You can (and should) create more classes
    // that implement the functionalities listed in the Facade and in the Test Cases.

    public Facade(){

    }

    public String createItem(String itemID, String itemName, double unitPrice) {
        return storage.createItem(itemID, itemName, unitPrice);
    }

    public String printItem(String itemID) {
        return storage.getItem(itemID).printItem();
    }

    public String removeItem(String itemID) {
        return storage.removeItem(itemID);
    }

    public boolean containsItem(String itemID) {
        return storage.getItemMap().containsKey(itemID);
    }

    public double buyItem(String itemID, int amount) {
        return 0.0;
    }

    public String reviewItem(String itemID, String reviewComment, int reviewGrade) {
        return storage.createReview(itemID, reviewComment, reviewGrade);
    }

    public String reviewItem(String itemID, int reviewGrade) {
        return storage.createReview(itemID, reviewGrade);
    }

    public String getItemCommentsPrinted(String itemID) {
        return "";
    }

    public List<String> getItemComments(String itemID) {
        return storage.getItemComments(itemID);
    }

    public double getItemMeanGrade(String itemID) {
        return storage.getItemMeanGrade(itemID);
    }

    public int getNumberOfReviews(String itemID) {
        return storage.getItemComments(itemID).size();
    }

    public String getPrintedItemReview(String itemID, int reviewNumber) {
        return "";
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
        return -1.0;
    }

    public String printItemTransactions(String itemID) {
        return "";
    }

    public int getTotalUnitsSold() {
        return -1;
    }

    public int getTotalTransactions() {
        return -1;
    }

    public double getProfit(String itemID) {
        return -1.0;
    }

    public int getUnitsSolds(String itemID) {
        return -1;
    }

    public String printAllTransactions() {
        return "";
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
        return "";
    }

    public String updateItemPrice(String itemID, double newPrice) {
        return "";
    }

    public String printAllItems() {
        System.out.println(storage.printAllItems());
        return storage.printAllItems();
    }

    public String printMostProfitableItems() {
        return "";
    }
}