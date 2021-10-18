package exceptions;

public class InvalidDegreeException extends RuntimeException{

    public InvalidDegreeException() {
        //default message
    }

    public InvalidDegreeException(String message) {
        super(message);
    }
}
