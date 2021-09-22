package menus;

import utility.MenuUtility;
import utility.Input;
import utility.Storage;

public class MainMenu {

    Input inputter = new Input();

    String initialMainMenu = "Main Menu: Please choose among the options below." + MenuUtility.EOL + MenuUtility.EOL +
                             "0. Close system." + MenuUtility.EOL +
                             "1. Open Item options." + MenuUtility.EOL +
                             "2. Open Review options." + MenuUtility.EOL +
                             "3. Open Transaction History options." + MenuUtility.EOL + MenuUtility.EOL +
                             "Type an option number: ";

    public void printMenu() {
        System.out.println(initialMainMenu);
        int chosenMainMenuOption = inputter.readMenuInt(0,3);
    }

}

