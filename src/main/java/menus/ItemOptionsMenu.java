package menus;

import facade.Facade;
import utility.Input;
import utility.MenuUtility;

public class ItemOptionsMenu {

    //Utility class declaration
    Facade facade;

    /*
     * Constructor for ItemOptionsMenu
     * Requires Facade passed through
     */
    public ItemOptionsMenu(Facade facade) {
        this.facade = facade;
    }

    //Menu options
    final int CREATE_ITEM = 1;
    final int REMOVE_ITEM = 2;
    final int PRINT_ALL_ITEMS = 3;
    final int BUY_ITEM = 4;
    final int UPDATE_ITEM_NAME = 5;
    final int UPDATE_ITEM_PRICE = 6;

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

    /*
     * Enter item menu loop, error handling is managed by Input class
     * User stays in loop even when accessing menu options, exit is only provided upon invalid input or 0
     */
    public void printMenu() {
        boolean loop = true;
        do {
            MenuUtility.print(itemMenuOptions);
            int chosenMenuOption = Input.readMenuInt(0, 6);
            switch (chosenMenuOption) {
                case CREATE_ITEM:
                    System.out.print("ID: ");
                    String createItemID = Input.readString();
                    System.out.print("Name: ");
                    String createItemName = Input.readString(true);
                    System.out.print("Item price: ");
                    double createItemPrice = Input.readDouble();

                    facade.createItem(createItemID, createItemName, createItemPrice);
                    break;
                case REMOVE_ITEM:
                    System.out.print("ID: ");
                    String removeID = Input.readString();

                    System.out.println(facade.removeItem(removeID));
                    break;
                case PRINT_ALL_ITEMS:
                    facade.printAllItems();
                    break;
                case BUY_ITEM:
                    System.out.print("ID: ");
                    String purchaseID = Input.readString();
                    System.out.print("Amount: ");
                    int purchaseAmount = Input.readInt();

                    System.out.println(facade.buyItem(purchaseID, purchaseAmount));
                    break;
                case UPDATE_ITEM_NAME:
                    System.out.print("ID: ");
                    String updateItemNameID = Input.readString();
                    System.out.print("Name: ");
                    String updateItemName = Input.readString(true);

                    System.out.println(facade.updateItemName(updateItemNameID, updateItemName));
                    break;
                case UPDATE_ITEM_PRICE:
                    System.out.print("ID: ");
                    String updateItemPriceID = Input.readString();
                    System.out.print("Name: ");
                    double updateItemPrice = Input.readDouble();

                    System.out.println(facade.updateItemPrice(updateItemPriceID, updateItemPrice));
                    break;
                default:
                    loop = false;
            }
        } while (loop);
    }
}
