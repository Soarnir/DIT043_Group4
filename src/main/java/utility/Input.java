package utility;

import java.util.Scanner;

public class Input {

    //TODO refactor class with exception handling (possibly)

    //Create protected Scanner object, due to menu design this is the only scanner created in the project
    final static Scanner inputScanner = new Scanner(System.in);

    public static String readString() {
        String word = inputScanner.next();
        inputScanner.nextLine();
        return word;
    }

    public static String readString(boolean words) {
        return inputScanner.nextLine();
    }

    public static String readItemName() {
        String name = inputScanner.next();
        if (name.equals("")) {
            return "Invalid data for item";
        } else {
            return name;
        }
    }

    /*
     * Method manages user input of menu options, assuming nothing other than integers gets input by the user.
     * User must enter integer between lowerBound and upperBound inclusive in order to progress through menu.
     */
    public static int readMenuInt(int lowerBound, int upperBound) {
        boolean invalidInput = true;
        int inputInteger;
        do {
            inputInteger = inputScanner.nextInt();
            inputScanner.nextLine();
            if (inputInteger < lowerBound || inputInteger > upperBound) {
                MenuUtility.print("Invalid menu option. Please type another option");
            } else {
                invalidInput = false;
            }
        } while (invalidInput);
        return inputInteger;
    }

    public static int readInt() {
        int x = inputScanner.nextInt();
        inputScanner.nextLine();
        return x;
    }

    public static double readDouble() {
        double x = inputScanner.nextDouble();
        inputScanner.nextLine();
        return x;
    }

    public static void closeScanner() {
        inputScanner.close();
    }

}
