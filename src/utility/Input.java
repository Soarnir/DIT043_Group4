package utility;

import java.util.Scanner;

public class Input {

    public Scanner inputScanner = new Scanner(System.in);


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

    public int readMenuInt(int lowerBound, int upperBound) {
        boolean fail = true;
        do {
            int x = inputScanner.nextInt();
            inputScanner.nextLine();
            if (x < lowerBound || x > upperBound) {
                System.out.println("Invalid menu option. Please type another option");
            } else {
                return x;
            }
        } while (fail);
        return 0;
    }

    public int readInt(int lowerBound, int upperBound) {
        int x = inputScanner.nextInt();
        inputScanner.nextLine();
        return x;
    }

    public int readInt() {
        int x = inputScanner.nextInt();
        inputScanner.nextLine();
        return x;
    }

    public double readDouble(double lowerBound, double upperBound) {
        double x = inputScanner.nextDouble();
        inputScanner.nextLine();
        return x;
    }

    public double readDouble() {
        double x = inputScanner.nextDouble();
        inputScanner.nextLine();
        return x;
    }

}
