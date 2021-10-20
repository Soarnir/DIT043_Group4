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
    final int EXIT = 0;
    final int CREATE_ITEM = 1;
    final int REMOVE_ITEM = 2;
    final int PRINT_ALL_ITEMS = 3;
    final int BUY_ITEM = 4;
    final int UPDATE_ITEM_NAME = 5;
    final int UPDATE_ITEM_PRICE = 6;
    final int PRINT_ITEM = 7;
    final int TOTAL_MENU_OPTIONS = 7;

    //Menu text
    final String ITEM_MENU_OPTIONS = "Item options menu:" + MenuUtility.EOL +
                                     "0. Return to Main Menu." + MenuUtility.EOL +
                                     "1. Create an Item." + MenuUtility.EOL +
                                     "2. Remove an Item." + MenuUtility.EOL +
                                     "3. Print all registered Items." + MenuUtility.EOL +
                                     "4. Buy an Item." + MenuUtility.EOL +
                                     "5. Update an item’s name." + MenuUtility.EOL +
                                     "6. Update an item’s price." + MenuUtility.EOL +
                                     "7. Print a specific item." + MenuUtility.EOL + MenuUtility.EOL +
                                     "Type an option number:";

    /*
     * Enter item menu loop, error handling is managed by Input class
     * User stays in loop even when accessing menu options, exit is only provided upon invalid input or 0
     */
    public void printMenu() {
        int chosenMenuOption;
        do {
            MenuUtility.print(ITEM_MENU_OPTIONS);
            String itemID, itemName;
            double itemPrice;
            int purchaseAmount;
            chosenMenuOption = Input.readMenuInt(EXIT, TOTAL_MENU_OPTIONS);
            switch (chosenMenuOption) {
                case CREATE_ITEM:
                    itemID = Input.readString("ID: ");
                    itemName = Input.readString("Name: ",true);
                    itemPrice = Input.readDouble("Item price: ");

                    System.out.println(facade.createItem(itemID, itemName, itemPrice));
                    break;
                case REMOVE_ITEM:
                    itemID = Input.readString("ID: ");

                    System.out.println(facade.removeItem(itemID));
                    break;
                case PRINT_ALL_ITEMS:
                    facade.printAllItems();
                    break;
                case BUY_ITEM:
                    itemID = Input.readString("ID: ");
                    purchaseAmount = Input.readInt("Amount: ");

                    System.out.println(facade.buyItem(itemID, purchaseAmount));
                    break;
                case UPDATE_ITEM_NAME:
                    itemID = Input.readString("ID: ");
                    itemName = Input.readString("Name: ",true);

                    System.out.println(facade.updateItemName(itemID, itemName));
                    break;
                case UPDATE_ITEM_PRICE:
                    itemID = Input.readString("ID: ");
                    itemPrice = Input.readDouble("Price: ");

                    System.out.println(facade.updateItemPrice(itemID, itemPrice));
                    break;
                case PRINT_ITEM:
                    itemID = Input.readString("ID: ");

                    System.out.println(facade.printItem(itemID));
            }
        } while (chosenMenuOption != EXIT);
    }
}
