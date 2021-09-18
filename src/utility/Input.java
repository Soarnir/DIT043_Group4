package utility;

import java.util.Scanner;

public class Input {

    public Scanner inputScanner = new Scanner(System.in);


    public String readString() {
        return "";
    }

    public int readInt(int lowerBound, int upperBound) {
        int x = inputScanner.nextInt();
        inputScanner.nextLine();
        return x;
    }

    public int readInt(int upperBound) {
        return 0;
    }

    public double readDouble(int lowerBound, int upperBound) {
        return 0.0;
    }

}
