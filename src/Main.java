import menus.MainMenu;
import menus.ItemOptionsMenu;

import java.util.Scanner;

public class Main {

    //Scanner reader = new Scanner(System.in);

    public static void main(String[] args) {

        MainMenu menu1 = new MainMenu();
        ItemOptionsMenu itemOptionsMenu1 = new ItemOptionsMenu();
        menu1.printMenu();

    }
}