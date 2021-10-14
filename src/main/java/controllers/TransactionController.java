package controllers;

import item.Item;
import item.Storage;
import item.Transaction;
import utility.MenuUtility;

import java.util.ArrayList;
import java.util.List;

public class TransactionController {

    Storage storage;

    public TransactionController(Storage storage) {
        this.storage = storage;
    }

    public List<Transaction> getTransactionList(String itemID) {
        if (storage.getItemTransactionList(itemID) == null) {
            return new ArrayList<>();
        } else {
            return storage.getItemTransactionList(itemID);
        }
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
        for (Transaction transaction : getTransactionList(itemID)) {
            unitsSold += transaction.getAmount();
        }
        return unitsSold;
    }

    public String printItemTransactions(String itemID) {
        if (storage.checkForUsedID(itemID)) {
            Item item = storage.getItem(itemID);
            StringBuilder stringBuilder = new StringBuilder("Transactions for item: " + item.toString() + MenuUtility.EOL);
            if (!getTransactionList(itemID).isEmpty()) {
                stringBuilder.append(getItemTransactions(item.getItemID()));
            } else {
                stringBuilder.append("No transactions have been registered for item ").append(item.getItemID()).append(" yet.");
            }
            return stringBuilder.toString();
        } else {
            return "Item " + itemID + " was not registered yet.";
        }
    }

    public String getItemTransactions(String itemID) {
        StringBuilder stringBuilder = new StringBuilder();
        List<Transaction> itemTransactions = getTransactionList(itemID);
        for (Transaction transaction : itemTransactions) {
            stringBuilder.append(transaction.toString()).append(MenuUtility.EOL);
        }
        return stringBuilder.toString();
    }

    public String printMostProfitableItems() {
        StringBuilder stringBuilder = new StringBuilder();
        if (storage.getItemMap().isEmpty()) {
            stringBuilder.append("No items registered yet.");
        } else if (storage.getTransactionMap().isEmpty()) {
            stringBuilder.append("No items were bought yet.");
        } else {
            stringBuilder.append("Most profitable items: ").append(MenuUtility.EOL);
            double totalProfit = 0;
            StringBuilder items = new StringBuilder();
            for (String itemID : getMostProfitableItems()) {
                totalProfit += getProfit(itemID);
                items.append(storage.getItem(itemID).toString()).append(MenuUtility.EOL);
            }
            stringBuilder.append("Total profit: ").append(MenuUtility.doubleFormat(totalProfit)).append(" SEK").append(MenuUtility.EOL);
            stringBuilder.append(items);
        }
        return stringBuilder.toString();
    }

    public List<String> getMostProfitableItems() {
        List<String> mostProfitable = new ArrayList<>();
        for (String itemID : storage.getItemMap().keySet()) {
            List<Transaction> transactions = getTransactionList(itemID);
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
}
