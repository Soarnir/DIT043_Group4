package utility;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MenuUtility {

    public static final String EOL = System.lineSeparator();
    
    //helper method for debugging, internal sout can be commented out to disable debug messages
    public static void sout (String text) {
        System.out.println(text);
    }

    public static double doubleFormatter (BigDecimal value, int scale) {
        return value.setScale(scale, RoundingMode.FLOOR).doubleValue();
    }

}
