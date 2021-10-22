package controllers;

import items.Item;
import utility.Storage;
import utility.MenuUtility;

import java.util.ArrayList;

public class ItemController {

    private final Storage storage;

    /*
     * The controller constructor passes through the same Storage reference from the Facade to be used by the controllers methods
     */
    public ItemController(Storage storage) {
        this.storage = storage;
    }

    public String createItem(String itemID, String itemName, double itemPrice) {
        String returnString;
        if (itemID.isEmpty() || storage.checkForUsedID(itemID) || itemName.isEmpty() || (itemPrice <= 0)) {
            returnString = "Invalid data for item.";
        } else {
            storage.getUsedIDs().add(itemID);
            storage.getItemMap().put(itemID, new Item(itemID, itemName, itemPrice));
            storage.getTransactionMap().put(itemID, new ArrayList<>());
            returnString = "Item " + itemID + " was registered successfully.";
        }
        return returnString;
    }

    public String removeItem(String itemID) {
        String returnString;
        if (storage.checkForUsedID(itemID) && storage.getItem(itemID) != null) {
            storage.getItemMap().remove(itemID);
            returnString = "Item " + itemID + " was successfully removed.";
        } else {
            returnString = "Item " + itemID + " could not be removed.";
        }
        return returnString;
    }

    public String updateItem(String itemID, String newName) {
        String returnString;
        if (!storage.checkForUsedID(itemID)) {
            returnString = "Item " + itemID + " was not registered yet.";
        } else if (newName.isEmpty()) {
            returnString = "Invalid data for item.";
        } else {
            storage.getItem(itemID).updateItemName(newName);
            returnString = "Item " + itemID + " was updated successfully.";
        }
        return returnString;
    }

    public String updateItem(String itemID, double newPrice) {
        String returnString;
        if (!storage.checkForUsedID(itemID)) {
            returnString = "Item " + itemID + " was not registered yet.";
        } else if (newPrice <= 0) {
            returnString = "Invalid data for item.";
        } else {
            storage.getItem(itemID).updateItemPrice(newPrice);
            returnString = "Item " + itemID + " was updated successfully.";
        }
        return returnString;
    }

    public String printItem(String itemID) {
        String returnString;
        if (storage.checkForUsedID(itemID)) {
            returnString = storage.getItem(itemID).toString();
        } else {
            returnString = "Item " + itemID + " was not registered yet.";
        }
        return returnString;
    }

    public String printAllItems() {
        StringBuilder sb = new StringBuilder();
        if (storage.getItemMap().isEmpty()) {
            sb.append("No items registered yet.");
        } else {
            sb.append("All registered items:").append(MenuUtility.EOL);
            for (Item item : storage.getItemMap().values()) {
                sb.append(item.toString()).append(MenuUtility.EOL);
            }
        }
        return sb.toString();
    }
}
