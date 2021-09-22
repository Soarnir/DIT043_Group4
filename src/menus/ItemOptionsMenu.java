package menus;

import utility.MenuUtility;
import utility.Input;
public class ItemOptionsMenu {

    Input inputter = new Input();

    String initialItemOptionsMenu = "Item options menu:" + MenuUtility.EOL +
                                    "0. Return to Main Menu." + MenuUtility.EOL +
                                    "1. Create an Item." + MenuUtility.EOL +
                                    "2. Remove an Item." + MenuUtility.EOL +
                                    "3. Print all registered Items." + MenuUtility.EOL +
                                    "4. Buy an Item." + MenuUtility.EOL +
                                    "5. Update an item’s name." + MenuUtility.EOL +
                                    "6. Update an item’s price." + MenuUtility.EOL + MenuUtility.EOL +
                                    "Type an option number:";

    String specifyPurchaseItemAmount = "";

    int chosenItemMenuOption = inputter.readMenuInt(0,6);


    public void printMenu() {
        System.out.println(initialItemOptionsMenu);
    }

/*
 * -Whenever the user decides to buy an Item, he must specify the ID of the Item he wants to buy.
 * -The System checks if the specified Item ID exists and if so, proceeds to the amount screen
 * -The amount screen should be put in by the user
 * -The total price containing discounts should be calculated and then printed by the system
 * -Return to Item options menu
 */
    public String buyItem(){

        System.out.println("Main Menu > Item options menu > Buy an Item." + MenuUtility.EOL +
                "Please specify the ID of the item you would like to buy:");

        String inputItemID = inputter.readString();
        //TODO Implement Input ID existance
        /*
        if (Item.usedIDs.contains(Item(String inputItemID))){

        }
        */

        return inputItemID;
    }

    public static void mainOfItemOptionsMenu(String[] args){



    }


}
