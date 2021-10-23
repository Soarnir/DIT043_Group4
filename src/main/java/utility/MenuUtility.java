package utility;

public class MenuUtility {

    public static final String EOL = System.lineSeparator();

    // Truncates any double to a set scale, representing the number of decimal points to be truncated to.
    public static double doubleTruncate(double value, int scale) {
        return ((int) (value * Math.pow(10, scale))) / Math.pow(10, scale);
    }

    public static String doubleFormat(double value) {
        return String.format("%.2f", doubleTruncate(value, 2));
    }
}