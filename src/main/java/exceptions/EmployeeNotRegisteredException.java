package exceptions;

public class EmployeeNotRegisteredException extends Exception {

    public EmployeeNotRegisteredException(String employeeID) {
        super("Employee " + employeeID + " was not registered yet.");
    }
}
