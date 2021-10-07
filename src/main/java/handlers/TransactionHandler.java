package handlers;

import Item.Storage;
import Item.Transaction;
import utility.MenuUtility;

import java.util.List;

public class TransactionHandler {

    Storage storage;

    public TransactionHandler (Storage storage) {
        this.storage = storage;
    }

    public double buyItem(String itemID, int amount) {
        if (storage.checkForUsedID(itemID)) {
            Transaction transaction = new Transaction(storage.getItem(itemID), amount);
            getTransactionList(itemID).add(transaction);
            return MenuUtility.doubleFormatter(transaction.getTransactionCost(), 2);
        } else {
            return -1.0;
        }
    }

    public List<Transaction> getTransactionList(String itemID) {
        return storage.getItem(itemID).getTransactionList();
    }
    
}
