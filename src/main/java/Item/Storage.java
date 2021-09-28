package Item;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class Storage {

    // No getter has been implemented because only the Storage class should be accessing usedIDs.
    private static final List<String> usedIDs = new ArrayList<>();

    private HashMap itemMap = new HashMap();

    public HashMap getItemMap(){
        return itemMap;
    }

    /*
     * I (Kevin) has implemented this as discussed but do not need it for User Stories 2.1 - 2.3
     * Oliver, feel free to use if needed for your user stories in Epic feature 2. If not, we can delete it.
     */
    public boolean checkForUsedID(String itemID) {
        if (usedIDs.contains(itemID)) {
            return true;
        } else {
            return false;
        }
    }

    public Object createItem(String itemID, String itemName, int itemPrice) {
        if (itemID.equals("") || (Storage.usedIDs.contains(itemID)) || itemName.equals("") || (itemPrice <= 0)) {
            System.out.println("Invalid data for item.");
            return null; // Not sure about this part yet.
        } else {
            Item item = new Item(itemID, itemName, itemPrice);
            usedIDs.add(itemID);
            itemMap.put(itemID, item);
            System.out.println("Item " + itemID + " was registered successfully.");
            return item;
        }
    }

}
