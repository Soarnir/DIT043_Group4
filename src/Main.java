import java.util.Scanner;

public class Main {

    Scanner reader = new Scanner(System.in);

    public static void main(String[] args) {


    }

    public void mainMenu () {
        System.out.println("Main Menu: Please choose among the options below. \n");

        System.out.println("0. Close system.");
        System.out.println("1. Open Item options.");
        System.out.println("2. Open Review options.");
        System.out.println("3. Open Transaction History options. \n");

        System.out.println("Type an option number:");

        int option = reader.nextInt();

        switch (option) {

            case 0:
                System.out.println("Menu Closed");
                break;
            case 1:
                itemMenu();
                break;
            case 2:


        }
    }

    public void itemMenu () {

        System.out.println("Item options menu:");
        System.out.println("0. Return to Main Menu.");
        System.out.println("1. Create an Item.");
        System.out.println("2. Remove an Item.");
        System.out.println("3. Print all registered Items.");
        System.out.println("4. Buy an Item.");
        System.out.println("5. Update an item’s name.");
        System.out.println("6. Update an item’s price.");

        int itemMenuOption = reader.nextInt();

    }

    public void reviewOptions () {

        System.out.println("Reviews options menu:");
        System.out.println("0. Return to Main Menu.");
        System.out.println("1. Create a review for an Item.");
        System.out.println("2. Print a specific review of an Item.");
        System.out.println("3. Print all reviews of an Item.");
        System.out.println("4. Print mean grade of an Item");
        System.out.println("5. Print all comments of an Item.");
        System.out.println("6. Print all registered reviews.");
        System.out.println("7. Print item(s) with most reviews.");
        System.out.println("8. Print item(s) with least reviews.");
        System.out.println("9. Print item(s) with best mean review grade.");
        System.out.println("10. Print item(s) with worst mean review grade.");

    }
}