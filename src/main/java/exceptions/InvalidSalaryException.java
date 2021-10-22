package exceptions;

public class InvalidSalaryException extends Exception {

    public InvalidSalaryException() {
        super("Salary must be greater than zero.");
    }
}
