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

    String buyItemID = "-Buy an Item.-" + MenuUtility.EOL +
                        "Please specify the ID of the item you would like to buy:";

    String specifyPurchaseItemAmount = "";

    int chosenItemMenuOption = inputter.readMenuInt(0,6);


    public void printMenu() {
        System.out.println(initialItemOptionsMenu);
    }


    public String buyItem(){

        System.out.println("-Buy an Item.-" + MenuUtility.EOL +
                            "Please specify the ID of the item you would like to buy:");

        String ItemID = inputter.readString();

        return ItemID;
    }


}
