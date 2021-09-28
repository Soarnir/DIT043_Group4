package Item;

public class Item {

    private String itemID = "";
    private String itemName = "";
    private int itemPrice = 0;

    public Item(String itemID, String itemName, int itemPrice) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    public String getItemID(){ return this.itemID; }

    public String getItemName(){
        return this.itemName;
    }

    public int getItemPrice(){
        return this.itemPrice;
    }

    public void updateItem(String newName, int newPrice) {

        if (newName.equals("") || newPrice <= 0) {
            System.out.println("Invalid data for item.");
        } else {
            this.itemName = newName;
            this.itemPrice = newPrice;
        }

    }

}
