package utility;

import java.util.Scanner;

public class Input {

    //TODO refactor class

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

    public static int readInt() {
        int readValue = inputScanner.nextInt();
        inputScanner.nextLine();
        return readValue;
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
