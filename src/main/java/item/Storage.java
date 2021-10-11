package item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class Storage {

    // No getter has been implemented because only the Storage class should be accessing usedIDs.
    private final List<String> usedIDs = new ArrayList<>();

    private final LinkedHashMap<String, Item> itemMap = new LinkedHashMap<>();

    public HashMap<String, Item> getItemMap(){
        return itemMap;
    }

    public List<String> getUsedIDs() {
        return usedIDs;
    }

    public boolean checkForUsedID(String itemID) {
        return usedIDs.contains(itemID);
    }

    public Item getItem(String itemID) {
        return getItemMap().get(itemID);
    }

}
