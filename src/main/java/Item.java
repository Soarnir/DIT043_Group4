import utility.Storage;

public class Item {

    public String itemID = ""; //int or string?
    public String itemName = "";
    public int itemPrice = 0;

    public Item(String id, String name, int price) {

        if (id.equals("") && (Storage.usedIDs.contains(id))) { //Only checking for blank IDs, no check for uniqueness has been implemented yet.
            System.out.println("Invalid data for item."); //Lines 9 - 10 are repeated, can it be placed in a function?
            return;
        } else {
            itemID = id;
        }

        if (name.equals("")) {
            System.out.println("Invalid data for item.");
            return;
        } else {
            itemName = name;
        }

        if (price <= 0) {
            System.out.println("Invalid data for item.");
            return;
        } else {
            itemPrice = price;
        }
    }

    //When the item is created successfully, the system should print the message: "Item <ID> was registered successfully."

    public void updateItem(String newName, int newPrice) {

        if (newName.equals("") || newPrice <= 0) {
            System.out.println("Invalid data for item.");
        } else {
            itemName = newName;
            itemPrice = newPrice;
        }

    }

}
