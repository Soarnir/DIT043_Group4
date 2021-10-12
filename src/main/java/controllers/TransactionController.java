package controllers;

import item.Storage;
import item.Transaction;
import utility.MenuUtility;

import java.util.List;

public class TransactionController {

    Storage storage;

    public TransactionController(Storage storage) {
        this.storage = storage;
    }

    public List<Transaction> getTransactionList(String itemID) {
        return storage.getItem(itemID).getTransactionList();
    }

    public double buyItem(String itemID, int amount) {
        if (storage.checkForUsedID(itemID)) {
            Transaction transaction = new Transaction(storage.getItem(itemID), amount);
            getTransactionList(itemID).add(transaction);
            return MenuUtility.doubleTruncate(transaction.getTransactionCost(), 2);
        } else {
            return -1.0;
        }
    }

    //TODO EP4 implementation
    public double getTotalProfit() {
        return 0;
    }

    public int getTotalUnitsSold() {
        return 0;
    }

    public int getTotalTransactions() {
        return 0;
    }

    public String printAllTransactions() {
        return "";
    }

    public double getProfit(String itemID) {
        return 0;
    }


    public int getUnitsSold(String itemID) {
        return 0;
    }

    public String printItemTransactions(String itemID) {
        return "";
    }

    public String printMostProfitableItems() {
        return "";
    }
}
