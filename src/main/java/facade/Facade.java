package facade;

import Item.Storage;
import utility.MenuUtility;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class Facade {

    // This class only has the skeleton of the methods used by the test.
    // You must fill in this class with your own code. You can (and should) create more classes
    // that implement the functionalities listed in the Facade and in the Test Cases.

    public Facade(){

    }

    public String createItem(String itemID, String itemName, double unitPrice) {
        return Storage.createItem(itemID, itemName, unitPrice);
    }

    public String printItem(String itemID) {
        return Storage.getItem(itemID).printItem();
    }

    public String removeItem(String itemID) {
        return Storage.removeItem(itemID);
    }

    public boolean containsItem(String itemID) {
        return Storage.getItemMap().containsKey(itemID);
    }

    public double buyItem(String itemID, int amount) {
        return Storage.buyItem(itemID, amount);
    }

    public String reviewItem(String itemID, String reviewComment, int reviewGrade) {
        return Storage.createReview(itemID, reviewComment, reviewGrade);
    }

    public String reviewItem(String itemID, int reviewGrade) {
        return Storage.createReview(itemID, reviewGrade);
    }

    public String getItemCommentsPrinted(String itemID) {
        return "";
    }

    public List<String> getItemComments(String itemID) {
        return Storage.getItemComments(itemID);
    }

    public double getItemMeanGrade(String itemID) {
        return Storage.getItemMeanGrade(itemID);
    }

    public int getNumberOfReviews(String itemID) {
        return Storage.getItemComments(itemID).size();
    }

    public String getPrintedItemReview(String itemID, int reviewNumber) {
        return Storage.printReview(itemID, reviewNumber);
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
        return Storage.updateItem(itemID, newName);
    }

    public String updateItemPrice(String itemID, double newPrice) {
        return Storage.updateItem(itemID, BigDecimal.valueOf(newPrice).setScale(2, RoundingMode.FLOOR));
    }

    public String printAllItems() {
        MenuUtility.sout(Storage.printAllItems());
        return Storage.printAllItems();
    }

    public String printMostProfitableItems() {
        return "";
    }
}