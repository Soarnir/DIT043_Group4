package menus;

import utility.MenuUtility;
import utility.Input;

public class MainMenu {

    Input inputter = new Input();

    String initialMainMenu = "Main Menu: Please choose among the options below." + MenuUtility.EOL + MenuUtility.EOL +
                             "0. Close system." + MenuUtility.EOL +
                             "1. Open Item options." + MenuUtility.EOL +
                             "2. Open Review options." + MenuUtility.EOL +
                             "3. Open Transaction History options." + MenuUtility.EOL + MenuUtility.EOL +
                             "Type an option number: " + MenuUtility.EOL; //Is EOL needed here or not?

    int chosenMainMenuOption = inputter.readInt(0,3);

    public void printMenu() {
        System.out.println(initialMainMenu);
    }

//    switch (chosenMainMenuOption) {
//        case 0:
//            System.out.println("Hi");
//            break;
//        case 1:
//            System.out.println("Hi");
//            break;
//        default:
//            System.out.println("Hi");
//    }

    public void whatever () {
        System.out.println(chosenMainMenuOption);
    }

}

