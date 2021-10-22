package menus;

import facade.Facade;
import utility.Input;
import utility.MenuUtility;

public class ItemOptionsMenu {

    //Utility class declaration
    private final Facade facade;

    /*
     * Constructor for ItemOptionsMenu
     * Requires Facade passed through
     */
    public ItemOptionsMenu(Facade facade) {
        this.facade = facade;
    }

    //Menu options
    private static final int EXIT = 0;
    private static final int CREATE_ITEM = 1;
    private static final int REMOVE_ITEM = 2;
    private static final int PRINT_ALL_ITEMS = 3;
    private static final int BUY_ITEM = 4;
    private static final int UPDATE_ITEM_NAME = 5;
    private static final int UPDATE_ITEM_PRICE = 6;
    private static final int PRINT_ITEM = 7;

    //Menu text
    private static final String ITEM_MENU_OPTIONS =
        "Item options menu:" + MenuUtility.EOL +
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
     * Enter item menu loop, error handling is limited to correct integer input for menu options
     * User stays in loop even when accessing menu options, exit is only provided upon invalid non-integer input or 0
     */
    public void printMenu() {
        int chosenMenuOption;
        boolean shouldPrintMenu = true;

        do {
            String itemID, itemName;
            double itemPrice;
            int purchaseAmount;

            if (shouldPrintMenu)
                System.out.print(ITEM_MENU_OPTIONS);
            chosenMenuOption = Input.readInt();
            shouldPrintMenu = true;
            switch (chosenMenuOption) {
                case EXIT:
                    break;
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
                    System.out.println(facade.printAllItems());
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
                    break;
                default:
                    shouldPrintMenu = false;
                    System.out.println("Invalid menu option. Please type another option");
            }
        } while (chosenMenuOption != EXIT);
    }
}
