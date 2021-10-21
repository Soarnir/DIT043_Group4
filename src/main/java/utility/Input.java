package utility;

import java.util.Scanner;

public class Input {

    //TODO refactor class with exception handling (possibly)

    //Create protected Scanner object, due to menu design this is the only scanner created in the project
    final static Scanner inputScanner = new Scanner(System.in);

    public static String readString(String message) {
        System.out.print(message);
        String word = inputScanner.next();
        inputScanner.nextLine();
        return word;
    }

    public static String readString(String message, boolean words) {
        System.out.print(message);
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
                System.out.println("Invalid menu option. Please type another option");
            } else {
                invalidInput = false;
            }
        } while (invalidInput);
        return inputInteger;
    }

    public static int readInt(String message) {
        System.out.print(message);
        int readValue = inputScanner.nextInt();
        inputScanner.nextLine();
        return readValue;
    }

    public static double readDouble(String message) {
        System.out.print(message);
        double readValue = inputScanner.nextDouble();
        inputScanner.nextLine();
        return readValue;
    }

    public static void closeScanner() {
        inputScanner.close();
    }

}
