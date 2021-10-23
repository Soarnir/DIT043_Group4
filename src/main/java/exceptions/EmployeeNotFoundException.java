package exceptions;

public class EmployeeNotFoundException extends Exception {

    public EmployeeNotFoundException(String employeeID) {
        super("Employee " + employeeID + " was not registered yet.");
    }
}
