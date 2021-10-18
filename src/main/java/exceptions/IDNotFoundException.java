package exceptions;

public class IDNotFoundException extends RuntimeException {

    public IDNotFoundException() {
        //default message
    }

    public IDNotFoundException(String message) {
        super(message);
    }
}
