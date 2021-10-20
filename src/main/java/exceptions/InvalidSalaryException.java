package exceptions;

public class InvalidSalaryException extends RuntimeException {

    public InvalidSalaryException() {
        super("Salary must be greater than zero.");
    }
}
