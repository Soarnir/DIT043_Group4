package exceptions;

public class InvalidGPAException extends RuntimeException {

    public InvalidGPAException() {
        //default message
    }

    public InvalidGPAException(String message) {
        super(message);
    }
}
