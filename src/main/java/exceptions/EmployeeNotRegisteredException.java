package exceptions;

public class EmployeeNotRegisteredException extends RuntimeException {

    public EmployeeNotRegisteredException(String employeeID) {
        super("Employee " + employeeID + " was not registered yet.");
    }
}
