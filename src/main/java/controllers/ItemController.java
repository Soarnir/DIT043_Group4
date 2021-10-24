package controllers;

import items.Item;
import utility.Storage;
import utility.MenuUtility;

import java.util.ArrayList;

public class ItemController {

    /*
     * Although the storage attribute is declared as final,
     * we decided to not use uppercase as it is a reference to an object.
     */
    private final Storage storage;

    /*
     * The controller constructor passes through the same Storage reference from the Facade
     * to be used by the controller's methods.
     */
    public ItemController(Storage storage) {
        this.storage = storage;
    }

    // Method handles the creation of items and lets the end user if the item was registered.
    public String createItem(String itemID, String itemName, double itemPrice) {
        String returnString;
        if (itemID.isEmpty() || storage.checkForUsedID(itemID) || itemName.isEmpty() || (itemPrice <= 0)) {
            returnString = "Invalid data for item.";
        } else {
            storage.addItem(itemID, new Item(itemID, itemName, itemPrice));
            storage.getTransactionMap().put(itemID, new ArrayList<>());
            returnString = "Item " + itemID + " was registered successfully.";
        }
        return returnString;
    }

    // Method handles the removal of items and lets the end user know if it was successfully removed.
    public String removeItem(String itemID) {
        String returnString;
        if (storage.checkForUsedID(itemID) && storage.getItem(itemID) != null) {
            storage.removeItem(itemID);
            returnString = "Item " + itemID + " was successfully removed.";
        } else {
            returnString = "Item " + itemID + " could not be removed.";
        }
        return returnString;
    }

    // Method allows the user to update the name of an existing item.
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

    // Method allows the user to update the price of an existing item.
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

    // Method prints the ID of an item with its corresponding name and price.
    public String printItem(String itemID) {
        String returnString;
        if (storage.checkForUsedID(itemID)) {
            returnString = storage.getItem(itemID).toString();
        } else {
            returnString = "Item " + itemID + " was not registered yet.";
        }
        return returnString;
    }

    // Method prints all currently registered item IDs with their corresponding names and prices.
    public String printAllItems() {
        StringBuilder sb = new StringBuilder();
        if (storage.isItemMapEmpty()) {
            sb.append("No items registered yet.");
        } else {
            sb.append("All registered items:").append(MenuUtility.EOL);
            for (Item item : storage.getItemMap().values()) {
                sb.append(item).append(MenuUtility.EOL);
            }
        }
        return sb.toString();
    }
}