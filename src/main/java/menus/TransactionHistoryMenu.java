package menus;

import facade.Facade;
import utility.Input;
import utility.MenuUtility;

public class TransactionHistoryMenu {

    //Utility class declaration
    Facade facade;

    /*
     * Constructor for TransactionHistoryMenu
     * Requires Input and MenuUtility utility classes
     */
    public TransactionHistoryMenu (Facade facade) {
        this.facade = facade;
    }

    //Menu text
    String transactionMenuOptions =  "Transaction History options menu:" + MenuUtility.EOL +
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
        boolean loop = true;
        do {
            System.out.println(transactionMenuOptions);
            int chosenMenuOption = Input.readMenuInt(0, 3);
            switch (chosenMenuOption) {
                case 1:
                    //Print total profit from all item purchases
                    break;
                case 2:
                    //Print total units sold from all item purchases
                    break;
                case 3:
                    //Print the total number of item transactions made
                    break;
                case 4:
                    //Print all transactions made
                    break;
                case 5:
                    //Print the total profit of a specific item
                    break;
                case 6:
                    //Print the number of units sold of a specific item
                    break;
                case 7:
                    //Print all transactions of a specific item
                    break;
                case 8:
                    //Print item with highest profit
                    break;
                default:
                    //return to main menu
                    loop = false;
            }
        } while (loop);
    }

}
