package utility;

import java.text.DecimalFormat;

public class MenuUtility {

    public static final String EOL = System.lineSeparator();
    
    //helper method for debugging, internal sout can be commented out to disable debug messages
    public static void sout (String text) {
        System.out.println(text);
    }

    public static double doubleFormatter (double value, int scale) {
        return (Math.floor(value * Math.pow(10, scale))) / Math.pow(10, scale);
    }

    public static String doubleFormatter (double value) {
        return new DecimalFormat("#.00").format(value);
    }
}