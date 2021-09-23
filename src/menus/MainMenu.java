package menus;

import utility.MenuUtility;
import utility.Input;
import utility.Storage;

public class MainMenu {

    //Create utility objects
    Input inputter = new Input();

    Storage storage = new Storage();

    MenuUtility mUtil = new MenuUtility();

    /*
     * Create Menu objects
     * Constructor passes through utility objects such that they do not need to be created multiple times, retaining the same object reference
     */
    ItemOptionsMenu itemOptionsMenu = new ItemOptionsMenu(inputter, mUtil);

    ReviewOptionsMenu reviewOptionsMenu = new ReviewOptionsMenu(inputter, mUtil);

    TransactionHistoryMenu transactionHistoryMenu = new TransactionHistoryMenu(inputter, mUtil);

    //Menu text
    String mainMenuOptions = "Main Menu: Please choose among the options below." + MenuUtility.EOL + MenuUtility.EOL +
                             "0. Close system." + MenuUtility.EOL +
                             "1. Open Item options." + MenuUtility.EOL +
                             "2. Open Review options." + MenuUtility.EOL +
                             "3. Open Transaction History options." + MenuUtility.EOL + MenuUtility.EOL +
                             "Type an option number: ";

    /*
     * Enter first menu loop, error handling is managed by Input class
     * User stays in loop even when moving to separate menu, exit is only provided upon invalid input or 0
     */
    public void printMenu() {
        boolean loop = true;
        do {
            System.out.println(mainMenuOptions);
            int chosenMenuOption = inputter.readMenuInt(0, 3);
            switch (chosenMenuOption) {
                case 1:
                    itemOptionsMenu.printMenu();
                    break;
                case 2:
                    reviewOptionsMenu.printMenu();
                    break;
                case 3:
                    transactionHistoryMenu.printMenu();
                    break;
                default:
                    loop = false;
                    inputter.closeScanner();
            }
        } while (loop);
        System.exit(0);
    }
}

