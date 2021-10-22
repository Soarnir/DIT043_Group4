package exceptions;

public class InvalidDepartmentException extends Exception {

    public InvalidDepartmentException() {
        super("Department must be one of the options: Business, Human Resources or Technical.");
    }
}
