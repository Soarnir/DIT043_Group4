package controllers;

import items.Item;
import utility.Storage;
import items.Transaction;
import utility.MenuUtility;

import java.util.ArrayList;
import java.util.List;

public class TransactionController {

    private final Storage storage;

    /*
     *
     */
    public TransactionController(Storage storage) {
        this.storage = storage;
    }

    public List<Transaction> getTransactionList(String itemID) {
        List<Transaction> transactionList = new ArrayList<>();
        if (storage.getItemTransactionList(itemID) != null) {
            transactionList = storage.getItemTransactionList(itemID);
        }
        return transactionList;
    }

    public double getTotalProfit() {
        double totalProfit = 0;
        for (String itemID : storage.getUsedIDs()) {
            totalProfit += getProfit(itemID);
        }
        return MenuUtility.doubleTruncate(totalProfit, 2);
    }

    public int getTotalUnitsSold() {
        int totalUnitsSold = 0;
        for (String itemID : storage.getUsedIDs()) {
            totalUnitsSold += getUnitsSold(itemID);
        }
        return totalUnitsSold;
    }

    public int getTotalTransactions() {
        int totalTransactions = 0;
        for (String itemID : storage.getUsedIDs()) {
            int numOfTransactions = storage.getItemTransactionList(itemID).size();
            totalTransactions += numOfTransactions;
        }
        return totalTransactions;
    }

    public double getProfit(String itemID) {
        double profit = 0;
        List<Transaction> transactions = getTransactionList(itemID);
        if (transactions != null && !transactions.isEmpty()) {
            for (Transaction transaction : transactions) {
                profit += transaction.getTransactionCost();
            }
        }
        return MenuUtility.doubleTruncate(profit, 2);
    }

    public int getUnitsSold(String itemID) {
        int unitsSold = 0;
        List<Transaction> transactions = getTransactionList(itemID);
        if (transactions != null && !transactions.isEmpty()) {
            for (Transaction transaction : getTransactionList(itemID)) {
                unitsSold += transaction.getAmount();
            }
        }
        return unitsSold;
    }

    public String getItemTransactions(String itemID) {
        StringBuilder sb = new StringBuilder();
        List<Transaction> itemTransactions = getTransactionList(itemID);
        for (Transaction transaction : itemTransactions) {
            sb.append(transaction.toString()).append(MenuUtility.EOL);
        }
        return sb.toString();
    }

    public List<String> getMostProfitableItems() {
        List<String> mostProfitable = new ArrayList<>();
        for (String itemID : storage.getTransactionMap().keySet()) {
            if (mostProfitable.isEmpty()) {
                mostProfitable.add(itemID);
            } else {
                String currentMostProfitableItem = mostProfitable.get(0);
                double currentItemProfit = getProfit(itemID);
                double storedItemProfit = getProfit(currentMostProfitableItem);

                if (currentItemProfit > storedItemProfit) {
                    mostProfitable.clear();
                    mostProfitable.add(itemID);
                } else if (currentItemProfit == storedItemProfit) {
                    mostProfitable.add(itemID);
                }
            }
        }
        return mostProfitable;
    }

    public double buyItem(String itemID, int amount) {
        double returnDouble;
        if (storage.checkForUsedID(itemID)) {
            Transaction transaction = new Transaction(storage.getItem(itemID), amount);
            getTransactionList(itemID).add(transaction);
            returnDouble = MenuUtility.doubleTruncate(transaction.getTransactionCost(), 2);
        } else {
            returnDouble = -1.0;
        }
        return returnDouble;
    }

    public String printAllTransactions() {
        StringBuilder sb = new StringBuilder("All purchases made: " + MenuUtility.EOL);

        sb.append("Total profit: ");
        sb.append(MenuUtility.doubleFormat(getTotalProfit()));
        sb.append(" SEK").append(MenuUtility.EOL);
        sb.append("Total items sold: ").append(getTotalUnitsSold()).append(" units").append(MenuUtility.EOL);
        sb.append("Total purchases made: ").append(getTotalTransactions()).append(" transactions").append(MenuUtility.EOL);
        sb.append("------------------------------------").append(MenuUtility.EOL);

        for (String itemID : storage.getUsedIDs()) {
            List<Transaction> transactions = getTransactionList(itemID);
            if (transactions != null && !transactions.isEmpty()) {
                for (Transaction transaction : transactions) {
                    sb.append(transaction.toString()).append(MenuUtility.EOL);
                }
            }
        }
        //TODO The last EOL append is needed for test but doesn't make sense.
        sb.append("------------------------------------").append(MenuUtility.EOL);
        return sb.toString();
    }

    public String printItemTransactions(String itemID) {
        StringBuilder sb = new StringBuilder();
        if (storage.checkForUsedID(itemID)) {
            Item item = storage.getItem(itemID);
            sb.append("Transactions for item: ").append(item.toString()).append(MenuUtility.EOL);
            if (!getTransactionList(itemID).isEmpty()) {
                sb.append(getItemTransactions(item.getItemID()));
            } else {
                sb.append("No transactions have been registered for item ").append(item.getItemID()).append(" yet.");
            }
        } else {
            sb.append("Item ").append(itemID).append(" was not registered yet.");
        }
        return sb.toString();
    }

    public String printMostProfitableItems() {
        StringBuilder sb = new StringBuilder();
        if (storage.getItemMap().isEmpty()) {
            sb.append("No items registered yet.");
        } else if (storage.getTransactionMap().isEmpty()) {
            sb.append("No items were bought yet.");
        } else {
            sb.append("Most profitable items: ").append(MenuUtility.EOL);
            double totalProfit = 0;
            StringBuilder items = new StringBuilder();
            for (String itemID : getMostProfitableItems()) {
                totalProfit += getProfit(itemID);
                items.append(storage.getItem(itemID).toString()).append(MenuUtility.EOL);
            }
            sb.append("Total profit: ").append(MenuUtility.doubleFormat(totalProfit)).append(" SEK").append(MenuUtility.EOL);
            sb.append(items);
        }
        return sb.toString();
    }
}