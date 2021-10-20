package exceptions;

public class InvalidDegreeException extends RuntimeException {

    public InvalidDegreeException() {
        super("Degree must be one of the options: BSc, MSc or PhD.");
    }
}
