package exceptions;

public class InvalidSalaryException extends RuntimeException{

    public InvalidSalaryException() {
        //default message
    }

    public InvalidSalaryException(String message) {
        super(message);
    }
}
