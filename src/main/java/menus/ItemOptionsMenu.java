package menus;

import utility.Input;
import utility.MenuUtility;

public class ItemOptionsMenu {

    //Utility class declaration
    Input inputter;
    MenuUtility mUtil;

    /*
     * Constructor for ItemOptionsMenu
     * Requires Input and MenuUtility utility classes
     */
    public ItemOptionsMenu (Input input, MenuUtility menuUtility) {
        this.inputter = input;
        this.mUtil = menuUtility;
    }

    //Menu text
    String itemMenuOptions = "Item.Item options menu:" + MenuUtility.EOL +
                             "0. Return to Main Menu." + MenuUtility.EOL +
                             "1. Create an Item.Item." + MenuUtility.EOL +
                             "2. Remove an Item.Item." + MenuUtility.EOL +
                             "3. Print all registered Items." + MenuUtility.EOL +
                             "4. Buy an Item.Item." + MenuUtility.EOL +
                             "5. Update an item’s name." + MenuUtility.EOL +
                             "6. Update an item’s price." + MenuUtility.EOL + MenuUtility.EOL +
                             "Type an option number:";

    String specifyPurchaseItemAmount = "";

    /*
     * Enter item menu loop, error handling is managed by Input class
     * User stays in loop even when accessing menu options, exit is only provided upon invalid input or 0
     */
    public void printMenu() {
        boolean loop = true;
        do {
            System.out.println(itemMenuOptions);
            int chosenMenuOption = inputter.readMenuInt(0, 6);
            switch (chosenMenuOption) {
                case 1:
                    //Create an Item.Item
                    break;
                case 2:
                    //Remove an Item.Item
                    break;
                case 3:
                    //Print all registered Items
                    break;
                case 4:
                    //Buy an Item.Item
                    break;
                case 5:
                    //Update an item’s name
                    break;
                case 6:
                    //Update an item’s price
                    break;
                default:
                    loop = false;
            }
        } while (loop);
    }

    /*
     * -Whenever the user decides to buy an Item.Item, he must specify the ID of the Item.Item he wants to buy.
     * -The System checks if the specified Item.Item ID exists and if so, proceeds to the amount screen
     * -The amount screen should be put in by the user
     * -The total price containing discounts should be calculated and then printed by the system
     * -Return to Item.Item options menu
     */
    public String buyItem(){

        System.out.println("Main Menu > Item.Item options menu > Buy an Item.Item." + MenuUtility.EOL +
                "Please specify the ID of the item you would like to buy:");

        String inputItemID = inputter.readString();
        //TODO Implement Input ID existance
        /*
        if (Item.Item.usedIDs.contains(Item.Item(String inputItemID))){

        }
        */

        return inputItemID;
    }

    public static void mainOfItemOptionsMenu(String[] args){



    }


}
