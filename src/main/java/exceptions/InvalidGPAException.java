package exceptions;

public class InvalidGPAException extends RuntimeException {

    public InvalidGPAException(int gpa) {
        super(gpa + " outside range. Must be between 0-10.");
    }
}
