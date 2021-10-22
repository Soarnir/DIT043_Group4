package exceptions;

public class EmployeesNotRegisteredException extends RuntimeException {

    // Text changed to "No employees registered yet." from "No employee has been registered yet."
    public EmployeesNotRegisteredException() {
        super("No employees registered yet.");
    }
}
