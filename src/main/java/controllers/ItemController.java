package controllers;

import items.Item;
import utility.Storage;
import utility.MenuUtility;

import java.util.ArrayList;

public class ItemController {

    Storage storage;

    /*
     *
     */
    public ItemController(Storage storage) {
        this.storage = storage;
    }

    public String createItem(String itemID, String itemName, double itemPrice) {
        if (itemID.isEmpty() || storage.checkForUsedID(itemID) || itemName.isEmpty() || (itemPrice <= 0)) {
            return "Invalid data for item.";
        } else {
            storage.getUsedIDs().add(itemID);
            storage.getItemMap().put(itemID, new Item(itemID, itemName, itemPrice));
            storage.getTransactionMap().put(itemID, new ArrayList<>());
            return "Item " + itemID + " was registered successfully.";
        }
    }

    public String removeItem(String itemID) {
        if (storage.checkForUsedID(itemID) && storage.getItem(itemID) != null) {
            storage.getItemMap().remove(itemID);
            return "Item " + itemID + " was successfully removed.";
        }
        return "Item " + itemID + " could not be removed.";
    }

    public String updateItem(String itemID, String newName) {
        if (!storage.checkForUsedID(itemID)) {
            return "Item " + itemID + " was not registered yet.";
        } else if (newName.isEmpty()) {
            return "Invalid data for item.";
        } else {
            storage.getItem(itemID).updateItemName(newName);
            return "Item " + itemID + " was updated successfully.";
        }
    }

    public String updateItem(String itemID, double newPrice) {
        if (!storage.checkForUsedID(itemID)) {
            return "Item " + itemID + " was not registered yet.";
        } else if (newPrice <= 0) {
            return "Invalid data for item.";
        } else {
            storage.getItem(itemID).updateItemPrice(newPrice);
            return "Item " + itemID + " was updated successfully.";
        }
    }

    public String printItem(String itemID) {
        if (storage.checkForUsedID(itemID)) {
            return storage.getItem(itemID).toString();
        } else {
            return "Item " + itemID + " was not registered yet.";
        }
    }

    public String printAllItems() {
        if (storage.getItemMap().isEmpty()) {
            return "No items registered yet.";
        } else {
            StringBuilder stringBuilder = new StringBuilder("All registered items:" + MenuUtility.EOL);
            for (Item item : storage.getItemMap().values()) {
                stringBuilder.append(item.toString()).append(MenuUtility.EOL);
            }
            return stringBuilder.toString();
        }
    }
}
