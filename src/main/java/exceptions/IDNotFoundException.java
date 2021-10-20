package exceptions;

public class IDNotFoundException extends RuntimeException {

    public IDNotFoundException(String employeeID) {
        super("Employee " + employeeID + " was not registered yet.");
    }
}
