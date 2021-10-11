package utility;

import java.text.DecimalFormat;

public class MenuUtility {

    public static final String EOL = System.lineSeparator();
    
    //helper method for debugging, internal sout can be commented out to disable debug messages
    public static void print(String text) {
        System.out.println(text);
    }

    public static double doubleTruncate(double value) {
        return (Math.floor(value * Math.pow(10, 2))) / Math.pow(10, 2);
    }

    public static double meanGradeTruncate(double value) {
        return (Math.floor(value * 10)) / 10;
    }

    public static String doubleFormat(double value) {
        return new DecimalFormat("#.00").format(value);
    }
}