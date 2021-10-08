package handlers;

import Item.Item;
import Item.Storage;
import utility.MenuUtility;

public class ItemController {

    Storage storage;

    public ItemController(Storage storage) {
        this.storage = storage;
    }

    public String createItem(String itemID, String itemName, double itemPrice) {
        if (itemID.equals("") || storage.checkForUsedID(itemID) || itemName.equals("") || (itemPrice <= 0)) {
            MenuUtility.print("Problem: ID: " + itemID + " |Name: " + itemName + " |itemPrice: " + itemPrice + " |Exists: " + storage.checkForUsedID(itemID));
            return "Invalid data for item.";
        } else {
            MenuUtility.print("Created: ID: " + itemID + " |Name: " + itemName + " |itemPrice: " + itemPrice);
            storage.getUsedIDs().add(itemID);
            storage.getItemMap().put(itemID, new Item(itemID, itemName, itemPrice));
            return "Item " + itemID + " was registered successfully.";
        }
    }

    public String removeItem(String itemID) {
        if (storage.checkForUsedID(itemID) && storage.getItem(itemID) != null) {
            storage.getUsedIDs().remove(itemID);
            storage.getItemMap().remove(itemID);
            //MenuUtility.print("Item " + itemID + " was successfully removed.");
            return "Item " + itemID + " was successfully removed.";
        }
        //MenuUtility.print("Item " + itemID + " could not be removed.");
        return "Item " + itemID + " could not be removed.";
    }

    public String printAllItems() {
        if (storage.getItemMap().isEmpty()) {
            return "No items registered yet.";
        } else {
            StringBuilder stringBuilder = new StringBuilder("All registered items:" + MenuUtility.EOL);
            for (int i = 0; i < storage.getUsedIDs().size(); i++) {
                Item item = storage.getItem(storage.getUsedIDs().get(i));
                stringBuilder.append(item.toString()).append(MenuUtility.EOL);
            }
            return stringBuilder.toString();
        }
    }

    public String updateItem(String itemID, String newName) {
        if (!storage.checkForUsedID(itemID)) {
            return "Item " + itemID + " was not registered yet.";
        } else if (newName.equals("")) {
            return "Invalid data for item.";
        } else {
            MenuUtility.print("Item: " + itemID + " name: " + storage.getItem(itemID).getItemName() + " | new name: " + newName);
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
            MenuUtility.print("Item: " + itemID + " price: " + storage.getItem(itemID).getItemPrice() + " | new price: " + newPrice);
            storage.getItem(itemID).updateItemPrice(newPrice);
            return "Item " + itemID + " was updated successfully.";
        }
    }
}
