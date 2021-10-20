package exceptions;

public class EmployeesNotRegisteredException extends RuntimeException {

    public EmployeesNotRegisteredException() {
        super("No employee has been registered yet.");
    }
}
