package menus;

import utility.Input;
import utility.MenuUtility;

public class ReviewOptionsMenu {

    //Utility class declaration
    Input inputter;
    MenuUtility mUtil;

    /*
     * Constructor for ReviewOptionsMenu
     * Requires Input and MenuUtility utility classes
     */
    public ReviewOptionsMenu (Input input, MenuUtility menuUtility) {
        this.inputter = input;
        this.mUtil = menuUtility;
    }

    //Menu text
    String reviewMenuOptions = "Reviews options menu:" + MenuUtility.EOL +
                               "0. Return to Main Menu." + MenuUtility.EOL +
                               "1. Create a review for an Item." + MenuUtility.EOL +
                               "2. Print a specific review of an Item." + MenuUtility.EOL +
                               "3. Print all reviews of an Item." + MenuUtility.EOL +
                               "4. Print mean grade of an Item" + MenuUtility.EOL +
                               "5. Print all comments of an Item." + MenuUtility.EOL +
                               "6. Print all registered reviews." + MenuUtility.EOL +
                               "7. Print item(s) with most reviews." + MenuUtility.EOL +
                               "8. Print item(s) with least reviews." + MenuUtility.EOL +
                               "9. Print item(s) with best mean review grade." + MenuUtility.EOL +
                               "10. Print item(s) with worst mean review grade." + MenuUtility.EOL + MenuUtility.EOL +
                               "Type an option number: ";

    /*
     * Enter review menu loop, error handling is managed by Input class
     * User stays in loop even when accessing menu options, exit is only provided upon invalid input or 0
     */
    public void printMenu() {
        boolean loop = true;
        do {
            System.out.println(reviewMenuOptions);
            int chosenMenuOption = inputter.readMenuInt(0, 10);
            switch (chosenMenuOption) {
                case 1:
                    //Create a review for an Item
                    break;
                case 2:
                    //Print a specific review of an Item
                    break;
                case 3:
                    //Print all reviews of an Item
                    break;
                case 4:
                    //Print mean grade of an Item
                    break;
                case 5:
                    //Print all comments of an Item
                    break;
                case 6:
                    //Print all registered reviews
                    break;
                case 7:
                    //Print item(s) with most reviews
                    break;
                case 8:
                    //Print item(s) with least reviews
                    break;
                case 9:
                    //Print item(s) with best mean review grade
                    break;
                case 10:
                    //Print item(s) with worst mean review grade
                    break;
                default:
                    loop = false;
            }
        } while (loop);
    }
}

