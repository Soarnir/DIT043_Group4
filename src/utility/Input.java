package utility;

import java.util.Scanner;

public class Input {

    //Create protected Scanner object, due to menu design this is the only scanner created in the project
    final Scanner inputScanner = new Scanner(System.in);

    public String readString() {
        String word = inputScanner.next();
        inputScanner.nextLine();
        return word;
    }

    public String readString(boolean words) {
        String wordy = inputScanner.nextLine();
        inputScanner.nextLine();
        return wordy;
    }

    /*
     * Method manages user input of menu options, assuming nothing other than integers gets input by the user.
     * User must enter integer between lowerBound and upperBound inclusive in order to progress through menu.
     */
    public int readMenuInt(int lowerBound, int upperBound) {
        boolean invalidInput = true;
        int x;
        do {
            x = inputScanner.nextInt();
            inputScanner.nextLine();
            if (x < lowerBound || x > upperBound) {
                System.out.println("Invalid menu option. Please type another option");
            } else {
                invalidInput = false;
            }
        } while (invalidInput);
        return x;
    }

    public int readInt() {
        int x = inputScanner.nextInt();
        inputScanner.nextLine();
        return x;
    }

    public double readDouble() {
        double x = inputScanner.nextDouble();
        inputScanner.nextLine();
        return x;
    }

    public void closeScanner() {
        inputScanner.close();
    }

}
