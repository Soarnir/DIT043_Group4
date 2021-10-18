package menus;

import facade.Facade;
import utility.Input;
import utility.MenuUtility;

public class ReviewOptionsMenu {

    //Utility class declaration
    Facade facade;

    /*
     * Constructor for ReviewOptionsMenu
     * Requires Facade passed through
     */
    public ReviewOptionsMenu(Facade facade) {
        this.facade = facade;
    }

    //Menu options
    final int EXIT = 0;
    final int CREATE_REVIEW = 1;
    final int PRINT_SPECIFIC_ITEM_REVIEW = 2;
    final int PRINT_ALL_ITEM_REVIEW = 3;
    final int PRINT_MEAN_GRADE_ITEM = 4;
    final int PRINT_ALL_ITEM_COMMENTS = 5;
    final int PRINT_ALL_REVIEWS = 6;
    final int PRINT_MOST_REVIEWS_ITEM = 7;
    final int PRINT_LEAST_REVIEWS_ITEM = 8;
    final int PRINT_BEST_MEAN_ITEM = 9;
    final int PRINT_WORST_MEAN_ITEM = 10;
    final int TOTAL_MENU_OPTIONS = 10;

    //Menu text
    final String REVIEW_MENU_OPTIONS = "Reviews options menu:" + MenuUtility.EOL +
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
        int chosenMenuOption;
        do {
            MenuUtility.print(REVIEW_MENU_OPTIONS);
            chosenMenuOption = Input.readMenuInt(EXIT, TOTAL_MENU_OPTIONS);
            switch (chosenMenuOption) {
                case CREATE_REVIEW:
                    //Create a review for an Item
                    break;
                case PRINT_SPECIFIC_ITEM_REVIEW:
                    //Print a specific review of an Item
                    break;
                case PRINT_ALL_ITEM_REVIEW:
                    //Print all reviews of an Item
                    break;
                case PRINT_MEAN_GRADE_ITEM:
                    //Print mean grade of an Item
                    break;
                case PRINT_ALL_ITEM_COMMENTS:
                    //Print all comments of an Item
                    break;
                case PRINT_ALL_REVIEWS:
                    //Print all registered reviews
                    break;
                case PRINT_MOST_REVIEWS_ITEM:
                    //Print item(s) with most reviews
                    break;
                case PRINT_LEAST_REVIEWS_ITEM:
                    //Print item(s) with least reviews
                    break;
                case PRINT_BEST_MEAN_ITEM:
                    //Print item(s) with best mean review grade
                    break;
                case PRINT_WORST_MEAN_ITEM:
                    //Print item(s) with worst mean review grade
                    break;
            }
        } while (chosenMenuOption != EXIT);
    }
}

