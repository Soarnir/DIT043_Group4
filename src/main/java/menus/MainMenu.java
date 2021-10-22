package menus;

import facade.Facade;
import utility.Input;
import utility.MenuUtility;

public class MainMenu {

    //Create Facade
    private final Facade facade = new Facade();

    /*
     * Create Menu objects
     * Constructor passes through utility objects such that they do not need to be created multiple times, retaining the same object reference
     */
    private final ItemOptionsMenu itemOptionsMenu = new ItemOptionsMenu(facade);
    private final ReviewOptionsMenu reviewOptionsMenu = new ReviewOptionsMenu(facade);
    private final TransactionHistoryMenu transactionHistoryMenu = new TransactionHistoryMenu(facade);
    private final EmployeeMenu employeeMenu = new EmployeeMenu(facade);

    //Menu options
    private static final int EXIT = 0;
    private static final int ITEM_MENU = 1;
    private static final int REVIEW_MENU = 2;
    private static final int TRANSACTION_MENU = 3;
    private static final int EMPLOYEE_MENU = 4;

    //Menu text
    private final static String MAIN_MENU_OPTIONS =
        "Main Menu: Please choose among the options below." + MenuUtility.EOL + MenuUtility.EOL +
        "0. Close system." + MenuUtility.EOL +
        "1. Open Item options." + MenuUtility.EOL +
        "2. Open Review options." + MenuUtility.EOL +
        "3. Open Transaction History options." + MenuUtility.EOL +
        "4. Open Employee options." + MenuUtility.EOL + MenuUtility.EOL +
        "Type an option number: ";

    /*
     * Enter first menu loop, error handling is limited to correct integer input for menu options
     * User stays in loop even when moving to separate menu, exit is only provided upon invalid non-integer input or 0
     */
    public void printMenu() {
        int chosenMenuOption;
        boolean shouldPrintMenu = true;

        do {
            if (shouldPrintMenu)
                System.out.print(MAIN_MENU_OPTIONS);
            chosenMenuOption = Input.readInt();
            shouldPrintMenu = true;
            switch (chosenMenuOption) {
                case EXIT:
                    Input.closeScanner();
                    break;
                case ITEM_MENU:
                    itemOptionsMenu.printMenu();
                    break;
                case REVIEW_MENU:
                    reviewOptionsMenu.printMenu();
                    break;
                case TRANSACTION_MENU:
                    transactionHistoryMenu.printMenu();
                    break;
                case EMPLOYEE_MENU:
                    employeeMenu.printMenu();
                    break;
                default:
                    shouldPrintMenu = false;
                    System.out.println("Invalid menu option. Please type another option");
            }
        } while (chosenMenuOption != EXIT);
        System.exit(0);
    }
}

