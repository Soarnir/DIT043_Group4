package menus;

import facade.Facade;
import utility.Input;
import utility.MenuUtility;

public class ItemOptionsMenu {

    //Utility class declaration
    Facade facade;

    /*
     * Constructor for ItemOptionsMenu
     * Requires Input and MenuUtility utility classes
     */
    public ItemOptionsMenu(Facade facade) {
        this.facade = facade;
    }

    //Menu text
    String itemMenuOptions = "Item options menu:" + MenuUtility.EOL +
            "0. Return to Main Menu." + MenuUtility.EOL +
            "1. Create an Item." + MenuUtility.EOL +
            "2. Remove an Item." + MenuUtility.EOL +
            "3. Print all registered Items." + MenuUtility.EOL +
            "4. Buy an Item." + MenuUtility.EOL +
            "5. Update an item’s name." + MenuUtility.EOL +
            "6. Update an item’s price." + MenuUtility.EOL + MenuUtility.EOL +
            "Type an option number:";

    String specifyPurchaseItemAmount = "";

    /*
     * Enter item menu loop, error handling is managed by Input class
     * User stays in loop even when accessing menu options, exit is only provided upon invalid input or 0
     */
    public void printMenu() {
        boolean loop = true;
        do {
            System.out.println(itemMenuOptions);
            int chosenMenuOption = Input.readMenuInt(0, 6);
            switch (chosenMenuOption) {
                case 1:
                    System.out.println("pls create item");
                    String id = Input.readString();
                    System.out.println(id);
                    String itemName = Input.readString(true);
                    System.out.println(itemName);
                    double unitCost = Input.readDouble();
                    System.out.println(unitCost);
                    facade.createItem(id, itemName, unitCost);
                    break;
                case 2:
                    System.out.println("pls remove item");
                    String removeID = Input.readString();
                    System.out.println(facade.removeItem(removeID));
                    break;
                case 3:
                    //Print all registered Items
                    break;
                case 4:
                    //Buy an Item
                    break;
                case 5:
                    //Update an item’s name
                    break;
                case 6:
                    //Update an item’s price
                    break;
                default:
                    loop = false;
            }
        } while (loop);
    }

    /*
     * -Whenever the user decides to buy an Item, he must specify the ID of the Item he wants to buy.
     * -The System checks if the specified Item ID exists and if so, proceeds to the amount screen
     * -The amount screen should be put in by the user
     * -The total price containing discounts should be calculated and then printed by the system
     * -Return to Item options menu
     */
    public String buyItem() {

        System.out.println("Main Menu > Item options menu > Buy an Item." + MenuUtility.EOL +
                "Please specify the ID of the item you would like to buy:");
        //TODO Implement Input ID existance
        /*
        if (Item.usedIDs.contains(Item(String inputItemID))){

        }
        */
        return "";
    }
}
