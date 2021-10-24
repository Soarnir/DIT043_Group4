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
     * The controller constructor passes through the same Storage reference from the Facade
     * to be used by the controller's methods
     */
    public TransactionController(Storage storage) {
        this.storage = storage;
    }

    // Method returns an empty arraylist if none is found mapped to the Item.
    public List<Transaction> getTransactionList(String itemID) {
        List<Transaction> transactionList = new ArrayList<>();

        if (storage.getItemTransactionList(itemID) != null) {
            transactionList = storage.getItemTransactionList(itemID);
        }
        return transactionList;
    }

    public double getTotalProfit() {
        double totalProfit = 0;

        for (String itemID : storage.getUsedItemIDs()) {
            totalProfit += getProfit(itemID);
        }
        return MenuUtility.doubleTruncate(totalProfit, 2);
    }

    public int getTotalUnitsSold() {
        int totalUnitsSold = 0;

        for (String itemID : storage.getUsedItemIDs()) {
            totalUnitsSold += getUnitsSold(itemID);
        }
        return totalUnitsSold;
    }

    public int getTotalTransactions() {
        int totalTransactions = 0;

        for (String itemID : storage.getUsedItemIDs()) {
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
            for (Transaction transaction : transactions) {
                unitsSold += transaction.getAmount();
            }
        }
        return unitsSold;
    }

    public String getItemTransactions(String itemID) {
        StringBuilder sb = new StringBuilder();
        List<Transaction> itemTransactions = getTransactionList(itemID);

        for (Transaction transaction : itemTransactions) {
            sb.append(transaction).append(MenuUtility.EOL);
        }
        return sb.toString();
    }

    public List<String> getMostProfitableItems() {
        List<String> mostProfitable = new ArrayList<>();

        /*
         * Iterates over all transactions and compares total item profits until the entire list has been checked
         * after which it returns a list of the most profitable item(s).
         */
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

    /*
     * Buying an item creates a Transaction object, an immutable object which is an abstraction of the purchase event,
     * which stores the items ID, the total cost, and the amount purchased.
     */
    public double buyItem(String itemID, int amount) {
        double returnDouble = -1;

        if (storage.checkForUsedID(itemID)) {
            Transaction transaction = new Transaction(storage.getItem(itemID), amount);
            getTransactionList(itemID).add(transaction);
            returnDouble = MenuUtility.doubleTruncate(transaction.getTransactionCost(), 2);
        }
        return returnDouble;
    }

    // Method prints all transactions that have been made, detailing item ID, name and purchase price.
    public String printAllTransactions() {
        StringBuilder sb = new StringBuilder("All purchases made: " + MenuUtility.EOL);

        sb.append("Total profit: ");
        sb.append(MenuUtility.doubleFormat(getTotalProfit()));
        sb.append(" SEK").append(MenuUtility.EOL);
        sb.append("Total items sold: ").append(getTotalUnitsSold()).append(" units").append(MenuUtility.EOL);
        sb.append("Total purchases made: ").append(getTotalTransactions()).append(" transactions").append(MenuUtility.EOL);
        sb.append("------------------------------------").append(MenuUtility.EOL);

        // Iterates over all items and prints all transactions if they exist for each item.
        for (String itemID : storage.getUsedItemIDs()) {
            List<Transaction> transactions = getTransactionList(itemID);
            if (transactions != null && !transactions.isEmpty()) {
                for (Transaction transaction : transactions) {
                    sb.append(transaction).append(MenuUtility.EOL);
                }
            }
        }
        sb.append("------------------------------------").append(MenuUtility.EOL);
        return sb.toString();
    }

    // Method prints all transactions for individual items.
    public String printItemTransactions(String itemID) {
        StringBuilder sb = new StringBuilder();

        if (storage.checkForUsedID(itemID)) {
            Item item = storage.getItem(itemID);
            sb.append("Transactions for item: ").append(item).append(MenuUtility.EOL);
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

    // Method prints the most profitable items, using the list acquired from getMostProfitableItems().
    public String printMostProfitableItems() {
        StringBuilder sb = new StringBuilder();

        if (storage.isItemMapEmpty()) {
            sb.append("No items registered yet.");
        } else if (storage.isTransactionMapEmpty()) {
            sb.append("No items were bought yet.");
        } else {
            sb.append("Most profitable items: ").append(MenuUtility.EOL);
            double totalProfit = 0;
            StringBuilder items = new StringBuilder();
            for (String itemID : getMostProfitableItems()) {
                totalProfit += getProfit(itemID);
                items.append(storage.getItem(itemID)).append(MenuUtility.EOL);
            }
            sb.append("Total profit: ").append(MenuUtility.doubleFormat(totalProfit)).append(" SEK").append(MenuUtility.EOL);
            sb.append(items);
        }
        return sb.toString();
    }
}