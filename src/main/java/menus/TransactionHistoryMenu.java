package menus;

import facade.Facade;
import utility.Input;
import utility.MenuUtility;

public class TransactionHistoryMenu {

    private final Facade facade;

    /*
     * Constructor for TransactionHistoryMenu
     * Requires Facade passed through
     */
    public TransactionHistoryMenu(Facade facade) {
        this.facade = facade;
    }

    //Menu options
    private static final int EXIT = 0;
    private static final int PRINT_ALL_TOTAL_PROFIT = 1;
    private static final int PRINT_ALL_UNITS_SOLD = 2;
    private static final int PRINT_ALL_TOTAL_TRANSACTIONS = 3;
    private static final int PRINT_ALL_TRANSACTIONS = 4;
    private static final int PRINT_ITEM_TOTAL_PROFIT = 5;
    private static final int PRINT_ITEM_UNITS_SOLD = 6;
    private static final int PRINT_ITEM_TRANSACTIONS = 7;
    private static final int PRINT_ITEM_HIGHEST_PROFIT = 8;

    //Menu text
    private static final String TRANSACTION_MENU_OPTIONS =
        "Transaction History options menu:" + MenuUtility.EOL +
        "0. Return to Main Menu." + MenuUtility.EOL +
        "1. Print total profit from all item purchases" + MenuUtility.EOL +
        "2. Print total units sold from all item purchases" + MenuUtility.EOL +
        "3. Print the total number of item transactions made." + MenuUtility.EOL +
        "4. Print all transactions made." + MenuUtility.EOL +
        "5. Print the total profit of a specific item." + MenuUtility.EOL +
        "6. Print the number of units sold of a specific item." + MenuUtility.EOL +
        "7. Print all transactions of a specific item." + MenuUtility.EOL +
        "8. Print item with highest profit." + MenuUtility.EOL + MenuUtility.EOL +
        "Type an option number: ";

    /*
     * Enter transaction history menu loop, error handling is limited to correct integer input for menu options
     * User stays in loop even when accessing menu options, exit is only provided upon invalid non-integer input or 0
     */
    public void printMenu() {
        int chosenMenuOption;
        boolean shouldPrintMenu = true;

        do {
            String itemID;

            if (shouldPrintMenu)
                System.out.print(TRANSACTION_MENU_OPTIONS);
            chosenMenuOption = Input.readInt();
            shouldPrintMenu = true;
            switch (chosenMenuOption) {
                case EXIT:
                    break;
                case PRINT_ALL_TOTAL_PROFIT:
                    System.out.println(facade.getTotalProfit());
                    break;
                case PRINT_ALL_UNITS_SOLD:
                    System.out.println(facade.getTotalUnitsSold());
                    break;
                case PRINT_ALL_TOTAL_TRANSACTIONS:
                    System.out.println(facade.getTotalTransactions());
                    break;
                case PRINT_ALL_TRANSACTIONS:
                    System.out.println(facade.printAllTransactions());
                    break;
                case PRINT_ITEM_TOTAL_PROFIT:
                    itemID = Input.readString("ID: ");

                    System.out.println(facade.getProfit(itemID));
                    break;
                case PRINT_ITEM_UNITS_SOLD:
                    itemID = Input.readString("ID: ");

                    System.out.println(facade.getUnitsSolds(itemID));
                    break;
                case PRINT_ITEM_TRANSACTIONS:
                    itemID = Input.readString("ID: ");

                    System.out.println(facade.printItemTransactions(itemID));
                    break;
                case PRINT_ITEM_HIGHEST_PROFIT:
                    System.out.println(facade.printMostProfitableItems());
                    break;
                default:
                    shouldPrintMenu = false;
                    System.out.println("Invalid menu option. Please type another option");
            }
        } while (chosenMenuOption != EXIT);
    }
}
