package menus;

import facade.Facade;
import utility.Input;
import utility.MenuUtility;

public class TransactionHistoryMenu {

    //Utility class declaration
    Facade facade;

    /*
     * Constructor for TransactionHistoryMenu
     * Requires Facade passed through
     */
    public TransactionHistoryMenu(Facade facade) {
        this.facade = facade;
    }

    //Menu options
    final int EXIT = 0;
    final int PRINT_ALL_TOTAL_PROFIT = 1;
    final int PRINT_ALL_UNITS_SOLD = 2;
    final int PRINT_ALL_TOTAL_TRANSACTIONS = 3;
    final int PRINT_ALL_TRANSACTIONS = 4;
    final int PRINT_ITEM_TOTAL_PROFIT = 5;
    final int PRINT_ITEM_UNITS_SOLD = 6;
    final int PRINT_ITEM_TRANSACTIONS = 7;
    final int PRINT_ITEM_HIGHEST_PROFIT = 8;

    //Menu text
    final String TRANSACTION_MENU_OPTIONS =  "Transaction History options menu:" + MenuUtility.EOL +
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
     * Enter transaction history menu loop, error handling is managed by Input class
     * User stays in loop even when accessing menu options, exit is only provided upon invalid input or 0
     */
    public void printMenu() {
        int chosenMenuOption;
        do {
            MenuUtility.print(TRANSACTION_MENU_OPTIONS);
            chosenMenuOption = Input.readMenuInt(0, 8);
            switch (chosenMenuOption) {
                case PRINT_ALL_TOTAL_PROFIT:
                    //Print total profit from all item purchases
                    break;
                case PRINT_ALL_UNITS_SOLD:
                    //Print total units sold from all item purchases
                    break;
                case PRINT_ALL_TOTAL_TRANSACTIONS:
                    //Print the total number of item transactions made
                    break;
                case PRINT_ALL_TRANSACTIONS:
                    //Print all transactions made
                    break;
                case PRINT_ITEM_TOTAL_PROFIT:
                    //Print the total profit of a specific item
                    break;
                case PRINT_ITEM_UNITS_SOLD:
                    //Print the number of units sold of a specific item
                    break;
                case PRINT_ITEM_TRANSACTIONS:
                    //Print all transactions of a specific item
                    break;
                case PRINT_ITEM_HIGHEST_PROFIT:
                    //Print item with highest profit
                    break;
            }
        } while (chosenMenuOption != EXIT);
    }
}
