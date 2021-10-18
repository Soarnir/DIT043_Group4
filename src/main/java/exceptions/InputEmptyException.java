package exceptions;

public class InputEmptyException extends RuntimeException{

    public InputEmptyException() {
        //default message
    }

    public InputEmptyException(String message) {
        super(message);
    }
}
